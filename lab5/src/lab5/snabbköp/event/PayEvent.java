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
		EventQueue queue = super.getQueue();
		SnabbköpState state = (SnabbköpState) super.getState();

		double timePassedBetweenEvents = (this.getTime() - state.getCurrentRunTime());
		state.updateTotalRunTime(this.getTime());
		state.updateAffectedTimes(timePassedBetweenEvents);

		state.notifyObs(this);

		// The effects
		state.increaseNrOfFreeRegisters();
		state.decreaseCurrentVisistors();
		state.increaseSuccesfullpurchases();

		if (state.queueIsEmpty() == false) {
			Customer customer = state.getNextCustomer();
			queue.addEvent(new PayEvent(state, queue, customer));
		}
	}

	public String getEventName() {
		return "Betalning";
	}
}
