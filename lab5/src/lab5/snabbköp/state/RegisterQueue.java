package lab5.snabbköp.state;

import java.util.ArrayList;

import lab5.deds.Event;
import lab5.snabbköp.event.CustomerDrivenEvents;
import lab5.snabbköp.state.CustomerFactory.Customer;

/**
 * 
 * A class to represent an register queue.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 * 
 *
 */
public class RegisterQueue {

	private ArrayList<Customer> registerQueue = new ArrayList();

	public RegisterQueue() {
	}

	/**
	 * Adds a customer to the back of the queue.
	 * 
	 * @param e, the customer that will be added to the queue.
	 */
	public void add(Customer e) {
		registerQueue.add(e);
	}

	/**
	 * Removes the customer at the front of the queue.
	 * 
	 * @return the customer at the front of the queue.
	 */
	public Customer removeFirstCustomer() {
		return registerQueue.remove(0);
	}

	/**
	 * Checks if the queue is empty.
	 * 
	 * @return true if the queue is empty, otherwise false.
	 */
	public boolean isEmpty() {
		return registerQueue.size() == 0;
	}

	/**
	 * Returns the amount of people standing in queue.
	 * 
	 * @return the current size of the queue.
	 */
	public int getSize() {
		return registerQueue.size();
	}

	/**
	 * Converts the queue to a string.
	 * 
	 * @return A string visualization of the queue.
	 */
	public String toString() {
		String str = "[";
		for (int i = 0; i < registerQueue.size(); i++) {
			if (i == 0) {
				str += String.valueOf(registerQueue.get(i).getCustomerId());
			} else {
				str += ", " + String.valueOf(registerQueue.get(i).getCustomerId());
			}
		}
		str += "]";
		return str;
	}
}
