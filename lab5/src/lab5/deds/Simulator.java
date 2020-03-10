package lab5.deds;

import lab5.snabbköp.event.StartEvent;
import lab5.snabbköp.event.StopEvent;

public class Simulator {

	private State state;
	private EventQueue queue;
	private View view;
	private StartEvent start;
	private StopEvent stop;

	public Simulator(State state, EventQueue queue, View view, StartEvent start, StopEvent stop) {
		this.state = state;
		this.queue = queue;
		this.view = view;
		this.start = start;
		this.stop = stop;

		queue.addEvent(start);
		queue.addEvent(stop);
		
		if (view != null) {
			state.addObserver(view);
		}
		driver();
	}

	private void driver() {
		while (queue.hasNext() && state.getStopValue() == false) {
			queue.run();
		}
	}
}
