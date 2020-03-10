package lab5.deds;

/**
 * 
 * A general class for events.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 *
 */
public class Event {

	private State state;
	private EventQueue queue;
	private double timeWhenExecuted;
	
	/**
	 * 
	 * @param state that the event can change.
	 * @param queue that the event will be added to.
	 */
	public Event(State state, EventQueue queue) {
		this.state = state;
		this.queue = queue;
	}
	
	/**
	 * Executes the event.
	 */
	public void execute() {}

	/**
	 * Sets the time that the event is going to get executed.
	 * 
	 * @param time to execute.
	 */
	public void setExecutionTime(double time) {
		this.timeWhenExecuted = time;
	}
	
	/**
	 * 
	 * @return time when the event is due to be executed.
	 */
	public double getTime() {
		return timeWhenExecuted;
	}
	
	/**
	 * 
	 * @return the state that the event is going to change.
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * 
	 * @return the queue that the event is going to get added to.
	 */
	public EventQueue getQueue() {
		return queue;
	}
	
	/**
	 * 
	 * @return the name of the event
	 */
	public String getEventName() {
		return "Event";
	}

}
