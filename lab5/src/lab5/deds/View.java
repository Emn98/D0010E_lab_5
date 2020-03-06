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
		printOutOnConsol((Event) arg);
		
	}
	
	private void printOutOnConsol(Event event) {
		System.out.println("Event : " + event.getEventName() + " " + state.getCurrentRunTime() + " "
                + ((SnabbköpState) state).getQueue() + ((SnabbköpState) state).getTotalVisitors()) ;
	}

}
