package lab5.deds;

import snabbköp.event.StartEvent;
import snabbköp.event.StopEvent;

public class Simulator {
	
	private Event startEvent;
	private Event stopEvent;
	private State state;
	private EventQueue eventQueue;
	private View view;

	public Simulator(Event startEvent, Event stopEvent, State state, EventQueue eventQueue, View view) {
		//this.startEvent = startEvent;
		//this.stopEvent = stopEvent;
		this.state = state;
		this.eventQueue = eventQueue;
		eventQueue.addEvent(new StartEvent(state,eventQueue));
		
		eventQueue.addEvent(new StopEvent(state,eventQueue,10));
		state.addObserver(view);
		driver();
	}
	
	private void driver() {
		while(eventQueue.hasNext() && state.getStopValue() == false) {
			eventQueue.run();
		}
	}
}


