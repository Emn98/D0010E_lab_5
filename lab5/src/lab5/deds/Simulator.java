package lab5.deds;

import lab5.snabbköp.event.StartEvent;
import lab5.snabbköp.event.StopEvent;

/**
 * 
 * Combines the View, EventQueue and the State to start the simulator.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 *
 */
public class Simulator {

	private State state;
	private EventQueue queue;
	private View view;
	private StartEvent start;
	private StopEvent stop;
	
	/**
	 * 
	 * Creates the simulator.
	 * 
	 * @param state to use.
	 * @param queue to use.
	 * @param view to use.
	 * @param start to use.
	 * @param stop to use.
	 */
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

	/**
	 * The main driver that keeps the program running.
	 */
	private void driver() {
		while (queue.hasNext() && state.getRunningValue() == true) {
			queue.run();
		}
	}
}
