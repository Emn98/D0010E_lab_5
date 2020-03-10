package lab5.deds;

/**
 * 
 * A class to represent the general stop event that will work for every type of
 * simulator.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 * 
 *
 */

public class GeneralStopEvent extends Event {

	/**
	 * Constructor. Adds itself to the event queue to signal at what time the
	 * simulation will be stopped.
	 * 
	 * @param state    that the event can change.
	 * @param queue    that the event will be added to.
	 * @param stopTime the time the simulation will be halted.
	 */
	public GeneralStopEvent(State state, EventQueue queue, double stopTime) {
		super(state, queue);
		super.setExecutionTime(stopTime);
		queue.addEvent(this);
	}

	/**
	 * Updates the simulation run time and then halts the entire simulation.
	 * 
	 */
	public void execute() {
		State state = this.getState();
		state.updateTotalRunTime(this.getTime());
		state.notifyObs(this);
		state.haltSimulation();
	}
}
