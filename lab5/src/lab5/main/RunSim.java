package lab5.main;

import lab5.deds.EventQueue;
import lab5.deds.Simulator;
import lab5.snabbköp.event.StartEvent;
import lab5.snabbköp.event.StopEvent;
import lab5.snabbköp.state.SnabbköpState;
import lab5.snabbköp.view.SnabbköpsView;

/**
 * 
 * Runs the simulator.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 *
 */
public class RunSim {

	public static final int M = 5;
	public static final double L = 1;
	public static final int N = 2;

	public static final double LOW_COLLECTION_TIME = 0.6d;
	public static final double HIGH_COLLECTION_TIME = 0.9d;

	public static final double LOW_PAYMENT_TIME = 0.35d;
	public static final double HIGH_PAYMENT_TIME = 0.6d;

	public static final int SEED = 13;
	public static final double END_TIME = 8.0d;
	public static final double STOP_TIME = 999.0d;

	/**
	 * 
	 * Runs the simulator.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue que = new EventQueue();
		SnabbköpState state = new SnabbköpState(SEED, L, LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, LOW_COLLECTION_TIME,
				HIGH_COLLECTION_TIME, N, M, END_TIME);
		SnabbköpsView view = new SnabbköpsView(state);
		StartEvent start = new StartEvent(state, que);
		StopEvent stop = new StopEvent(state, que, STOP_TIME);
		new Simulator(state, que, null, start, stop);
	}
}
