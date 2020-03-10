package lab5.snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.GeneralStopEvent;
import lab5.deds.State;

/**
 * 
 * A class for the specialized stop event.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 * 
 */
public class StopEvent extends GeneralStopEvent {

	/**
	 * Constructor. Saves the correct state, queue and stoptime.
	 * 
	 * @param state    that the event can change.
	 * @param queue    that the event can add itself to and other events.
	 * @param stopTime the time the event will halt.
	 */
	public StopEvent(State state, EventQueue queue, double stopTime) {
		super(state, queue, stopTime);
	}

	/**
	 * Updates the total run time and the halts the simulation.
	 */
	public void execute() {
		State state = super.getState();
		state.updateTotalRunTime(this.getTime());
		state.notifyObs(this);
		state.haltSimulation();
	}

	/**
	 * 
	 * Returns the name of the event.
	 * 
	 * @return The name of the event.
	 */
	public String getEventName() {
		return "Stop    ";
	}
}
