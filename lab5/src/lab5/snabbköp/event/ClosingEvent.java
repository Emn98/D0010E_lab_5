package lab5.snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.State;
import lab5.snabbköp.state.*;

/**
 * A class to create an closing event for the store.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 *
 */
public class ClosingEvent extends Event {

	/**
	 * Constructor. Saves the state and queue before it inserts itself to the event
	 * queue to execute at the time the store closes.
	 * 
	 * @param state that the event can change.
	 * @param queue that the event can add itself and other events to.
	 */
	public ClosingEvent(State state, EventQueue queue) {
		super(state, queue);
		double closingTime = ((SnabbköpState) state).getStoreClosingTime();
		this.setExecutionTime(closingTime);
	}

	/**
	 * Updates the affected times and then closes the store.
	 */
	public void execute() {
		SnabbköpState state = (SnabbköpState) super.getState();

		double timePassedBetweenEvents = (this.getTime() - state.getCurrentRunTime());
		state.updateAffectedTimes(timePassedBetweenEvents);

		state.updateTotalRunTime(this.getTime());
		state.notifyObs(this);
		state.closeTheStore();
	}

	/**
	 * 
	 * Returns the name of the event.
	 * 
	 * @return The name of the event.
	 */
	public String getEventName() {
		return "Stänger   ";
	}
}
