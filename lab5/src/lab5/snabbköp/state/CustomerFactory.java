package lab5.snabbköp.state;

public class CustomerFactory {
	
	private int customerId = 0;

	public CustomerFactory() {}
	
	public Customer makeANewCustomer() {
		Customer newCustomer = new Customer(customerId);
		customerId += 1;
		return newCustomer;
	}
	
	public class Customer{
		
		private int customerId;
		
		private Customer(int customerId) {
			this.customerId = customerId;
		}
		
		public int getCustomerId() {
			return customerId;
		}
	}
}