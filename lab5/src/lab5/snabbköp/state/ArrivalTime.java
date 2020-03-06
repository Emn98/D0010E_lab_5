package lab5.snabbköp.state;

import lab5.random.ExponentialRandomStream;

public class ArrivalTime {

	private SnabbköpState state;
	private ExponentialRandomStream expRandStream;

	public ArrivalTime(SnabbköpState state, double lambda, long seed) {
		this.state = state;
		expRandStream = new ExponentialRandomStream(lambda, seed);
	}

	public double calcNextCustArrival() {
		return state.getCurrentRunTime() + expRandStream.next();
	}
}
