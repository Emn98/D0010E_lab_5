package lab5.main;

import java.util.Random;

import lab5.deds.EventQueue;
import lab5.deds.Simulator;
import lab5.deds.State;
import lab5.snabbköp.event.StartEvent;
import lab5.snabbköp.event.StopEvent;
import lab5.snabbköp.state.SnabbköpState;

/**
 * 
 * Main program that finds the optimal amount of registers.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 *
 */
public class Optimize {

	public static final int M = 100;
	public static final double L = 50.0;

	public static final double LOW_COLLECTION_TIME = 0.45d;
	public static final double HIGH_COLLECTION_TIME = 0.65d;

	public static final double LOW_PAYMENT_TIME = 0.2d;
	public static final double HIGH_PAYMENT_TIME = 0.3d;

	public static final int SEED = 42;
	public static final double END_TIME = 20.0d;
	public static final double STOP_TIME = 999.0d;

	private int kassor = 1;
	private State state;
	private int ominalKassa = Integer.MAX_VALUE;
	
	
	/**
	 * Runs the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Optimize a = new Optimize();

		a.printGetOptimalKassor();
		a.printGetOptimalRandomKassor();

	}

	/**
	 * 
	 * Runs a simulator.
	 * 
	 * @param seed     to try.
	 * @param Ammaount of kassor to try.
	 * @return the state the state.
	 */
	public State run(long seed, int kassor) {
		EventQueue que = new EventQueue();
		SnabbköpState state = new SnabbköpState(seed, L, LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME, LOW_COLLECTION_TIME,
				HIGH_COLLECTION_TIME, kassor, M, END_TIME);
		StartEvent start = new StartEvent(state, que);
		StopEvent stop = new StopEvent(state, que, STOP_TIME);
		new Simulator(state, que, null, start, stop);
		return state;
	}

	/**
	 * Calculates the optimal amount of register.
	 * 
	 * @param seed to try.
	 * @return the optimal amount of kassor.
	 */
	public int getOptimalKassor(long seed) {
		State bestState = run(seed, kassor);
		for (int i = 1; i <= ((SnabbköpState) bestState).getMaxNrOfCustomersInStore(); i++) {
			State testState = run(seed, kassor);

			if (((SnabbköpState) testState).getNrOfMissedCustomers() < ((SnabbköpState) bestState)
					.getNrOfMissedCustomers()) {
				bestState = testState;
			}

			if (((SnabbköpState) bestState).getNrOfMissedCustomers() == 0) {
				break;
			}
			kassor++;
		}
		kassor = 1;
		this.state = bestState;
		return ((SnabbköpState) bestState).getNrOfRegisters();
	}

	/**
	 * 
	 * Finds the optimal amount of registers required.
	 * 
	 * @param seed that is used to generate other seeds.
	 */
	public int getOptimalRandomKassor(long metod_3Seed) {
		Random rand = new Random();
		rand.setSeed(metod_3Seed);

		int i = 0;

		while (i < 100) {
			long seed = rand.nextLong();
			int nrOfReg = getOptimalKassor(seed);
			if (nrOfReg < ominalKassa) {
				ominalKassa = nrOfReg;
				i = 0;
			}
			i++;
		}
		return ominalKassa;
	}

	/**
	 * Runs getOptimalKassor and print its result.
	 */
	public void printGetOptimalKassor() {
		System.out.println(getOptimalKassor(SEED) + " kassor är den optimal mängden av kassor och det ger "
				+ ((SnabbköpState) state).getMissedCustomers() + " missade kunder.");
	}

	/**
	 * Runs getOptimalRandomKassor and print its result.
	 */
	public void printGetOptimalRandomKassor() {
		System.out.println(getOptimalRandomKassor(SEED) + " kassor är den optimal mängden av kassor och det ger "
				+ ((SnabbköpState) state).getMissedCustomers() + " missade kunder.");
	}

}
