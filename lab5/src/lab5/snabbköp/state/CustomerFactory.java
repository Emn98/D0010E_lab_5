package lab5.snabbköp.state;

/**
 * 
 * A class for creating customers.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 * 
 */
public class CustomerFactory {

	private int customerId = 0;// Represent the customer id:s.

	public CustomerFactory() {
	}

	/**
	 * 
	 * Returns a new customer with an unique id.
	 * 
	 * @return An new customer.
	 */
	public Customer makeANewCustomer() {
		Customer newCustomer = new Customer(customerId);
		customerId += 1;
		return newCustomer;
	}

	/**
	 * 
	 * A class to represent customers.
	 * 
	 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
	 * 
	 */
	public class Customer {

		private int customerId;

		private Customer(int customerId) {
			this.customerId = customerId;
		}

		/**
		 * Returns an customers unique id number.
		 * 
		 * @return The id of the customer.
		 */
		public int getCustomerId() {
			return customerId;
		}
	}
}
