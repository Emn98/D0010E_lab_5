package snabbköp.state;

import lab5.random.UniformRandomStream;

public class TimeToPay {
	
	private SnabbköpState state;
	private UniformRandomStream uniRandStream;
	
	TimeToPay(SnabbköpState state, double lower, double upper, long seed){
		this.state = state;
		uniRandStream = new UniformRandomStream(lower, upper, seed);
	}
	
	public double calcTimeToPayForGoods() {
		return state.getCurrentRunTime() + uniRandStream.next();
	}
}
