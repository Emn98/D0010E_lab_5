package lab5.snabbköp.state;

import java.util.ArrayList;

import lab5.deds.Event;
import lab5.snabbköp.event.CustomerDrivenEvents;
import lab5.snabbköp.state.CustomerFactory.Customer;

public class RegisterQueue {

	private ArrayList<Customer> registerQueue = new ArrayList();

	public RegisterQueue() {
	}

	public void add(Customer e) {
		registerQueue.add(e);
	}

	public Customer removeFirstCustomer() {
		return registerQueue.remove(0);
	}

	public boolean isEmpty() {
		return registerQueue.size() == 0;
	}
	
	public int getSize() {
		return registerQueue.size();
	}
	
	public String toString() {
		String str = "[";
		for(int i = 0; i < registerQueue.size(); i++) {
			str += String.valueOf(registerQueue.get(i).getCustomerId() +", ");
		}
		str += "]";
		return str;
	}
}