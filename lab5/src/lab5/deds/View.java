package lab5.deds;

import java.util.Observable;
import java.util.Observer;

import lab5.snabbköp.state.SnabbköpState;

public class View implements Observer{
	
	private State state;

	public View(State state) {
		this.state = state;
	}
	
	public void update(Observable o, Object arg) {
		
	}
	
	public State getState() {
		return state;
	}
}
