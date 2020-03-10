package lab5.snabbköp.state;

import lab5.random.UniformRandomStream;

/**
 * Creates a time for gather goods.
 * 
 * @author Karl Näslund, Emil Nyberg and Isak Lundmark.
 *
 */
public class TimeToGatherGoods {
	
	private SnabbköpState state;
	private UniformRandomStream uniRandStream;
	
	/**
	 * 
	 * Creates the time that it will take to gather goods.
	 * 
	 * @param state Takes a state.
	 * @param lower Takes the lowes time to gather goods.
	 * @param upper Takes the higest time to gather goods.
	 * @param seed Takes a seed.
	 */
	TimeToGatherGoods(SnabbköpState state, double lower, double upper, long seed){
		this.state = state;
		uniRandStream = new UniformRandomStream(lower, upper, seed);
	}
	
	/**
	 * 
	 * @return Returns the time it will take to gather goods.
	 */
	public double calcTimeToGatherGoods() {
		return state.getCurrentRunTime() + uniRandStream.next(); 
	}
	

}
