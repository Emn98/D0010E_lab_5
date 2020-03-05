package snabbköp.state;
import lab5.deds.*;
import snabbköp.state.CustomerFactory.Customer;

public class SnabbköpState extends State {

	private int nrOfRegisters;
	private int nrOfFreeRegisters;
	private int maxNrOfCustomerInStoreAtOnce;

	private int totalNrOfVisitedCustomer = 0;
	private int succesFullPurchases = 0;
	private int nrOfCustomerInStoreAtTheMoment = 0;
	private int nrOfCustomerWhoHadToQueue = 0;
	private int missedCustomer = 0;
	private boolean isClosed = false;
	
	private double closingTime;
	private double timeRegistersUnused = 0.00;
	private double timeRegistersUsed = 0.00;
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
	
	
	public SnabbköpState(long seed, double lambda, double kMin, double kMax, double pMin, double pMax, int nrOfRegisters, int maxNrOfCustomerInStoreAtOnce, double closingTime) {
		this.seed = seed;
		this.lambda = lambda;
		this.kMin = kMin;
		this.kMax = kMax;
		this.pMin = pMin;
		this.pMax = pMax;
		
		this.nrOfRegisters = nrOfRegisters;
		this.nrOfFreeRegisters = nrOfRegisters;
		this.maxNrOfCustomerInStoreAtOnce = maxNrOfCustomerInStoreAtOnce;
		this.closingTime = closingTime;
		
		regQueue = new RegisterQueue(this); 
		factory = new CustomerFactory();
		
		arrivalTimeManager = new ArrivalTime(this,lambda,seed);
		timeToGatherGoodsManager = new TimeToGatherGoods(this,pMin,pMax,seed);
		timeToPayManager = new TimeToPay(this,kMin,kMax,seed);
		
	}
	
	public Customer makeACustomer() {
		return factory.makeNewCustomer();
	}
	
	
	public double calcNextCustomerArrival() {
		return arrivalTimeManager.calcNextCustArrival();
	}
	
	public double calcTimeToGatherTheGoods() {
		return timeToGatherGoodsManager.calcTimeToGatherGoods();
	}
	
	public double calcTimeToPay() {
		return timeToPayManager.calcTimeToPayForGoods();
	}
	
	public boolean isClosed() {
		return isClosed;
	}	
	
	public void closeStore() {
		isClosed = true;
	}
	
	public boolean isFreeRegister(){
		if(nrOfFreeRegisters != 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void increaseSuccessfullPurchase() {
		succesFullPurchases++;
	}
	
	public double getClosingTime() {
		return closingTime;
	}
	
	public boolean isFullStore() {
		if(nrOfCustomerInStoreAtTheMoment == maxNrOfCustomerInStoreAtOnce) {
			return true;
		}else {
			return false;
		}
	}
	
	public void increaseMissedCustomer() {
		missedCustomer++;
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
	
	public void addCustomerToQueue(Customer c) {
		regQueue.add(c);
	}
	
	public String getQueueString() {
		return regQueue.toString();
	}	
}
