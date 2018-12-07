/**
 * Just like last time, the ATM class is responsible for managing all
 * of the user interaction. This means login procedures, displaying the
 * menu, and responding to menu selections. In the enhanced version, the
 * ATM class will have the added responsibility of interfacing with the
 * Database class to write and read information to and from the database.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */
/* The `ATM` class is responsible for managing the interaction between the customer and ATM. Most of the logic will be implemented in this class. It should meet the specifications outlined below.

 * Display a simple main menu.
   	- Open account
   	- Login
   	- Quit
 * Display a more complex submenu after logging in.
   	- Deposit funds
   	- Withdraw funds
   	- Transfer funds
   	- View balance
   	- View personal information
   	- Update personal infromation
   	- Close account
   	- Logout
 * Respond accordingly to each of the menu options.

   The expected inputs for a program like this will understandably vary. It is your responsibility to handle this. Your program needs to be able to handle anything a customer might throw at it. Simply put, it should never crash.
*/
import java.util.Scanner;

public class ATM {

	Scanner in = new Scanner(System.in);
	private BankAccount account;
	private Database db;
		/**
		 * Main method.
		 * 
		 * @param args
		 */
		
	public static void main(String[] args) {		
//		ATM atm = new ATM(new BankAccount());	
//		atm.menu();
			// TODO
			
			// you need to start the program by calling some method of the ATM class
	}

	
	public ATM(BankAccount account) {
		this.account = account;
	}
	
	public BankAccount getAccount() {
		return account;
	}
	public void setAccount(BankAccount account) {
		this.account = account;
	}
	
