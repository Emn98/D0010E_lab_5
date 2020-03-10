package lab5.snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.GeneralStartEvent;
import lab5.deds.State;
import lab5.snabbköp.state.CustomerFactory.Customer;
import lab5.snabbköp.state.SnabbköpState;

/**
 * 
 * A class for the specialized start event.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 * 
 */
public class StartEvent extends GeneralStartEvent {

	/**
	 * 
	 * Constructor. Saves the correct state and queue. .
	 * 
	 * @param state that the event can change.
	 * @param queue that the event will be added to.
	 */
	public StartEvent(State state, EventQueue queue) {
		super(state, queue);
	}

	/**
	 * 
	 * Creates a new customer arrival event and a new closing event and adds them to
	 * the event queue.
	 * 
	 */
	public void execute() {
		SnabbköpState state = (SnabbköpState) this.getState();
		EventQueue queue = this.getQueue();

		state.notifyObs(this);
		Customer newCustomer = state.makeNewCustomer();
		queue.addEvent(new CustomerArrivalEvent(state, queue, newCustomer));
		queue.addEvent(new ClosingEvent(state, queue));
	}

	/**
	 * 
	 * Returns the name of the event.
	 * 
	 * @return The name of the event.
	 */
	public String getEventName() {
		return "Start";
	}
}
