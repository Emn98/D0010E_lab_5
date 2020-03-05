package snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.State;
import snabbköp.state.SnabbköpState;

public class StartEvent extends Event {
	


	public StartEvent(State state, EventQueue queue) {
		super(state, queue);
		this.setExecutionTime(0.00);
	}
	
	public void execute() {
		State state = this.getState();
		EventQueue queue = this.getQueue();
		state.updateTotalRunTime(0.00);
		state.notifyObs(this);
		queue.addEvent(new CustomerArrival(state,queue));
	}
	
	public String getEventNamn() {
		return "Start";
	}
}
