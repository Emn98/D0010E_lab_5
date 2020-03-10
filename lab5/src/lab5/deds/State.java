package lab5.deds;

import java.util.Observable;

/**
 * A general class for the state.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 *
 */
public class State extends Observable {

	private double totalTimeRunning = 0;
	private boolean stopSimulation = false;

	public State() {
	}

	/**
	 * Get the running state of the State
	 * 
	 * @return true if the simulation is halted. Else false.
	 */
	public boolean getStopValue() {
		return stopSimulation;
	}

	/**
	 * How long the simulator has been running
	 * 
	 * @return how long the simulator has been running.
	 */
	public double getCurrentRunTime() {
		return totalTimeRunning;
	}

	/**
	 * Halt the halts the simulator.
	 */
	public void haltSimulation() {
		stopSimulation = true;
	}

	/**
	 * Updates the total running time
	 * 
	 * @param the new time the simulator has been running.
	 */
	public void updateTotalRunTime(double newTotalRunningTime) {
		totalTimeRunning = newTotalRunningTime;
	}

	/**
	 * setChanged and notifyObservers
	 * 
	 * @param an event to setChanged and notifyObservers to.
	 */
	public void notifyObs(Event event) {
		setChanged();
		notifyObservers(event);
	}

}
