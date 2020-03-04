package lab5.deds;

import java.util.ArrayList;

public class EventQueue {
	private ArrayList<Event> eventQueue;

	public EventQueue() {
		eventQueue = new ArrayList<Event>();
	}

	/**
	 * Adds an event to the queue and sort the queue.
	 * 
	 * 
	 * @param e an Event to add to the eventQueue.
	 * 
	 */
	public void addEvent(Event e) {
		eventQueue.add(e);
		SortedSequence sortedSequence = new SortedSequence(eventQueue);
		sortedSequence.sort();
		eventQueue = sortedSequence.getSortedQueue();
	}

	/**
	 * 
	 * executes the first element in the queue.
	 * 
	 */
	public void run() {
		eventQueue.get(0).execute();
		eventQueue.remove(0);
	}

	/**
	 * 
	 * Clears the queue.
	 * 
	 */
	public void clearQueue() {
		eventQueue.clear();
	}

	/**
	 * Checks if the queue contains anything.
	 * 
	 * @return true the queue contains anything. false if the queue is empty.
	 */
	public boolean hasNext() {
		if (!eventQueue.isEmpty()) {
			return true;
		}
		return false;
	}
}
