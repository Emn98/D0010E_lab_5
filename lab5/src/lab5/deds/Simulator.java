package lab5.deds;

import lab5.snabbköp.event.StartEvent;
import lab5.snabbköp.event.StopEvent;

public class Simulator {
	
	private State state;
	private EventQueue queue;
	private View view;
	
	public Simulator(State state, EventQueue queue, View view) {
		this.state = state;
		this.queue = queue;
		this.view = view;
		
		queue.addEvent(new StartEvent(state,queue));
        queue.addEvent(new StopEvent(state,queue,999.00));
        
        state.addObserver(view);
        driver();
    }

    private void driver() {
        while(queue.hasNext() && state.getStopValue() == false) {
            queue.run();
        }
    }
		
}

