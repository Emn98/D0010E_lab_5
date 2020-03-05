package snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.State;
import snabbköp.state.SnabbköpState;

public class StopEvent extends Event {
	
	public StopEvent(State state, EventQueue queue, double timeWhenStopped) {
		super(state, queue);
		this.setExecutionTime(timeWhenStopped);
	}
	
	public void execute() {
		State state = this.getState();
		state.updateTotalRunTime(this.getTime());
		state.notifyObs(this);
		state.haltSimulation();
		state.updateTotalRunTime(this.getTime());
	}
	
	public String getEventNamn() {
		return "StopEvent";
	}
}
