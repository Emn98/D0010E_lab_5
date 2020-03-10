package lab5.snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.State;
import lab5.snabbköp.state.CustomerFactory.Customer;

/**
 * 
 * A more general class that will be inherited to every event that is driven by
 * an customers action.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 * 
 */
public class CustomerDrivenEvents extends Event {

	private Customer customer;

	/**
	 * Constructor. Saves the state,queue and the customer to the event.
	 * 
	 * @param state    that the event can change.
	 * @param queue    that the event can add itself and other events to.
	 * @param customer that will get attached to the event.
	 */
	public CustomerDrivenEvents(State state, EventQueue queue, Customer customer) {
		super(state, queue);
		this.customer = customer;
	}

	/**
	 * 
	 * Gets the id of the customer that is attached to this event.
	 * 
	 * @return an customers id.
	 */
	public int getCustomerid() {
		return customer.getCustomerId();
	}

	/**
	 * Gets the customer that is attached to this event.
	 * 
	 * @return an customer.
	 */
	public Customer getCustomer() {
		return customer;
	}
}
