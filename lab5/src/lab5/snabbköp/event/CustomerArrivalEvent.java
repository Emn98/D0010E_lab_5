package lab5.snabbköp.event;

import lab5.deds.Event;
import lab5.deds.EventQueue;
import lab5.deds.State;
import lab5.snabbköp.state.CustomerFactory.Customer;
import lab5.snabbköp.state.SnabbköpState;

public class CustomerArrivalEvent extends CustomerDrivenEvents {
	
	public CustomerArrivalEvent(State state, EventQueue queue, Customer customer) {
		super(state, queue, customer);
		double arrivalTime = ((SnabbköpState) state).calcNextCustomerArrivalTime();
		this.setExecutionTime(arrivalTime);
	}
	
	public void execute() {
		SnabbköpState state = (SnabbköpState) super.getState();
		EventQueue queue = super.getQueue();
		
		double timePassedBetweenEvents = (this.getTime() - state.getCurrentRunTime());
		state.updateAffectedTimes(timePassedBetweenEvents);
		state.updateTotalRunTime(this.getTime());
		
		state.notifyObs(this);
		
		//The effects
		if(state.isClosed() == true) {
			return;
		}
	
		state.increaseTotalVisitors();
		if(state.enoughRoomInStore() == false) {
			state.increaseMissedCustomer();
		}else {
			state.increaseCurrentVisitors();
			queue.addEvent(new PlockEvent(state,queue,super.getCustomer()));
		}
		queue.addEvent(new CustomerArrivalEvent(state,queue,state.makeNewCustomer()));
	}
	
	public String getEventName() {
		return "Ankomst   ";
	}
}
