package lab5.snabbköp.state;

import lab5.deds.State;
import snabbköp.state.CustomerFactory.Customer;

/**
 * 
 * Creates a snabbköpstate.
 * 
 * @author Karl Näslund, Emil Nyberg, Isak Lundmark.
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
	 * Creates a snabbköpstate.
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
		return factory.makeNewCustomer();
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
	 * 
	 * @return Returns if the store is closed or open.
	 */
	public boolean isClosed() {
		return storeIsClosed;
	}

	/**
	 * 
	 * @return Returns a symbol that indicates that the store is open or closed.
	 */
	public String getIsClosedSymbol() {
		if (isClosed()) {
			return "S";
		}
		return "Ö";
	}

	/**
	 * 
	 * @return Returns the closing time for the store.
	 */
	public double getStoreClosingTime() {
		return storeClosingTime;
	}

	/**
	 * 
	 * @return Returns if there are free registers or not.
	 */
	public boolean isFreeRegisters() {
		if (nrOfFreeRegisters >= 1) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return Returns if the store is full or not.
	 */
	public boolean enoughRoomInStore() {
		if (maxNrOfCustomerInStoreAtOnce > nrOfCustomerInStoreAtTheMoment) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return Returns how many customers that has visited the store.
	 */
	public int getTotalVisitors() {
		return totalNrOfVisitedCustomer;
	}

	/**
	 * 
	 * @return Returns how many that has purchased anything.
	 */
	public int getNumberOfSuccessfullPurchases() {
		return succesfullPurchases;
	}

	/**
	 * 
	 * @return Returns how many customers that have been missed.
	 */
	public int getNrOfMissedCustomers() {
		return missedCustomers;
	}

	/**
	 * 
	 * @return Returns how many registers the store has.
	 */
	public int getNrOfRegisters() {
		return nrOfRegisters;
	}

	/**
	 * 
	 * @return Returns how many registers that are free.
	 */
	public int getNrOfFreeRegisters() {
		return nrOfFreeRegisters;
	}

	/**
	 * 
	 * @return Returns how many customers that can be in the store at once.
	 */
	public int getMaxNrOfCustomersInStore() {
		return maxNrOfCustomerInStoreAtOnce;
	}

	/**
	 * 
	 * @return Returns how many customers that are in the store at this moment.
	 */
	public int getCurrentCustomersInStore() {
		return nrOfCustomerInStoreAtTheMoment;
	}

	/**
	 * 
	 * @return Returns how many sucessfull purchases that has been made.
	 */
	public int getSucessfullPurschases() {
		return succesfullPurchases;
	}

	/**
	 * 
	 * @return Returns how many customers that have been missed.
	 */
	public int getMissedCustomers() {
		return missedCustomers;
	}

	/**
	 * 
	 * @return Returns how many customers that have had to queue.
	 */
	public int getCustomersWhoHadToQueue() {
		return nrOfCustomerWhoHadToQueue;
	}

	/**
	 * 
	 * @return Returns the size of the queue.
	 */
	public int getQueueSize() {
		return regQueue.getSize();
	}

	/**
	 * 
	 * @return Returns the total amount of visitors.
	 */
	public int getTotalNumberOfVisistors() {
		return totalNrOfVisitedCustomer;
	}

	/**
	 * 
	 * @return Returns the time for when the last payment was made.
	 */
	public double getTimeForLastPayEvent() {
		return timeWhenLastPayEventIsMade;
	}

	/**
	 * 
	 * @return Returns the customer arrivalspeed.
	 */
	public double getLambda() {
		return lambda;
	}

	/**
	 * 
	 * @return Returns the time to gather goods.
	 */
	public double getPMin() {
		return pMin;
	}

	/**
	 * 
	 * @return Returns the max time to gather goods.
	 */
	public double getPMax() {
		return pMax;
	}

	/**
	 * 
	 * @return Returns the lowest time to pay.
	 */
	public double getKMin() {
		return kMin;
	}

	/**
	 * 
	 * @return Returns the highest time to pay.
	 */
	public double getKMax() {
		return kMax;
	}

	/**
	 * 
	 * @return Returns the seed.
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
	 * Adds a customer to the registerqueue.
	 * 
	 * @param customer Takes a customer.
	 */
	public void addCustomerToRegisterQueue(Customer customer) {
		regQueue.add(customer);
	}

	/**
	 * 
	 * @return Returns if the queue is empty.
	 */
	public boolean queueIsEmpty() {
		return regQueue.isEmpty();
	}

	/**
	 * 
	 * @return Returns and removes the the first customer in the queue.
	 */
	public Customer getNextCustomer() {
		return regQueue.removeFirstCustomer();
	}

	/**
	 * 
	 * @return Returns a string displaying the queue.
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
	 * 
	 * @return Returns the total time customers have bee queueing.
	 */
	public double getTotalQueueTime() {
		return timeCustomersQueuing;
	}

	/**
	 * 
	 * @return Returns the total time registers have been unused.
	 */
	public double getTotalFreeRegistersTime() {
		return timeRegistersUnused;
	}
}
