package lab5.snabbköp.view;

import java.text.DecimalFormat;
import java.util.Observable;

import lab5.deds.Event;
import lab5.deds.State;
import lab5.deds.View;
import lab5.snabbköp.event.ClosingEvent;
import lab5.snabbköp.event.CustomerDrivenEvents;
import lab5.snabbköp.event.StartEvent;
import lab5.snabbköp.event.StopEvent;
import lab5.snabbköp.state.SnabbköpState;

/**
 * 
 * A class that constructs a specialized view that prints the necessary
 * information about the ongoing simulation.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 * 
 *
 */
public class SnabbköpsView extends View {

	private State state;
	private DecimalFormat format;// Saves the output format.

	/**
	 * Constructor. Sets the format the text will have when it is printed. Gets and
	 * saves the state that is taken as a parameter.
	 * 
	 * 
	 * @param state the state that the view observes.
	 */
	public SnabbköpsView(State state) {
		super(state);
		this.state = super.getState();
		format = new DecimalFormat("#0.00");// Sets the output format.
	}

	/**
	 * Decides what print method should be called and then calls that method.
	 * 
	 * 
	 * @param o,   the observable object.
	 * @param arg, an optional object.
	 */
	public void update(Observable o, Object arg) {
		if (arg instanceof StartEvent) {
			printOutStartOnConsol((StartEvent) arg);
		} else if (arg instanceof StopEvent) {
			printOutStopEventOnConsol((StopEvent) arg);
		} else {
			printOutEventOnConsol((Event) arg);
		}
	}

	/**
	 * Prints out the initial information (parameters) about the current running
	 * simulation.
	 * 
	 * @param event of the type StartEvent.
	 */
	private void printOutStartOnConsol(StartEvent event) {

		System.out.println("PARAMETRAR" + "\n=======");
		System.out.println("Antal kassor, N..........: " + ((SnabbköpState) state).getNrOfRegisters());
		System.out.println("Max som ryms, M..........: " + ((SnabbköpState) state).getMaxNrOfCustomersInStore());
		System.out.println("Ankomsthastighet, lambda..: " + ((SnabbköpState) state).getLambda());
		System.out.println("Plocktider, [P_min..Pmax]: " + "[" + ((SnabbköpState) state).getPMin() + ".."
				+ ((SnabbköpState) state).getPMax() + "]");
		System.out.println("Betaltider, [K_min..Kmax]: " + "[" + ((SnabbköpState) state).getKMin() + ".."
				+ ((SnabbköpState) state).getKMax() + "]");
		System.out.println("Frö, f...................: " + ((SnabbköpState) state).getSeed() + "\n");
		System.out.println("FÖRLOPP" + "\n=======");
		System.out.println("Tid	Händelse		Kund	?	led	ledT	I	$	:-(	köat	köT	köar	[Kassakö..]");
		System.out.println("0,00" + "\t" + event.getEventName());
	}

	/**
	 * Prints out the current time of the simulation and the effects the previous
	 * event had on the state.
	 * 
	 * @param event of the type Event.
	 */
	private void printOutEventOnConsol(Event event) {

		System.out.print(format.format(event.getTime()) + "\t" + event.getEventName() + "\t" + "\t");
		if (event instanceof ClosingEvent) {
			System.out.print("---");
		} else {
			System.out.print(((CustomerDrivenEvents) event).getCustomerid());
		}
		System.out.print("\t" + ((SnabbköpState) state).getIsClosedSymbol() + "\t"
				+ ((SnabbköpState) state).getNrOfFreeRegisters() + "\t"
				+ format.format(((SnabbköpState) state).getTotalFreeRegistersTime()) + "\t"
				+ ((SnabbköpState) state).getCurrentCustomersInStore() + "\t"
				+ ((SnabbköpState) state).getSucessfullPurschases() + "\t"
				+ ((SnabbköpState) state).getMissedCustomers() + "\t"
				+ ((SnabbköpState) state).getCustomersWhoHadToQueue() + "\t"
				+ format.format(((SnabbköpState) state).getTotalQueueTime()) + "\t"
				+ ((SnabbköpState) state).getQueueSize() + "\t" + ((SnabbköpState) state).getQueue() + "\n");
	}

	/**
	 * prints out the result of the simulation.
	 * 
	 * @param event, of the type StopEvent.
	 */
	private void printOutStopEventOnConsol(StopEvent event) {
		double avgFreeRegTime = (((SnabbköpState) state).getTotalFreeRegistersTime()
				/ ((SnabbköpState) state).getNrOfRegisters());
		double pctFreeRegTime = (avgFreeRegTime / ((SnabbköpState) state).getTimeForLastPayEvent()) * 100;
		double avgQueingTime = ((SnabbköpState) state).getTotalQueueTime()
				/ ((SnabbköpState) state).getCustomersWhoHadToQueue();

		System.out.println(format.format(event.getTime()) + "\t" + event.getEventName() + "\n");
		System.out.println("RESULTAT \n========\n");
		System.out.println("1) Av " + ((SnabbköpState) state).getTotalVisitors() + " kunder handlade "
				+ ((SnabbköpState) state).getSucessfullPurschases() + " medan "
				+ ((SnabbköpState) state).getNrOfMissedCustomers() + " missades.\n");
		System.out.println("2) Total tid " + ((SnabbköpState) state).getNrOfRegisters() + " kassor varit lediga: "
				+ format.format(((SnabbköpState) state).getTotalFreeRegistersTime())
				+ " te.\n Genomsnittlig ledig kassatid: " + format.format(avgFreeRegTime) + " te (dvs "
				+ format.format(pctFreeRegTime) + "% av tiden från öppning tills sista kunden betalat).\n");
		System.out.println("3) Total tid " + ((SnabbköpState) state).getCustomersWhoHadToQueue()
				+ " kunder tvingats köa: " + format.format(((SnabbköpState) state).getTotalQueueTime())
				+ " te.\n Genomsnittlig kötid: " + format.format(avgQueingTime) + " te.");
	}
}
