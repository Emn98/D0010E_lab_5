package snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.State;
import snabbköp.state.SnabbköpState;
import snabbköp.state.CustomerFactory.Customer;

public class PayEvent extends Event {
	
	private Customer customer;

	public PayEvent(State state, EventQueue queue, Customer customer) {
		super(state, queue);
		this.customer = customer;
		this.setExecutionTime(((SnabbköpState) state).calcTimeToPay());
	}

	public void execute() {
		State state = this.getState();
		EventQueue queue = this.getQueue();
		state.updateTotalRunTime(this.getTime());
		state.notifyObs(this);
		((SnabbköpState) state).addCustomerToQueue(customer);
	}
	
	public String getEventNamn() {
		return "PayEvent";
	}
	
	
}
