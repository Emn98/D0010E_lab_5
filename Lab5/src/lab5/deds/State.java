package lab5.deds;

import java.util.Observable;

public class State extends Observable {
	
	private double totalTimeRunning = 0;
	private boolean stopSimulation = false;
	
	public State(){
	}
	
	public boolean getStopValue() {
		return stopSimulation;
	}
	
	public double getCurrentRunTime() {
		return totalTimeRunning;
	}
	
	public void haltSimulation() {
		stopSimulation = true;
	}
	
	public void updateTotalRunTime(double newTime) {
		totalTimeRunning = newTime;
	}
	
	public void notifyObs(Event event) {
		setChanged();
		notifyObservers(event);
	}	
}
