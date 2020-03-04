package snabbköp.state;

public class CustomerFactory {
	
	private int customerId = 0;
	
	CustomerFactory(){}
	
	public Customer makeNewCustomer(double arrivalTime) {
		Customer newCustomer = new Customer(customerId, arrivalTime);
		customerId++;
		return newCustomer;
	}
	
	public int getCustomerId(Customer c) {
		return c.getCustomerId();
	}
	
	public double getCustomerTimeInStore(Customer c) {
		return c.getCustomerTimeInStore();
	}
	
	private class Customer{
		
		private int id;
		private double arrivalTime;
		private double timeSpentInStore = 0;
		
		Customer(int id, double arrivalTime){
			this.id = id;
			this.arrivalTime = arrivalTime;
		}
		
		private int getCustomerId() {
			return id;
		}
		
		private double getCustomerTimeInStore() {
			return timeSpentInStore;
		}
	}
}