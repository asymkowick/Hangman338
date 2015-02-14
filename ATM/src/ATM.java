/**
* Title: ATM.java
* Abstract: This class holds information for ATM machines and several customer accounts.
* Author: Anthony Symkowick
* ID: 3895
* Date: 2/13/15
*/



import java.text.DecimalFormat;


public class ATM {
	
	private String bankName;
	private String bankLocation;
	private Double machineCash;
	private int serialNumber;
	private int withdrawalPass;
	private int withdrawalFail;
	private int depositPass;
	private int depositFail;
	private int transferPass;
	private int transferFail;
	
	
	Account [] customers; //holds customers
	
	
	
	/*
	 * Constructors
	 */
	
	public ATM() {
		customers = new Account[10];
		this.bankName = "UNKNOWN";
		this.bankLocation = "UNKNOWN";
		this.machineCash = 100.00;
		this.serialNumber = 0;
		customers[0] = new Account("Alice", 1234, 5000.00);
		customers[1] = new Account("Tom", 2000, 200.00);
		customers[2] = new Account("Monica", 3000, 50.00);
		customers[3] = new Account("Michael", 7777, 0.00);
		customers[4] = new Account("John", 8000, 500.00);
		customers[5] = new Account("Jane", 2222, 500.00);
		customers[6] = new Account("Robert", 2323, 200.00);
		customers[7] = new Account("Owen", 4455, 50.00);
		customers[8] = new Account("Chris", 8787, 10.00);
		customers[9] = new Account("Rebecca", 8080, 555.55);
	}
	
	public ATM(String name) {
		customers = new Account[10];
		this.bankName = name;
		this.bankLocation = "UNKNOWN";
		this.machineCash = 100.00;
		this.serialNumber = 0;
		customers[0] = new Account("Alice", 1234, 5000.00);
		customers[1] = new Account("Tom", 2000, 200.00);
		customers[2] = new Account("Monica", 3000, 50.00);
		customers[3] = new Account("Michael", 7777, 0.00);
		customers[4] = new Account("John", 8000, 500.00);
		customers[5] = new Account("Jane", 2222, 500.00);
		customers[6] = new Account("Robert", 2323, 200.00);
		customers[7] = new Account("Owen", 4455, 50.00);
		customers[8] = new Account("Chris", 8787, 10.00);
		customers[9] = new Account("Rebecca", 8080, 555.55);
	}
	
	public ATM(int serialNumber, String name, String location) {
		this.bankName = name;
		this.bankLocation = location;
		this.machineCash = 100.00;
		this.serialNumber = serialNumber;
		
		customers = new Account[10];
		customers[0] = new Account("Alice", 1234, 5000.00);
		customers[1] = new Account("Tom", 2000, 200.00);
		customers[2] = new Account("Monica", 3000, 50.00);
		customers[3] = new Account("Michael", 7777, 0.00);
		customers[4] = new Account("John", 8000, 500.00);
		customers[5] = new Account("Jane", 2222, 500.00);
		customers[6] = new Account("Robert", 2323, 200.00);
		customers[7] = new Account("Owen", 4455, 50.00);
		customers[8] = new Account("Chris", 8787, 10.00);
		customers[9] = new Account("Rebecca", 8080, 555.55);
	}
	
