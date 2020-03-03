package lab5.deds;

import java.util.ArrayList;

public class EventQueue {
	private ArrayList<Event> eventQueue;

	public EventQueue() {
		eventQueue = new ArrayList<Event>();
	}

	public void addEvent(Event e) {
		eventQueue.add(e);
		SortedSequence sortedSequence = new SortedSequence(eventQueue);
		sortedSequence.sort();
		eventQueue = sortedSequence.getSortedQueue();
	}

	public void run() {
		eventQueue.get(0).execute();
		eventQueue.remove(0);
	}

	public boolean hasNext() {
		if (!eventQueue.isEmpty()) {
			return true;
		}
		return false;
	}
}
