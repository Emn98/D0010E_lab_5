package lab5.snabbköp.event;

import lab5.deds.EventQueue;
import lab5.deds.State;
import lab5.snabbköp.state.CustomerFactory.Customer;
import lab5.snabbköp.state.SnabbköpState;

/**
 * 
 * A class for pay events.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 * 
 */
public class PayEvent extends CustomerDrivenEvents {

	/**
	 * Constructor. Saves the correct state, queue and customer to the event, and
	 * then calculates the time it takes for the customer to pay for the goods
	 * before it adds itself to the event queue. It also decreases the number of
	 * free registers.
	 * 
	 * @param state    that the event can change.
	 * @param queue    that the event will add itself and other events to.
	 * @param customer the customer that is tied to this event.
	 */
	public PayEvent(State state, EventQueue queue, Customer customer) {
		super(state, queue, customer);
		double timeToPay = ((SnabbköpState) state).calcTimeToPay();
		this.setExecutionTime(timeToPay);
		((SnabbköpState) state).decreaseNrOfFreeRegisters();
	}

	/**
	 * Updates the affected times and the total running time in state. It increases
	 * the number of available registers, decreases the number of customers
	 * currently in the store and adds one new successful purchase. If Someone or a
	 * few people is standing in line to the registers. The first customer in the
	 * line will be added to a new pay event.
	 * 
	 */
	public void execute() {
		EventQueue queue = super.getQueue();
		SnabbköpState state = (SnabbköpState) super.getState();

		double timePassedBetweenEvents = (this.getTime() - state.getCurrentRunTime());
		state.updateTotalRunTime(this.getTime());
		state.updateAffectedTimes(timePassedBetweenEvents);

		state.notifyObs(this);

		// The effects
		state.increaseNrOfFreeRegisters();
		state.decreaseCurrentVisistors();
		state.increaseSuccesfullpurchases();

		if (state.queueIsEmpty() == false) {
			Customer customer = state.getNextCustomer();
			queue.addEvent(new PayEvent(state, queue, customer));
		}
	}

	/**
	 * 
	 * Returns the name of the event.
	 * 
	 * @return The name of the event.
	 */
	public String getEventName() {
		return "Betalning";
	}
}
