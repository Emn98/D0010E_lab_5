package lab5.snabbköp.event;

import lab5.deds.EventQueue;
import lab5.deds.State;
import lab5.snabbköp.state.CustomerFactory.Customer;
import lab5.snabbköp.state.SnabbköpState;

public class PayEvent extends CustomerDrivenEvents {
	

	public PayEvent(State state, EventQueue queue, Customer customer) {
		super(state, queue, customer);
		double timeToPay = ((SnabbköpState) state).calcTimeToPay();
		this.setExecutionTime(timeToPay);
		((SnabbköpState) state).decreaseNrOfFreeRegisters();
	}

	public void execute() {
		State state = this.getState();
		EventQueue queue = this.getQueue();

		double timePassedBetweenEvents = (this.getTime() - state.getCurrentRunTime());

		state.updateTotalRunTime(this.getTime());
		state.notifyObs(this);
		
		//The effects
		((SnabbköpState) state).increaseNrOfFreeRegisters();
		((SnabbköpState) state).decreaseCurrentVisistors();
		((SnabbköpState) state).increaseSuccesfullpurchases();
		((SnabbköpState) state).updateAffectedTimes(timePassedBetweenEvents);
		
		if(((SnabbköpState) state).queueIsEmpty() == false) {
			Customer customer = ((SnabbköpState) state).getNextCustomer();
			queue.addEvent(new PayEvent(state,queue,customer));
		}
	}
	
	public String getEventName() {
		return "Betalning";
	}
}
