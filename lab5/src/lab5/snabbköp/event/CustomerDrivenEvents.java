package lab5.snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.State;
import lab5.snabbköp.state.CustomerFactory.Customer;

public class CustomerDrivenEvents extends Event {
	
	private Customer customer;

	public CustomerDrivenEvents(State state, EventQueue queue, Customer customer) {
		super(state, queue);
		this.customer = customer;
	}
	
	public int getCustomerid() {
		return customer.getCustomerId();
	}
	
	public Customer getCustomer() {
		return customer;
	}
}
