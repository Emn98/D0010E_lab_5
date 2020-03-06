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
		State state = this.getState();
		EventQueue queue = this.getQueue();
		
		double timePassedBetweenEvents = (this.getTime() - state.getCurrentRunTime());
		
		state.updateTotalRunTime(this.getTime());
		state.notifyObs(this);
		
		//The effects
		if(((SnabbköpState) state).isClosed() == true) {
			((SnabbköpState) state).updateAffectedTimes(timePassedBetweenEvents);
			return;
		}
		
		if(((SnabbköpState) state).enoughRoomInStore() == false) {
			((SnabbköpState) state).increaseMissedCustomer();
			System.out.println("hej");
		}else {
			((SnabbköpState) state).increaseCurrentVisitors();
			((SnabbköpState) state).increaseTotalVisitors();
			queue.addEvent(new PlockEvent(state,queue,this.getCustomer()));
		}
		queue.addEvent(new CustomerArrivalEvent(state,queue,((SnabbköpState) state).makeNewCustomer()));
		((SnabbköpState) state).updateAffectedTimes(timePassedBetweenEvents);
	}
	
	public String getEventName() {
		return "Ankomst";
	}
}
