package lab5.snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.State;
import lab5.snabbköp.state.CustomerFactory.Customer;
import lab5.snabbköp.state.SnabbköpState;

public class StartEvent extends Event{

	public StartEvent(State state, EventQueue queue) {
		super(state, queue);
		this.setExecutionTime(0);
	}
	
	public void execute() {
		State state = this.getState();
		EventQueue queue = this.getQueue();
		
		state.notifyObs(this);
		Customer newCustomer = ((SnabbköpState) state).makeNewCustomer();
		queue.addEvent(new CustomerArrivalEvent(state, queue, newCustomer));
		queue.addEvent(new ClosingEvent(state,queue));
	}
	
	public String getEventName() {
		return "Start";
	}
}
