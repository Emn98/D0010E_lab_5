package lab5.deds;

import java.util.ArrayList;

/**
 * Creates an sorted list
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 *
 */
public class SortedSequence {
	/**
	 * 
	 * Adds an event and sorts the list.
	 * 
	 */
	public ArrayList<Event> add(ArrayList<Event> eventQueue, Event e) {

		if (eventQueue.isEmpty()) {
			eventQueue.add(e);
		} else {
			for (int i = 0; i < eventQueue.size(); i++) {
				if (eventQueue.get(i).getTime() > e.getTime()) {
					eventQueue.add(i, e);
					break;
				} else if (i == eventQueue.size() - 1) {
					eventQueue.add(e);
					break;
				}

			}
		}

		return eventQueue;
	}
}
