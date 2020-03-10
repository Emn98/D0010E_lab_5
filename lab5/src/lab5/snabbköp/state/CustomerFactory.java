package lab5.snabbköp.state;

/**
 * 
 * Creates a customer.
 * 
 * @author Karl Näslund, Emil Nyberg, Isak Lundmark.
 *
 */

public class CustomerFactory {
	
	private int customerId = 0;
	
	CustomerFactory(){}
	
	/**
	 * 
	 * @return Returns a new customer.
	 */
	public Customer makeNewCustomer() {
		Customer newCustomer = new Customer(customerId);
		customerId++;
		return newCustomer;
	}

	/**
	 * 
	 * @author Karl Näslund, Emil Nyberg, Isak Lundmark.
	 *
	 */
	public class Customer{
		
		private int id;
		
		/**
		 * 
		 * Gives an id to the customer.
		 * 
		 * @param id Takes an id for the customer.
		 */
		private Customer(int id){
			this.id = id;
		}
		
		/**
		 * 
		 * @return Returns the customer id.
		 */
		public int getCustomerId() {
			return id;
		}
	}
}
