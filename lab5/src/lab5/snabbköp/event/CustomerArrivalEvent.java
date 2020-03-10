package lab5.snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.State;
import lab5.snabbköp.state.CustomerFactory.Customer;
import lab5.snabbköp.state.SnabbköpState;

/**
 * 
 * A class that creates customer arrival events.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 * 
 */
public class CustomerArrivalEvent extends CustomerDrivenEvents {

	/**
	 * Constructor. Saves the correct state, queue and customer to the event, and
	 * then calculates the time for the arrival of the customer before it adds
	 * itself to the event queue.
	 * 
	 * @param state    that the event can change.
	 * @param queue    that the event can add itself and other events to.
	 * @param customer the customer that is tied to this event.
	 */
	public CustomerArrivalEvent(State state, EventQueue queue, Customer customer) {
		super(state, queue, customer);
		double arrivalTime = ((SnabbköpState) state).calcNextCustomerArrivalTime();
		this.setExecutionTime(arrivalTime);
	}

	/**
	 * Updates the affected times and the total running time in state. If the store
	 * is open the customer will try to enter the store. If the store is full, it
	 * will have missed a customer. Otherwise it will add a new plock event to the
	 * event queue with the customer. In the end it also adds an new customer
	 * arrival event to the event queue.
	 * 
	 */
	public void execute() {
		SnabbköpState state = (SnabbköpState) super.getState();
		EventQueue queue = super.getQueue();

		double timePassedBetweenEvents = (this.getTime() - state.getCurrentRunTime());
		state.updateAffectedTimes(timePassedBetweenEvents);
		state.updateTotalRunTime(this.getTime());

		state.notifyObs(this);

		// The effects
		if (state.isClosed() == true) {
			return;
		}

		state.increaseTotalVisitors();
		if (state.enoughRoomInStore() == false) {
			state.increaseMissedCustomer();
		} else {
			state.increaseCurrentVisitors();
			queue.addEvent(new PlockEvent(state, queue, super.getCustomer()));
		}
		queue.addEvent(new CustomerArrivalEvent(state, queue, state.makeNewCustomer()));
	}

	/**
	 * 
	 * Returns the name of the event.
	 * 
	 * @return The name of the event.
	 */
	public String getEventName() {
		return "Ankomst   ";
	}
}
