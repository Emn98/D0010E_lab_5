package snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.State;
import snabbköp.state.CustomerFactory.Customer;
import snabbköp.state.SnabbköpState;

public class PlockEvent extends Event {

	private Customer customer;

	
	public PlockEvent(State state, EventQueue queue, Customer customer) {
		super(state, queue);
		this.customer = customer;
		this.setExecutionTime(((SnabbköpState) state).calcTimeToGatherTheGoods());
	}

	public void execute() {
		State state = this.getState();
		EventQueue queue = this.getQueue();
		state.updateTotalRunTime(this.getTime());
		state.notifyObs(this);
		queue.addEvent(new PayEvent(state,queue,customer));
	}
	
	public String getEventNamn() {
		return "PlockEvent";
	}
}
