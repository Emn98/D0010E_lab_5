package lab5.snabbköp.event;

import lab5.deds.EventQueue;
import lab5.deds.State;
import lab5.snabbköp.state.CustomerFactory.Customer;
import lab5.snabbköp.state.SnabbköpState;

public class PlockEvent extends CustomerDrivenEvents {

	public PlockEvent(State state, EventQueue queue, Customer customer) {
		super(state, queue, customer);
		double plockTime = ((SnabbköpState) state).calcTimeToGatherTheGoods();
		this.setExecutionTime(plockTime);
	}

	public void execute() {
		State state = this.getState();
		EventQueue queue = this.getQueue();

		double timePassedBetweenEvents = (this.getTime() - state.getCurrentRunTime());

		state.updateTotalRunTime(this.getTime());
		state.notifyObs(this);
		
		//the effects.
		if(((SnabbköpState) state).isFreeRegisters()) {
			queue.addEvent(new PayEvent(state,queue,this.getCustomer()));
		}else {
			((SnabbköpState) state).addCustomerToRegisterQueue(this.getCustomer());
		}
		((SnabbköpState) state).updateAffectedTimes(timePassedBetweenEvents);
	}
	
	public String getEventName() {
		return "Plock";
	}
}
