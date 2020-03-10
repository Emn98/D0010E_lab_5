package lab5.deds;

import java.util.Observable;
import java.util.Observer;
import lab5.snabbköp.state.SnabbköpState;

/**
 * 
 * A general class for the view.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 *
 */
public class View implements Observer {

	private State state;

	/**
	 * Creates a view for a sate.
	 * 
	 * @param state to create a view for.
	 */
	public View(State state) {
		this.state = state;
	}

	/**
	 * Creates an update method for inheritance.
	 * 
	 */
	public void update(Observable o, Object arg) {

	}

	/**
	 * Gets a state
	 * 
	 * @return the viewable state.
	 */
	public State getState() {
		return state;
	}
}
