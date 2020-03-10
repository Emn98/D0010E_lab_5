package lab5.snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.State;
import snabbköp.state.*;

/**
 * Creates an event for when the store is to close.
 * 
 * @author Karl Näslund, Emil Nyberg and Isak Lundmark.
 *
 */
public class ClosingEvent extends Event {

	/**
	 * 
	 * Creates a closing event.
	 * 
	 * @param state Takes a state.
	 * @param queue Takes an eventqueue.
	 */
	public ClosingEvent(State state, EventQueue queue) {
		super(state, queue);
		double closingTime = ((SnabbköpState) state).getStoreClosingTime();
		this.setExecutionTime(closingTime);
	}

	/**
	 * This method closes the store and updates the affected times.
	 */
	public void execute() {
		State state = this.getState();

		double timePassedBetweenEvents = (this.getTime() - state.getCurrentRunTime());

		state.updateTotalRunTime(this.getTime());
		state.notifyObs(this);

		((SnabbköpState) state).closeTheStore();
		((SnabbköpState) state).updateAffectedTimes(timePassedBetweenEvents);
	}

	/**
	 * Returns a string of the events name.
	 */
	public String getEventName() {
		return "Stänger";
	}
}
