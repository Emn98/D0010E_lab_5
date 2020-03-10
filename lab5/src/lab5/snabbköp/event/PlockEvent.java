package lab5.snabbköp.event;

import lab5.deds.EventQueue;
import lab5.deds.State;
import lab5.snabbköp.state.CustomerFactory.Customer;
import lab5.snabbköp.state.SnabbköpState;

/**
 * 
 * A class for plock events.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 * 
 */
public class PlockEvent extends CustomerDrivenEvents {

	/**
	 * Constructor. Saves the correct state, queue and customer to the event, and
	 * then calculates the time it takes for the customer to gather the goods before
	 * it adds itself to the event queue.
	 * 
	 * @param state    that the event can change.
	 * @param queue    that the event can add itself aswell as other events to.
	 * @param customer that is tied to this event.
	 */
	public PlockEvent(State state, EventQueue queue, Customer customer) {
		super(state, queue, customer);
		double plockTime = ((SnabbköpState) state).calcTimeToGatherTheGoods();
		this.setExecutionTime(plockTime);
	}

	/**
	 * Updates the affected times and the total running time in state. If there is a
	 * free registers a new pay event with the customer will be created. However if
	 * there is no free registers available, the customer will be added to the
	 * register queue.
	 * 
	 */
	public void execute() {
		SnabbköpState state = (SnabbköpState) this.getState();
		EventQueue queue = this.getQueue();

		double timePassedBetweenEvents = (this.getTime() - state.getCurrentRunTime());
		state.updateAffectedTimes(timePassedBetweenEvents);
		state.updateTotalRunTime(this.getTime());

		state.notifyObs(this);

		// the effects.
		if (state.isFreeRegisters() == true) {
			queue.addEvent(new PayEvent(state, queue, super.getCustomer()));
		} else {
			state.increaseCustomersWhoHadToQueue();
			state.addCustomerToRegisterQueue(this.getCustomer());
		}
	}

	/**
	 * 
	 * Returns the name of the event.
	 * 
	 * @return The name of the event.
	 */
	public String getEventName() {
		return "Plock    ";
	}
}
