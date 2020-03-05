package snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.State;
import snabbköp.state.SnabbköpState;

public class Closing extends Event {
	
	Closing(State state, EventQueue queue) {
		super(state, queue);
		this.setExecutionTime(((SnabbköpState) state).getClosingTime());
		queue.addEvent(this);
	}
	
	public void execute() {
		State state = this.getState();
		EventQueue queue = this.getQueue();
		state.updateTotalRunTime(this.getTime());
		state.notifyObs(this);
		((SnabbköpState) state).closeStore();
	}
	
	public String getEventNamn() {
		return "Closing";
	}
	
}
