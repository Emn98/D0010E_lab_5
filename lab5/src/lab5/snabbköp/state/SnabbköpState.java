package lab5.snabbköp.state;

import lab5.deds.Event;
import lab5.deds.State;
import lab5.snabbköp.state.CustomerFactory.Customer;

/**
 * 
 * A class to represent the specialized state. That of a snabbköp.
 * 
 * @author Isak Lundmark, Emil Nyberg and Karl Näslund.
 * 
 */
public class SnabbköpState extends State {

	private int nrOfRegisters;
	private int nrOfFreeRegisters;
	private int maxNrOfCustomerInStoreAtOnce;
	private double storeClosingTime;

	private int totalNrOfVisitedCustomer = 0;
	private int succesfullPurchases = 0;
	private int nrOfCustomerInStoreAtTheMoment = 0;
	private int nrOfCustomerWhoHadToQueue = 0;
	private int missedCustomers = 0;
	private boolean storeIsClosed = false;

	private double timeWhenLastPayEventIsMade;
	private double timeRegistersUnused = 0.00;
	private double timeCustomersQueuing = 0.00;

	private RegisterQueue regQueue;
	private CustomerFactory factory;
	private ArrivalTime arrivalTimeManager;
	private TimeToGatherGoods timeToGatherGoodsManager;
	private TimeToPay timeToPayManager;

	private long seed;
	private double lambda;
	private double kMin, kMax;
	private double pMin, pMax;

	/**
	 * 
	 * Constructor. Creates a SnabbköpState and the relevant instances of classes.
	 * 
	 * @param seed                         Takes a seed.
	 * @param lambda                       Decides the arrivalspeed of the
	 *                                     customers.
	 * @param kMin                         The lowest time that it takes to pay.
	 * @param kMax                         The highest time that it takes to pay.
	 * @param pMin                         The lowest time it takes to gather goods.
	 * @param pMax                         The highest time it takes to gather
	 *                                     goods.
	 * @param nrOfRegisters                The amount of registers in the store.
	 * @param maxNrOfCustomerInStoreAtOnce The max amount of customers that can be
	 *                                     in the store at once.
	 * @param closingTime                  The time for when the store closes.
	 */

	public SnabbköpState(long seed, double lambda, double kMin, double kMax, double pMin, double pMax,
			int nrOfRegisters, int maxNrOfCustomerInStoreAtOnce, double closingTime) {
		this.seed = seed;
		this.lambda = lambda;
		this.kMin = kMin;
		this.kMax = kMax;
		this.pMin = pMin;
		this.pMax = pMax;

		this.nrOfRegisters = nrOfRegisters;
		this.nrOfFreeRegisters = nrOfRegisters;
		this.maxNrOfCustomerInStoreAtOnce = maxNrOfCustomerInStoreAtOnce;
		this.storeClosingTime = closingTime;

		regQueue = new RegisterQueue();
		factory = new CustomerFactory();

		arrivalTimeManager = new ArrivalTime(this, lambda, seed);
		timeToGatherGoodsManager = new TimeToGatherGoods(this, pMin, pMax, seed);
		timeToPayManager = new TimeToPay(this, kMin, kMax, seed);
	}

	/**
	 * 
	 * Creates a new Customer.
	 * 
	 * @return Returns a new customer.
	 */
	public Customer makeNewCustomer() {
		return factory.makeANewCustomer();
	}

	/**
	 * 
	 * Creates a time for when a customer is to arrive.
	 * 
	 * @return Returns a time for when the customer is to arrive.
	 */
	public double calcNextCustomerArrivalTime() {
		return arrivalTimeManager.calcNextCustArrival();
	}

	/**
	 * 
	 * Creates a time for how long it will take to gather goods.
	 * 
	 * @return Returns a time for the time to gather goods.
	 */
	public double calcTimeToGatherTheGoods() {
		return timeToGatherGoodsManager.calcTimeToGatherGoods();
	}

	/**
	 * 
	 * Creates a time for how long it will take to pay.
	 * 
	 * @return Returns the time for the time to pay.
	 */
	public double calcTimeToPay() {
		return timeToPayManager.calcTimeToPayForGoods();
	}

