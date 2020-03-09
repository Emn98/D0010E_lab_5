package lab5.main;

import java.util.Random;

import lab5.deds.EventQueue;
import lab5.deds.Simulator;
import lab5.deds.State;
import lab5.snabbköp.state.SnabbköpState;
import lab5.snabbköp.view.SnabbköpsView;

public class Optimize {

	private int kassor = 1;
	
	private long metod_3Seed = 1234;
	private long seed = 1234;
	private State state;
	private int ominalKassa = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Optimize a = new Optimize();
		a.metod_3();

	}

	public State metod_1() {
		EventQueue que = new EventQueue();
		SnabbköpState state = new SnabbköpState(seed, 100.0, 2.0, 3.0, 0.5, 1.0, kassor, 5, 10.00);
		new Simulator(state, que, null);
		return state;
	}

	public int metod_2() {
		this.state = metod_1();
		for (int i = 1; i < ((SnabbköpState) state).getMaxNrOfCustomersInStore(); i++) {
			State temp = metod_1();
			if (((SnabbköpState) temp).getNrOfMissedCustomers() < ((SnabbköpState) state).getNrOfMissedCustomers()) {
				this.state = temp;
			}
			kassor++;
		}
		kassor = 1;
		return ((SnabbköpState) state).getNrOfRegisters();
	}

	public void metod_3() {
		Random rand = new Random();
		rand.setSeed(metod_3Seed);

		int i = 0;

		while (i < 100) {
//			this.seed = rand.nextLong();
			this.seed = 1234;
			int temp = metod_2();
			if (temp < ominalKassa) {
				ominalKassa = temp;
				i = 0;
			}
			i++;
		}
		System.out.println(((SnabbköpState) state).getMissedCustomers());
		System.out.println(((SnabbköpState) state).getNrOfRegisters());
		System.out.println(ominalKassa);
	}

}
