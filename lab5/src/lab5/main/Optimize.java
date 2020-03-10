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

	private int kassor = 1;
	private State state;
	private int ominalKassa = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Optimize a = new Optimize();
		a.metod_3(13);
	}

	private State metod_1(long seed, int kassor) {
		EventQueue que = new EventQueue();
		SnabbköpState state = new SnabbköpState(seed, 10.0, 0.35, 0.6, 0.6, 0.9, kassor, 7, 8.00);
		StartEvent start = new StartEvent(state, que);
		StopEvent stop = new StopEvent(state, que, 999.00);
		new Simulator(state, que, null, start, stop);
		return state;
	}

	private int metod_2(long seed) {
		State bestState = metod_1(seed, kassor);
		for (int i = 1; i <= ((SnabbköpState) bestState).getMaxNrOfCustomersInStore(); i++) {
			State testState = metod_1(seed, kassor);

			if (((SnabbköpState) testState).getNrOfMissedCustomers() < ((SnabbköpState) bestState)
					.getNrOfMissedCustomers()) {
				bestState = testState;
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
	public void metod_3(long metod_3Seed) {
		Random rand = new Random();
		rand.setSeed(metod_3Seed);

		int i = 0;

		while (i < 100) {
			long seed = rand.nextLong();
			int nrOfReg = metod_2(seed);
			if (nrOfReg < ominalKassa) {
				ominalKassa = nrOfReg;
				i = 0;
			}
			i++;
		}
		System.out.println(ominalKassa + " kassor är den opimala mängden av kassor och det ger "
				+ ((SnabbköpState) state).getMissedCustomers() + " missade kunder.");

	}

}
