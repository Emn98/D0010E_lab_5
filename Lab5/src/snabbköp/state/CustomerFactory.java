package snabbköp.state;

public class CustomerFactory {
	
	private int customerId = 0;
	
	CustomerFactory(){}
	
	public Customer makeNewCustomer() {
		Customer newCustomer = new Customer(customerId);
		customerId++;
		return newCustomer;
	}

	
	public class Customer{
		
		private int id;
		
		private Customer(int id){
			this.id = id;
		}
		
		public int getCustomerId() {
			return id;
		}
	}
}
