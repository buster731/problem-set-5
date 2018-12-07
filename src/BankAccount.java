/**
 * Just like last time, the BankAccount class is primarily responsible
 * for depositing and withdrawing money. In the enhanced version, there
 * will be the added requirement of transfering funds between accounts.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */
/* The `BankAccount` class is responsible for managing all aspects of the customer's account information, as outlined below.

* The system-generated account number.
* The user associated with the account (i.e., an instance of the `User` class).
* The current account balance.

The `BankAccount` class should have the ability to deposit and withdraw money, as well as transfer funds to another account. That being said, customers should be prohibited from doing any of the following.

* Transfering a dollar amount that is less than or equal to $0.00. DONE
* Transfering a dollar amount that is greater than the sending account balance. DONE
* Transfering funds to non-existent accounts.
*/

public class BankAccount {
		
	private static long generatedAccountNumber = 100000001L;
		
	private long accountNumber;
	private double balance;
	private AccntHolder accntHolder;
	private Database db;
	
	public BankAccount(String account) {
		this.accountNumber = parseAcctNum();
		this.balance = parseBal();
		this.accntHolder = new AccntHolder(parsePin(), parseFName(), parseLName(), parseDateOB(), parsePNum(), parseAddy(), parseCity(), parseState(), parseZip());
	}
		
	public double getBalance() {
		return balance;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}
		
	public AccntHolder getAccntHolder() {
		return accntHolder;
	}
		
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = db.getAccount(accountNumber).parseAcctNum();
	}
		
	public void setBalance(double balance) {
		this.balance = db.getAccount(accountNumber).parseBal();
	}
		
	public void setAcctHolder(AccntHolder accntHolder) {
		this.accntHolder = accntHolder;
	}

	public long parseAcctNum() {
		String AcctNum = db.getAccount(accountNumber).toString().substring(0,10);
		long AccntNum = Long.parseLong(AcctNum);
		return AccntNum;
	}
	
	public String parsePin() {
		String Pin = db.getAccount(accountNumber).toString().substring(10,14);
		return Pin;
	}
	
	public double parseBal() {
		
		double balance = Double.parseDouble(db.getAccount(accountNumber).toString().substring(14,28));
		return balance;
	}
	
	public String parseLName() {
		
		String LastN= db.getAccount(accountNumber).toString().substring(28, 48);
		return LastN;
	}
	
	public String parseFName() {
		
		String FirstN= db.getAccount(accountNumber).toString().substring(48, 63);
		return FirstN;
	}
	
	public String parseDateOB() {
		
		String DateOB= db.getAccount(accountNumber).toString().substring(63, 71);
		return DateOB;
	}
	
	public String parsePNum() {
		
		String PNum= db.getAccount(accountNumber).toString().substring(71, 81);
		return PNum;
	}
	
	public String parseAddy() {
		
		String Addy= db.getAccount(accountNumber).toString().substring(81, 101);
		return Addy;
	}
	
	public String parseCity() {
		
		String City= db.getAccount(accountNumber).toString().substring(101, 131);
		return City;
	}

	public String parseState() {
		
		String State= db.getAccount(accountNumber).toString().substring(131, 133);
		return State;
	}

	public String parseZip() {
		
		String Zip= db.getAccount(accountNumber).toString().substring(133,138);
		return Zip;
	}
	
	public boolean parseActive() {
		if(db.getAccount(accountNumber).toString().substring(138,139) == "Y") {
			return true;
		}
		return false;
	}

	public int deposit(double depositVal) {
		if(depositVal < -1) {
			return 0;
		}
		else {
			this.balance = balance + depositVal;
			return 1;
		}
	}
		
	public int withdraw(double withdrawVal) {
		if(withdrawVal < -1) {
			return 0;
		}
		else if(withdrawVal > balance) {
			return -1;
		}
		else {
			this.balance = balance - withdrawVal;
			db.updateAccount(db.getAccount(accountNumber), null);
			return 1;
		}
	}
	
	public int transfer(double transferVal, long accountNumber1) {
		if(transferVal > this.balance) {
			System.out.println("Cannot transfer greater funds than are in your account");
			return -1;
		}
		else if(transferVal < 0) {
			System.out.println("Cannot transfer negative funds");
			return 0;
		}
		else {
			this.balance = balance - transferVal;
			db.updateAccount(db.getAccount(accountNumber), db.getAccount(accountNumber1));
			if(db.getAccount(accountNumber).parseActive() == true) { 
				balance = balance + transferVal;
				System.out.println("Transfer to Acct " + accountNumber + " complete.");
				return 1;
			}
			else {
				System.out.println("Invalid account number. Please try again.");
				return -2;
			}
		}
	}
}
