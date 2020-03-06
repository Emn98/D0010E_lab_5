package lab5.snabbköp.state;

import lab5.random.UniformRandomStream;

public class TimeToGatherGoods {
	
	private SnabbköpState state;
	private UniformRandomStream uniRandStream;
	
	
	TimeToGatherGoods(SnabbköpState state, double lower, double upper, long seed){
		this.state = state;
		uniRandStream = new UniformRandomStream(lower, upper, seed);
	}
	
	public double calcTimeToGatherGoods() {
		return state.getCurrentRunTime() + uniRandStream.next(); 
	}
}