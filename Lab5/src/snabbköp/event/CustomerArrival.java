package snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.State;
import snabbköp.state.CustomerFactory;
import snabbköp.state.CustomerFactory.Customer;
import snabbköp.state.SnabbköpState;

public class CustomerArrival extends Event {
	
	public CustomerArrival(State state, EventQueue queue) {
		super(state,queue);
		double tid = ((SnabbköpState) state).calcNextCustomerArrival();
		this.setExecutionTime(tid);
	}
	
	public void execute() {
		State state = this.getState();
		EventQueue queue = this.getQueue();
		state.updateTotalRunTime(this.getTime());
		state.notifyObs(this);
		if(((SnabbköpState) state).isClosed() == true) {
			return;
		}
		if(((SnabbköpState) state).isFullStore() == true) {
			((SnabbköpState) state).increaseMissedCustomer();
		}else {
			Customer newCustomer = ((SnabbköpState) state).makeACustomer();
			((SnabbköpState) state).increaseCurrentVisitors();
			((SnabbköpState) state).increaseTotalVisitors();
			queue.addEvent(new PlockEvent(state,queue,newCustomer));
		}
		queue.addEvent(new CustomerArrival(state,queue));
		
	}
	
	public String getEventNamn() {
		return "CustomerArrival";
	}
	
}
