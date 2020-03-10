package lab5.deds;

/**
 * 
 * A class to represent the general start event that will work for every type of
 * simulator.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 * 
 *
 */
public class GeneralStartEvent extends Event {

	/**
	 * 
	 * Constructor. Starts the entire simulation on the given state and sets it's
	 * execution time to 0;
	 * 
	 * 
	 * @param state that the event can change.
	 * @param queue that the event will be added to.
	 */
	public GeneralStartEvent(State state, EventQueue queue) {
		super(state, queue);
		state = super.getState();
		state.startSimulation();
		this.setExecutionTime(0);
	}
}
