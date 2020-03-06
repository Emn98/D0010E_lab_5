package lab5.snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.State;

public class StopEvent extends Event {

	public StopEvent(State state, EventQueue queue, double stopTime) {
		super(state, queue);
		this.setExecutionTime(stopTime);
	}
	
	public void execute() {
		State state = this.getState();
		state.updateTotalRunTime(this.getTime());
		state.notifyObs(this);
		state.haltSimulation();
	}
	
	public String getEventName() {
		return "Stop";
	}
}