	public void menu() {
		int exit = 1;
		System.out.println("Welcome!");
		do {
			System.out.println("Available actions include: \n\t 1 - Open Account \n\t 2 - Login\n\t 3 - exit");
			System.out.println("Please enter the number of the option you are looking for: \n");
			int options = in.nextInt();
			
			switch(options) {
				case 1:
					System.out.println("Please enter your information as prompted now");
					System.out.println("Please enter your first name: ");
					String newUserFName = in.nextLine();
					
					account.getAccntHolder().setFName(newUserFName);
					System.out.println("Please enter your last name: ");
					String newUserLName = in.nextLine();
					account.getAccntHolder().setLName(newUserLName);
					System.out.println("Please enter your desired pin: ");
					int newUserPin = in.nextInt();
					account.getAccntHolder().setPin(newUserPin);
					System.out.println("Please enter your date of birth: ");
					String newUserDOB = in.nextLine();
					account.getAccntHolder().setDOB(newUserDOB);
					System.out.println("Please enter your phone number: ");
					String newUserPhoneNum = in.nextLine();
					account.getAccntHolder().setPhoneNum(newUserPhoneNum);
					System.out.println("Please enter your address: ");
					String newUserAddy = in.nextLine();
					account.getAccntHolder().setAddress(newUserAddy);
					System.out.println("Please enter your city: ");
					String newUserCity = in.nextLine();
					account.getAccntHolder().setCity(newUserCity);
					System.out.println("Please enter your state: ");
					String newUserState = in.nextLine();
					account.getAccntHolder().setState(newUserState);
					System.out.println("Please enter your zip code: ");
					String newUserZip = in.nextLine();
					account.getAccntHolder().setZip(newUserZip);
					break;
//WILL NEED TO ADD NEW USER TO DATABASE WHEN I FIGURE THAT ONE OUT
				case 2: 
					exit = 0;
					break;
				case 3:
					exit = -1;
					break;
				default:
					System.out.println("Invalid option. Please enter the number of the option you are looking for.");
					break;
			}
		}
		while(exit != 0 || exit != -1);			
					
					
		if(exit == 0) {			
			System.out.println("What is your account number: \n");
			account.setAccountNumber(in.nextLong());
			System.out.println("Please input your pin to continue, or enter 0 to reset your pin: \n");
			int pinCheck = in.nextInt();
			if(pinCheck == 0) {
				System.out.println("Please enter your old pin: ");
				int oldPin = in.nextInt();
				account.getAccntHolder().modifyPin(oldPin);
			}
			while(account.getAccntHolder().getPin() != pinCheck) {
				System.out.println("Incorrect Pin. Please try again. \n");
				System.out.println("Please input your pin to continue, or enter 0 to reset your pin: \n");
				pinCheck = in.nextInt();
				if(pinCheck == 0) {
					System.out.println("Please enter your old pin: ");
					int oldPin = in.nextInt();
					account.getAccntHolder().modifyPin(oldPin);
				}
			}
			
			in.nextLine();
			/*The `ATM` class is responsible for managing the interaction between the customer and ATM. Most of the logic will be implemented in this class. It should meet the specifications outlined below.

			 * Display a simple main menu.
   				- Open account
   				- Login
   				- Quit
			 * Display a more complex submenu after logging in.
   				- Transfer funds
   				- Close account
   				- Logout
		 	* Respond accordingly to each of the menu options.*/
			
			int escape = 0;
			do {
				System.out.println("Available actions include: \n\t1 - show balance\n\t2 - deposit\n\t3 - withdraw \n\t4 - transfer funds \n\t5 - view user information \n\t6 - update user information \n\t7 - close account \n\t0 - log out");
				System.out.println("Please select the number of an option now: \n");
				int action = in.nextInt();
				
				switch(action) {
					case 1:
						System.out.println("The balance for account " + account.getAccountNumber() + " is currently: " + account.getBalance() + "\n");
						break;
					case 2:
						double depositVal = -1;
						while(depositVal != 0) {
							System.out.println("How much would you like to deposit? enter 0 to cancel\n");
							depositVal = in.nextDouble();
							if (depositVal <= -1) {
								System.out.println("You cannot deposit negative money. Did you mean \'withdraw\'? Please enter a valid deposit to continue or enter 0 to exit \n");
								depositVal = in.nextDouble();
								while(depositVal != 0) {
									account.deposit(depositVal);
									System.out.println("Deposit successful. Your new balance is " + account.getBalance() + ".\n");
									System.out.println("To make another deposit, please enter another value. To exit the deposit window please enter 0");
									depositVal = in.nextDouble();
								}
								if(depositVal == 0) {
									in.nextLine();
	
									break;							
								}
							}
							else if(depositVal == 0) {
								in.nextLine();
	
								break;							
							}
							else {
								while(depositVal != 0) {
									account.deposit(depositVal);
									System.out.println("Deposit successful. Your new balance is " + account.getBalance() + ".\n");
									System.out.println("To make another deposit, please enter another value. To exit the deposit window please enter 0");
									depositVal = in.nextDouble();
								}
							}
						}
						break;
					case 3:
						double withdrawVal = -1;
						while(withdrawVal != 0) {
							System.out.println("Your current balance is " + account.getBalance() + ". How much would you like to withdraw? or enter 0 to exit.\n");
							withdrawVal = in.nextDouble();
							if(withdrawVal > account.getBalance()) {
								System.out.println("You cannot withdraw more money than is in your account. Please enter a valid withdraw value or enter 0 to cancel. \n");
							}
							else {
								while(withdrawVal != 0) {
									
									if(withdrawVal > account.getBalance()) {
										System.out.println("You cannot withdraw more money than is in your account. Please enter a valid withdraw value or enter 0 to cancel. \n");
									}
									if (withdrawVal <= -1) {
										System.out.println("You cannot withdraw negative money. Did you mean \'deposit\'? Please enter a valid withdrawl to continue or enter 0 to exit \n");
										withdrawVal = in.nextDouble();
										while(withdrawVal != 0) {
											account.withdraw(withdrawVal);
											System.out.println("Withdraw successful. Your new balance is " + account.getBalance() + ".\n");
											System.out.println("To make another withdrawl, please enter another value. To exit the withdrawl window please enter 0");
											withdrawVal = in.nextDouble();
										}
										if(withdrawVal == 0) {
											in.nextLine();
	
											break;							
										}
									withdrawVal = in.nextDouble();
									}
									account.withdraw(withdrawVal);
									if(withdrawVal > account.getBalance()) {
										System.out.println("You cannot withdraw more money than is in your account. Please enter a valid withdraw value or enter 0 to cancel. \n");
										withdrawVal = in.nextDouble();
									}
									else {
									System.out.println("Withdraw successful. Your new balance is " + account.getBalance() + ".\n");
									System.out.println("To make another withdrawl, please enter another value. To exit the withdrawl window please enter 0\n"); 
									}
								}
							}
							if(withdrawVal == 0) {						
								in.nextLine();
								break;
							}
						}
						break;
					case 4:
						System.out.println("Please enter the receiver account number now: ");
						long destAcct = in.nextLong();
						System.out.println("How much would you like to transfer?");
						double transferVal = in.nextDouble();
						account.transfer(transferVal, destAcct);
						break;
					case 5:
						System.out.println("Account Details: \n");
						System.out.println("Account Number: " + account.getAccountNumber());
						System.out.println("First Name: " + account.getAccntHolder().getFName());
						System.out.println("Last Name: " + account.getAccntHolder().getLName());
						System.out.println("Pin: " + account.getAccntHolder().getPin());
						System.out.println("Date Of Birth: " + account.getAccntHolder().getDob());
						System.out.println("Phone Num: " + account.getAccntHolder().getPhoneNum());
						System.out.println("Address: " + account.getAccntHolder().getAddress());
						System.out.println("City: " + account.getAccntHolder().getCity());
						System.out.println("State: " + account.getAccntHolder().getState());
						System.out.println("Zip: " + account.getAccntHolder().getZip() + "\n");
						break;
					case 6:
						int cancel = 0;
						do{
							System.out.println("To update information, select the information which you would like to change: ");
							System.out.println("\t1 - Pin \n\t 2 - PhoneNumber \n\t 3 - Address \n\t 4 - City \n\t5 - State \n\t6 - Zip \n\t 0 - cancel");
							int change = in.nextInt();
							switch (change) {
								case 1: 
									System.out.println("Please enter your old pin now to change your pin, or press 0 to cancel: ");
									int oldPin = in.nextInt();
									if(oldPin == 0) {
										break;
									}
									account.getAccntHolder().modifyPin(oldPin);
									break;
								case 2:
									System.out.println("To modify your phone number, please enter 1 now. Otherwise please enter 0 to cancel");
									int a = in.nextInt();
									if(a == 0) {
										break;
									}
									account.getAccntHolder().modifyPhoneNum();
									break;
								case 3: 
									System.out.println("To modify your address, please enter 1 now. Otherwise please enter 0 to cancel");
									int b = in.nextInt();
									if(b == 0) {
										break;
									}
									account.getAccntHolder().modifyAddress();
									break;
								case 4:
									System.out.println("To modify your city, please enter 1 now. Otherwise please enter 0 to cancel");
									int c = in.nextInt();
									if(c == 0) {
										break;
									}
									account.getAccntHolder().modifyCity();
									break;
								case 5:
									System.out.println("To modify your state, please enter 1 now. Otherwise please enter 0 to cancel");
									int d = in.nextInt();
									if(d == 0) {
										break;
									}
									account.getAccntHolder().modifyState();
									break;
								case 6:
									System.out.println("To modify your zip, please enter 1 now. Otherwise please enter 0 to cancel");
									int e = in.nextInt();
									if(e == 0) {
										break;
									}
									account.getAccntHolder().modifyZip();
									break;
								case 0:
									cancel = -1;
									break;
								default:
									System.out.println("Invalid option. Please try again or press 0 to cancel");
									break;
									
							}
						}
						while(cancel != -1);
					case 0:
						System.out.println("Thank you.\n");
						db.updateAccount(db.getAccount(accountNumber), null);
						escape = -1;
						break;
					default:
						System.out.println("Invalid option. Please try again \n");
						break;
				}
			}
			while(escape != -1);
		}
		System.out.println("Have a nice day!\n");
	}
}