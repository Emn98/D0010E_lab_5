package lab5.deds;

public class Event {
	
	private State state;
	private EventQueue queue;
	private double timeWhenExecuted;

	public Event(State state, EventQueue queue){
		this.state = state;
		this.queue = queue;
	}
	
	public void execute() {}
	
	public void setExecutionTime(double time) {
		this.timeWhenExecuted = time;
	}

	public double getTime() {
		return timeWhenExecuted;
	}
	
	public State getState() {
		return state;
	}
	public EventQueue getQueue() {
		return queue;
	}
	
	public String getEventNamn() {
		return "Event";
	}
}
