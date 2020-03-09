package lab5.snabbköp.event;

import lab5.deds.EventQueue;
import lab5.deds.State;
import lab5.snabbköp.state.CustomerFactory.Customer;
import lab5.snabbköp.state.SnabbköpState;

public class PayEvent extends CustomerDrivenEvents {
	
	private SnabbköpState tempState;

	public PayEvent(State state, EventQueue queue, Customer customer) {
		super(state, queue, customer);
		tempState = (SnabbköpState) super.getState();
		double timeToPay = tempState.calcTimeToPay();
		this.setExecutionTime(timeToPay);
		tempState.decreaseNrOfFreeRegisters();
	}

	public void execute() {
		EventQueue queue = this.getQueue();

		double timePassedBetweenEvents = (this.getTime() - tempState.getCurrentRunTime());
		tempState.updateTotalRunTime(this.getTime());
		tempState.updateAffectedTimes(timePassedBetweenEvents);

		
		tempState.notifyObs(this);
		
		//The effects
		tempState.increaseNrOfFreeRegisters();
		tempState.decreaseCurrentVisistors();
		tempState.increaseSuccesfullpurchases();
		
		if(tempState.queueIsEmpty() == false) {
			Customer customer = tempState.getNextCustomer();
			queue.addEvent(new PayEvent(tempState,queue,customer));
		}
	}
	
	public String getEventName() {
		return "Betalning";
	}
}
