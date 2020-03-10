package lab5.snabbköp.state;

import lab5.random.UniformRandomStream;

/**
 * 
 * Creates a time that decides how long it will take to pay.
 * 
 * @author Karl Näslund, Emil Nyberg and Isak Lundmark.
 *
 */
public class TimeToPay {
	
	private SnabbköpState state;
	private UniformRandomStream uniRandStream;
	
	/**
	 * 
	 * Creates the time.
	 * 
	 * @param state Takes a state.
	 * @param lower Takes the lowest time it can take to pay.
	 * @param upper Takes the longest time it can take to pay.
	 * @param seed Takes a seed.
	 */
	TimeToPay(SnabbköpState state, double lower, double upper, long seed){
		this.state = state;
		uniRandStream = new UniformRandomStream(lower, upper, seed);
	}
	
	/**
	 * 
	 * @return Returns the time that it will take to pay.
	 */
	public double calcTimeToPayForGoods() {
		return state.getCurrentRunTime() + uniRandStream.next();
	}
}
