import java.io.IOException;

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
		
	private static long generatedAccountNumber = 99000001L;
		
	private long accountNumber;
	private double balance;
	private AccntHolder accntHolder;
	private Database db;
	
	public BankAccount(String account) {
		this.accountNumber = parseAcctNum(account);
		this.balance = parseBal(account);
		this.accntHolder = new AccntHolder(parsePin(account), parseFName(account), parseLName(account), parseDateOB(account), parsePNum(account), parseAddy(account), parseCity(account), parseState(account), parseZip(account));
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
		System.out.println("gets this far (setAccountNum)");
		this.accountNumber = accountNumber;
	}
		
	public void setBalance(double balance) {
		this.balance = balance;
	}
		
	public void setAcctHolder(AccntHolder accntHolder) {
		this.accntHolder = accntHolder;
	}

	public long parseAcctNum(String account) {
		String AcctNum = account.substring(0,9);
		long AccntNum = Long.parseLong(AcctNum);
		return AccntNum;
	}
	
	public int parsePin(String account) {
		String Pin1 = account.substring(9,13);
		int Pin = Integer.parseInt(Pin1);
		return Pin;
	}
	
	public double parseBal(String account) {
		
		double balance = Double.parseDouble(account.substring(13,28));
		return balance;
	}
	
	public String parseLName(String account) {
		
		String LastN= account.substring(28, 48);
		return LastN;
	}
	
	public String parseFName(String account) {
		
		String FirstN= account.substring(48, 63);
		return FirstN;
	}
	
	public String parseDateOB(String account) {
		
		String DateOB= account.substring(63, 71);
		return DateOB;
	}
	
	public String parsePNum(String account) {
		
		String PNum= account.substring(71, 81);
		return PNum;
	}
	
	public String parseAddy(String account) {
		
		String Addy= account.substring(81, 111);
		return Addy;
	}
	
	public String parseCity(String account) {
		
		String City= account.substring(111, 141);
		return City;
	}

	public String parseState(String account) {
		
		String State= account.substring(141, 143);
		return State;
	}

	public String parseZip(String account) {
		
		String Zip= account.substring(143,148);
		return Zip;
	}

	public int deposit(double depositVal) throws IOException {
		if(depositVal < -1) {
			return 0;
		}
		else {
			this.balance = balance + depositVal;
			db.updateAccount(db.getAccount(accountNumber), null);
			return 1;
		}
	}
		
	public int withdraw(double withdrawVal) throws IOException {
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
	
	public int transfer(double transferVal, long accountNumber1) throws IOException {
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
			if(db.getAccount(accountNumber1) != null) { 
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
