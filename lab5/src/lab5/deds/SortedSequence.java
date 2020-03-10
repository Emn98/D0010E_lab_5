package lab5.deds;

import java.util.ArrayList;


/**
 * Creates an sorted list
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 *
 */
public class SortedSequence {
	private ArrayList<Event> tempQueue;
	
	/**
	 * 
	 * @param tempQueue is an ArrayList to sort.
	 */
	public SortedSequence(ArrayList<Event> tempQueue) {
		this.tempQueue = tempQueue;
	}
	
	
	/**
	 * 
	 * @return sorted list
	 */
	public ArrayList<Event> getSortedQueue() {
		return tempQueue;
	}

	/**
	 * 
	 * Sort the unsorted list.
	 * 
	 */
	public void sort() {
		for (int i = 1; i < tempQueue.size(); i++) {
			Event now = tempQueue.get(i);
			int j = i - 1;

			while (j >= 0 && now.getTime() < tempQueue.get(j).getTime()) {
				tempQueue.set(j + 1, tempQueue.get(j));
				j--;
			}

			tempQueue.set(j + 1, now);
		}

	}
}
