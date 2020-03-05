package snabbköp.state;

import java.util.NoSuchElementException;
import java.util.Vector;

import lab5.deds.State;
import snabbköp.state.*;
import snabbköp.state.CustomerFactory.Customer;

public class RegisterQueue {
	
	private Vector<Customer> registerQueue = new Vector();
	private State state;
	
	
	public RegisterQueue(State state){
		this.state = state;
	}
	
	
	
	public void removeAndGetFirst() {
		if(registerQueue.isEmpty()) {
			throw new NoSuchElementException();
		}else {
			Customer customer = registerQueue.elementAt(0); 
			registerQueue.removeElementAt(0);
			((SnabbköpState) state).increaseSuccessfullPurchase();
			((SnabbköpState) state).increaseNrOfFreeRegisters();
		}
	}
	
	public boolean isEmpty() {
		return registerQueue.size() == 0; 
	}
	
	public void add(Customer item) {
		registerQueue.add(item);
	}	
	
	public int getSize() {
		return registerQueue.size();
	}
	
	public String toString() {
		String str = "[";
		for(int i = 0; i < registerQueue.size(); i++) {
			str += String.valueOf(registerQueue.elementAt(i).getCustomerId());
		}
		str += "]";
		return str;
	}
}