	/*
	 * Overloaded Methods
	 */
	
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("0.00");
		
		
		return("Serial Number: " + this.serialNumber + "\n"
				+ "Bank Name: " + this.bankName + "\n" 
				+ "Location: " + this.bankLocation + "\n"
				+ "Balance: " + formatter.format(this.machineCash));
		
	}
	
	public boolean equals(ATM myATM) {
		return(this.serialNumber == myATM.serialNumber);
	}
	
	
	/*
	 * Member Methods
	 */
	
	public void setATM(int serialNumber, String location) {
		this.serialNumber = serialNumber;
		this.bankLocation = location;
	}
	
	public void addFund(int amount) {
		this.machineCash += amount;
	}
	
	public void displayMenu() {
		System.out.println("===== ATM Transaction Menu =====");
		System.out.println("  1. Withdrawal");
		System.out.println("  2. Deposit");
		System.out.println("  3. Transfer");
		
	}
	
	public void withdrawal(String accountName, int PIN, double amount) {
		DecimalFormat formatter = new DecimalFormat("0.00");
		boolean found = false;
		
		for(int i = 0; i < 10; i++)
		{
		
			if(customers[i].accountName.equals(accountName) && 
					customers[i].accountPin == PIN && amount <= customers[i].accountBalance && amount <= this.machineCash)
			{
				customers[i].accountBalance -= amount;
				System.out.println("Succeed - withdrawal: " + customers[i].accountName + " new balance: $" 
						+ formatter.format(customers[i].accountBalance));
				
				this.machineCash -= amount;
				found = true;
				this.withdrawalPass += 1;
			}
			
		}
		
		if(found == false)
		{
			System.out.println("Fail - withdrawal");
			this.withdrawalFail += 1;
		}
		
		}
	
	public void deposit(String accountName, int PIN, double amount) {
		DecimalFormat formatter = new DecimalFormat("0.00");
		
		
		
		boolean found = false;
		
		for(int i = 0; i < 10; i++)
		{
			
			if(customers[i].accountName.equals(accountName) && 
					customers[i].accountPin == PIN && amount <= customers[i].accountBalance)
			{
				customers[i].accountBalance += amount;
				System.out.println("Succeed - deposit: " + customers[i].accountName + " new balance: $" 
						+ formatter.format(customers[i].accountBalance));
				
				this.machineCash += amount;
				
				found = true;
				this.depositPass += 1;
			}
			
			
		}
		
		if(found == false)
		{
			System.out.println("Fail - deposit");
			this.depositFail += 1;
		}
			
	}
	
	public void transfer(String giveName, int givePIN, int amount, String getName, int getPIN) {
		DecimalFormat formatter = new DecimalFormat("0.00");
		boolean found = false;
		
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(customers[i].accountName.equals(giveName) && customers[i].accountPin == givePIN &&
						customers[j].accountName.equals(getName) && 
						customers[j].accountPin == getPIN && amount <= customers[i].accountBalance)
					
				{
					customers[i].accountBalance -= amount;
					customers[j].accountBalance += amount;
					
					System.out.println("Succeed - transfer: " + customers[i].accountName + " new balance: $" + 
					formatter.format(customers[i].accountBalance) + ", " + customers[j].accountName + " new balance: $" + 
							formatter.format(customers[j].accountBalance));
					
					transferPass += 1;
					found = true;
				}
				
			}
		}
		
		if(found == false) 
		{
			System.out.println("Fail - transfer");
			transferFail += 1;
		}
			
	}
	
	public void status() {
		DecimalFormat formatter = new DecimalFormat("0.00");
		
		
		System.out.println("Serial Number: " + this.serialNumber + "\n"
				+ "Bank Name: " + this.bankName + "\n" 
				+ "Location: " + this.bankLocation + "\n"
				+ "Balance: " + formatter.format(this.machineCash));
		int totalW = withdrawalPass + withdrawalFail;
		int totalD = depositPass + depositFail;
		int totalT = transferPass + transferFail;
		int total = totalW + totalD + totalT;
		
		
System.out.println(total + " Transactions so far: ");
System.out.println("	Withdrawal: " + totalW + " (" + withdrawalPass + " success, " + withdrawalFail + " fail)");
System.out.println("	Deposit: " + totalD + " (" + depositPass + " success, " + depositFail + " fail)");
System.out.println("	Transfer: " + totalT + " (" + transferPass + " success, " + transferFail + " fail)");
	}
	
	
	
	
public class Account {
	String accountName;
	int accountPin;
	double accountBalance;
	
	public Account(String name, int pin, double bal) {
		this.accountName = name;
		this.accountPin = pin;
		this.accountBalance = bal;
	}
	
		
	}
			
		
	
	
}
