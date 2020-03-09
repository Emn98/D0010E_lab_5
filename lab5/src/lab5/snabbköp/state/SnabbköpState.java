package lab5.snabbköp.state;

import lab5.deds.Event;
import lab5.deds.State;
import lab5.snabbköp.state.CustomerFactory.Customer;

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

	public Customer makeNewCustomer() {
		return factory.makeANewCustomer();
	}

	public double calcNextCustomerArrivalTime() {
		return arrivalTimeManager.calcNextCustArrival();
	}

	public double calcTimeToGatherTheGoods() {
		return timeToGatherGoodsManager.calcTimeToGatherGoods();
	}

	public double calcTimeToPay() {
		return timeToPayManager.calcTimeToPayForGoods();
	}

	public void closeTheStore() {
		storeIsClosed = true;
	}

	public boolean isClosed() {
		return storeIsClosed;
	}
	
	public String getIsClosedSymbol() {
		if(isClosed()) {
			return "S";
		}
		return "Ö";
	}

	public double getStoreClosingTime() {
		return storeClosingTime;
	}

	public boolean isFreeRegisters() {
		if (nrOfFreeRegisters >= 1) {
			return true;
		}
		return false;
	}

	public boolean enoughRoomInStore() {
		if (maxNrOfCustomerInStoreAtOnce > nrOfCustomerInStoreAtTheMoment) {
			return true;
		}
		return false;
	}

	public int getTotalVisitors() {
		return totalNrOfVisitedCustomer;
	}
	
	public int getNumberOfSuccessfullPurchases() {
		return succesfullPurchases;
	}
	
	public int getNrOfMissedCustomers() {
		return missedCustomers;
	}
	
	public int getNrOfRegisters() {
		return nrOfRegisters;
	}
	
	public int getNrOfFreeRegisters() {
		return nrOfFreeRegisters;
	}
	
	public int getMaxNrOfCustomersInStore() {
		return maxNrOfCustomerInStoreAtOnce;
	}
	
	public int getCurrentCustomersInStore() {
		return nrOfCustomerInStoreAtTheMoment;
	}
	
	public int getSucessfullPurschases() {
		return succesfullPurchases;
	}
	
	public int getMissedCustomers() {
		return missedCustomers;
	}
	
	public int getCustomersWhoHadToQueue() {
		return nrOfCustomerWhoHadToQueue;
	}
	
	public int getQueueSize() {
		return regQueue.getSize();
	}
	
	public int getTotalNumberOfVisistors() {
		return totalNrOfVisitedCustomer;
	}
	
	public double getTimeForLastPayEvent() {
		return timeWhenLastPayEventIsMade;
	}
	
	public double getLambda() {
		return lambda;
	}
	
	public double getPMin() {
		return pMin;
	}
	
	public double getPMax() {
		return pMax;
	}
	
	public double getKMin() {
		return kMin;
	}
	
	public double getKMax() {
		return kMax;
	}
	
	public double getSeed() {
		return seed;
	}

	public void increaseMissedCustomer() {
		missedCustomers++;
	}

	public void increaseCurrentVisitors() {
		nrOfCustomerInStoreAtTheMoment++;
	}

	public void increaseTotalVisitors() {
		totalNrOfVisitedCustomer++;
	}

	public void increaseNrOfFreeRegisters() {
		nrOfFreeRegisters++;
	}

	public void increaseNrOfCustomersWhoHadToQueue() {
		nrOfCustomerWhoHadToQueue++;
	}

	public void increaseSuccesfullpurchases() {
		succesfullPurchases++;
	}
	
	public void increaseCustomersWhoHadToQueue() {
		nrOfCustomerWhoHadToQueue++;
	}

	public void decreaseCurrentVisistors() {
		--nrOfCustomerInStoreAtTheMoment;
	}

	public void decreaseNrOfFreeRegisters() {
		nrOfFreeRegisters--;
	}

	public void addCustomerToRegisterQueue(Customer customer) {
		regQueue.add(customer);
	}

	public boolean queueIsEmpty() {
		return regQueue.isEmpty();
	}

	public Customer getNextCustomer() {
		return regQueue.removeFirstCustomer();
	}

	public String getQueue() {
		return regQueue.toString();
	}

	public void updateAffectedTimes(double time) {
		if (isFreeRegisters() == true && (isClosed() == false || nrOfCustomerInStoreAtTheMoment != 0)) {
			timeRegistersUnused += (time * nrOfFreeRegisters);
			timeWhenLastPayEventIsMade = super.getCurrentRunTime();
		}

		if (queueIsEmpty() == false) {
			timeCustomersQueuing += (time * regQueue.getSize());
		}
	}
	
	public double getTotalQueueTime() {
		return timeCustomersQueuing;
	}
	
	public double getTotalFreeRegistersTime() {
		return timeRegistersUnused;
	}
}
