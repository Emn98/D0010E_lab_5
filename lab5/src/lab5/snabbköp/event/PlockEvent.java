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
		SnabbköpState state = (SnabbköpState) this.getState();
		EventQueue queue = this.getQueue();

		double timePassedBetweenEvents = (this.getTime() - state.getCurrentRunTime());
		state.updateAffectedTimes(timePassedBetweenEvents);

		state.updateTotalRunTime(this.getTime());
		state.notifyObs(this);
		
		//the effects.
		if(state.isFreeRegisters() == true) {
			queue.addEvent(new PayEvent(state,queue,super.getCustomer()));
		}else {
			state.increaseCustomersWhoHadToQueue();
			state.addCustomerToRegisterQueue(this.getCustomer());
		}
	}
	
	public String getEventName() {
		return "Plock    ";
	}
}
