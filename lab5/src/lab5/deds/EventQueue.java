package lab5.deds;

import java.util.ArrayList;

/**
 * 
 * Makes a queue for events.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 * 
 *
 */
public class EventQueue {
	private ArrayList<Event> eventQueue;
	private SortedSequence sortedSequence = new SortedSequence();

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

		eventQueue = sortedSequence.add(eventQueue, e);
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
