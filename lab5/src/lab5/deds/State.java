package lab5.deds;

import java.util.Observable;

/**
 * 
 * A class to represent the general state that can be used by any type of
 * simulation.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 * 
 */

public class State extends Observable {

	private double totalTimeRunning = 0;
	private boolean SimulationIsRunning = false;

	public State() {
	}

	/**
	 * Get the running state of the State
	 * 
	 * @return true if the simulation is running. Else false.
	 */
	public boolean getRunningValue() {
		return SimulationIsRunning;
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
	 * Halts the running simulator.
	 */
	public void haltSimulation() {
		SimulationIsRunning = false;
	}

	/**
	 * Starts the simulator.
	 */
	public void startSimulation() {
		SimulationIsRunning = true;
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