	/**
	 * 
	 * Closes the store.
	 * 
	 */
	public void closeTheStore() {
		storeIsClosed = true;
	}

	/**
	 * Checks if the store is closed.
	 * 
	 * @return true if the store is closed, otherwise false.
	 */
	public boolean isClosed() {
		return storeIsClosed;
	}

	/**
	 * gets a symbol to represent if the store is open or closed.
	 * 
	 * @return Returns a symbol that indicates that the store is open (Ö) or closed
	 *         (S).
	 */
	public String getIsClosedSymbol() {
		if (isClosed()) {
			return "S";
		}
		return "Ö";
	}

	/**
	 * 
	 * Gets the store's closing time.
	 * 
	 * @return the closing time for the store.
	 */
	public double getStoreClosingTime() {
		return storeClosingTime;
	}

	/**
	 * Checks if there is a free register available.
	 * 
	 * @return true if there is at least one free registers, otherwise false.
	 */
	public boolean isFreeRegisters() {
		if (nrOfFreeRegisters >= 1) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if there is enough room in the store to take in more customers.
	 * 
	 * @return true if there is enough room, otherwise false.
	 */
	public boolean enoughRoomInStore() {
		if (maxNrOfCustomerInStoreAtOnce > nrOfCustomerInStoreAtTheMoment) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the total number of customers who have visited the store.
	 * 
	 * @return the total number of visited customers.
	 */
	public int getTotalVisitors() {
		return totalNrOfVisitedCustomer;
	}

	/**
	 * Gets the number of successful purchases.
	 * 
	 * @return the number of successful purchases.
	 */
	public int getSucessfullPurschases() {
		return succesfullPurchases;
	}

	/**
	 * Gets the number of missed customers.
	 * 
	 * @return the number of missed customers.
	 */
	public int getNrOfMissedCustomers() {
		return missedCustomers;
	}

	/**
	 * Gets the total number of registers in the store.
	 * 
	 * @return the number of registers in the store.
	 */
	public int getNrOfRegisters() {
		return nrOfRegisters;
	}

	/**
	 * Gets the total number of available registers.
	 * 
	 * @return The number of free registers.
	 */
	public int getNrOfFreeRegisters() {
		return nrOfFreeRegisters;
	}

	/**
	 * Gets the max number of customers that can be in the store at once.
	 * 
	 * @return The number of max customers in the store.
	 */
	public int getMaxNrOfCustomersInStore() {
		return maxNrOfCustomerInStoreAtOnce;
	}

	/**
	 * Gets the current number of customers in the store at once.
	 * 
	 * @return The number of customers in the store.
	 */
	public int getCurrentCustomersInStore() {
		return nrOfCustomerInStoreAtTheMoment;
	}

	/**
	 * Gets the total number of customers that got missed during the simulation.
	 * 
	 * @return The number of missed customers.
	 */
	public int getMissedCustomers() {
		return missedCustomers;
	}

	/**
	 * Gets the number of customers that had to queue.
	 * 
	 * @return The number of customers that had to queue.
	 */
	public int getCustomersWhoHadToQueue() {
		return nrOfCustomerWhoHadToQueue;
	}

	/**
	 * Gets the current size of the queue.
	 * 
	 * @return The size of the queue.
	 */
	public int getQueueSize() {
		return regQueue.getSize();
	}

	/**
	 * Gets the time when the last pay event occurred during the simulation.
	 * 
	 * @return The time for the last pay event.
	 */
	public double getTimeForLastPayEvent() {
		return timeWhenLastPayEventIsMade;
	}

	/**
	 * Gets the value of lambda.
	 * 
	 * @return The value of lambda.
	 */
	public double getLambda() {
		return lambda;
	}

	/**
	 * Gets the minimum time it takes to gather the goods.
	 * 
	 * @return the shortest time it can take to gather the goods.
	 */
	public double getPMin() {
		return pMin;
	}

	/**
	 * Gets the maximum time it takes to gather goods.
	 * 
	 * @return the longest time it can take to gather the goods.
	 */
	public double getPMax() {
		return pMax;
	}

	/**
	 * Gets the minimum time it takes to pay.
	 * 
	 * @return the shortest time it can take to pay for the goods.
	 */
	public double getKMin() {
		return kMin;
	}

	/**
	 * Gets the maximum time it takes to pay.
	 * 
	 * @return the longest time it can take to pay for the goods.
	 */
	public double getKMax() {
		return kMax;
	}

	/**
	 * Gets the seed value.
	 * 
	 * @return the value of the seed.
	 */
	public double getSeed() {
		return seed;
	}

	/**
	 * 
	 * Increases the number of customers that have been missed.
	 */
	public void increaseMissedCustomer() {
		missedCustomers++;
	}

	/**
	 * 
	 * Increases the number of customers that are in the store.
	 */
	public void increaseCurrentVisitors() {
		nrOfCustomerInStoreAtTheMoment++;
	}

	/**
	 * 
	 * Increases the number of total visitors.
	 */
	public void increaseTotalVisitors() {
		totalNrOfVisitedCustomer++;
	}

	/**
	 * 
	 * Increases the number of free registers.
	 */
	public void increaseNrOfFreeRegisters() {
		nrOfFreeRegisters++;
	}

	/**
	 * 
	 * Increases the number of customers that have had to queue.
	 */
	public void increaseNrOfCustomersWhoHadToQueue() {
		nrOfCustomerWhoHadToQueue++;
	}

	/**
	 * 
	 * Increases the number of sucessfull purchases.
	 */
	public void increaseSuccesfullpurchases() {
		succesfullPurchases++;
	}

	/**
	 * 
	 * Increases the number of customers who have had to queue.
	 */
	public void increaseCustomersWhoHadToQueue() {
		nrOfCustomerWhoHadToQueue++;
	}

	/**
	 * 
	 * Decreases the number of current customers in the store.
	 */
	public void decreaseCurrentVisistors() {
		--nrOfCustomerInStoreAtTheMoment;
	}

	/**
	 * 
	 * Decreases the number of free registers.
	 */
	public void decreaseNrOfFreeRegisters() {
		nrOfFreeRegisters--;
	}

	/**
	 * 
	 * Adds a customer to the register queue.
	 * 
	 * @param customer Takes a customer.
	 */
	public void addCustomerToRegisterQueue(Customer customer) {
		regQueue.add(customer);
	}

	/**
	 * Checks if the register queue is empty or not. 
	 * 
	 * @return true if the queue is empty, otherwise false.
	 */
	public boolean queueIsEmpty() {
		return regQueue.isEmpty();
	}

	/**
	 * Removes the first customer standing in the queue. 
	 * 
	 * @return the first customer in the queue.
	 */
	public Customer getNextCustomer() {
		return regQueue.removeFirstCustomer();
	}

	/**
	 * Converts the queue to a string.
	 * 
	 * @return A string visualization of the queue.
	 */
	public String getQueue() {
		return regQueue.toString();
	}
	
	/**
	 * 
	 * Updates the time that the registers have been unused, when the last payment
	 * was made and the total time that customers have been queueing.
	 * 
	 * @param time Takes the time that is used to update with.
	 */
	public void updateAffectedTimes(double time) {
		if (isFreeRegisters() == true && (isClosed() == false || nrOfCustomerInStoreAtTheMoment != 0)) {
			timeRegistersUnused += (time * nrOfFreeRegisters);
			timeWhenLastPayEventIsMade = super.getCurrentRunTime();
		}

		if (queueIsEmpty() == false) {
			timeCustomersQueuing += (time * regQueue.getSize());
		}
	}

	/**
	 * Gets the total time customer have had to queue. 
	 * 
	 * @return Returns the total time customers have beeb queueing.
	 */
	public double getTotalQueueTime() {
		return timeCustomersQueuing;
	}

	/**
	 * Gets the total time registers have been unused. 
	 * 
	 * @return Returns the total time registers have been unused.
	 */
	public double getTotalFreeRegistersTime() {
		return timeRegistersUnused;
	}
}
