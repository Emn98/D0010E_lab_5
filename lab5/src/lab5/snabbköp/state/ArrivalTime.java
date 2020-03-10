package lab5.snabbköp.state;

import lab5.random.ExponentialRandomStream;

/**
 *
 * Sets the time for a customer arrivalevent.
 * 
 * @author Karl Näslund, EmilNyberg and Isak Lundmark
 *
 */
public class ArrivalTime {
	
	private SnabbköpState state;
	private ExponentialRandomStream expRandStream;

	/**
	 * 
	 * @param state Takes a snabbköpstate.
	 * @param lambda Takes a double that desides the arrivalspeed.
	 * @param seed Takes a seed.
	 */
	
	public ArrivalTime(SnabbköpState state, double lambda, long seed) {
		this.state = state;
		expRandStream = new ExponentialRandomStream(lambda, seed);
	}
	
	/**
	 * 
	 * @return Returns the time for when the next customer is to arrive.
	 */
	
	public double calcNextCustArrival() {
		return state.getCurrentRunTime() + expRandStream.next();
	}	
}
