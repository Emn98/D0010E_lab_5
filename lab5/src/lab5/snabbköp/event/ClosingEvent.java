package lab5.snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.State;
import lab5.snabbköp.state.*;

public class ClosingEvent extends Event {

	public ClosingEvent(State state, EventQueue queue) {
		super(state, queue);
		double closingTime = ((SnabbköpState) state).getStoreClosingTime();
		this.setExecutionTime(closingTime);
	}
	
	public void execute() {
		State state = this.getState();
		
		double timePassedBetweenEvents = (this.getTime() - state.getCurrentRunTime());
		
		state.updateTotalRunTime(this.getTime());
		state.notifyObs(this);
		
		((SnabbköpState) state).closeTheStore();
		((SnabbköpState) state).updateAffectedTimes(timePassedBetweenEvents);
	}
	
	public String getEventName() {
		return "Stänger";
	}
}