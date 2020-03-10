package lab5.snabbköp.state;

import java.util.ArrayList;

import lab5.deds.Event;
import snabbköp.event.CustomerDrivenEvents;
import snabbköp.state.CustomerFactory.Customer;

/**
 * 
 * Creates a register queue.
 * 
 * @author Karl Näslund, Emil Nyberg, Isak Lundmark.
 *
 */

public class RegisterQueue {

	private ArrayList<Customer> registerQueue = new ArrayList();

	public RegisterQueue() {
	}

	/**
	 * 
	 * Adds a customer to the register queue.
	 * 
	 * @param e Takes a customer.
	 */
	public void add(Customer e) {
		registerQueue.add(e);
	}

	/**
	 * 
	 * @return Returns a customer and removes him from the queue.
	 */
	public Customer removeFirstCustomer() {
		return registerQueue.remove(0);
	}

	/**
	 * 
	 * @return Returns true or false if the queue is empty.
	 */
	public boolean isEmpty() {
		return registerQueue.size() == 0;
	}
	
	/**
	 * 
	 * @return Returns the size of the queue.
	 */
	public int getSize() {
		return registerQueue.size();
	}
	
	/**
	 * @return Returns a string displaying the queue.
	 */
	public String toString() {
		String str = "[";
		for(int i = 0; i < registerQueue.size(); i++) {
			str += String.valueOf(registerQueue.get(i).getCustomerId() +", ");
		}
		str += "]";
		return str;
	}
}
