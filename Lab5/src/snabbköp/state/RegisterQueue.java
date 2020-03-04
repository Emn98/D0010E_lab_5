package snabbköp.state;

import java.util.NoSuchElementException;
import java.util.Vector;

public class RegisterQueue {
	
	private Vector registerQueue = new Vector();
	
	public RegisterQueue(){}
	
	public Object removeAndGetFirst() {
		if(registerQueue.isEmpty()) {
			throw new NoSuchElementException();
		}else {
			Object customer = registerQueue.elementAt(0); 
			registerQueue.removeElementAt(0);
			return customer;
		}
	}
	
	public boolean isEmpty() {
		return registerQueue.size() == 0; 
	}
}
