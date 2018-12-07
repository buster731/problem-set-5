/**
 * Just like last time, the User class is responsible for retrieving
 * (i.e., getting), and updating (i.e., setting) user information.
 * This time, though, you'll need to add the ability to update user
 * information and display that information in a formatted manner.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

import java.util.Scanner;



public class AccntHolder {
	/*### User

	The `User` class is responsible for managing all aspects of the customer's personal information, as outlined below.

	* The customer's first name.
	* The customer's last name.
	* The customer's PIN.
	* The customer's date of birth.
	* The customer's telephone number.
	* The customer's street address.
	* The customer's city.
	* The customer's state.
	* The customer's postal code.

	Generally, the `User` class should have the ability to retrieve and modify each of the above pieces of information. Exceptions to this general rule of thumb are outlined below.

	* In order to modify the PIN, a customer must first enter his or her existing PIN before choosing a new one.
	* A customer should not be permitted to modify his or her first name, last name, or date of birth.
	*/
	

	private Scanner in = new Scanner(System.in);
		
		private int pin;
		private String fName;
		private String lName;
		private String dob;
		private String phoneNum;
		private String address;
		private String city;
		private String state;
		private String zip;
		
		
		public AccntHolder(int pin, String fName, String lName, String dob, String phoneNum, String address, String city, String state, String zip){
			this.pin = pin;
			this.fName = fName;
			this.lName = lName;
			this.dob = dob;
			this.phoneNum = phoneNum;
			this.address = address;
			this.city = city;
			this.state = state;
			this.zip = zip;
		}
		
		public int getPin() {
			return pin;
		}
		
		public String getFName() {
			return fName;
		}
		
		public String getLName() {
			return lName;
		}
		
		public String getDob() {
			return dob;
		}
		
		public String getPhoneNum() {
			return phoneNum;
		}
		
		public String getAddress() {
			return address;
		}
		
		public String getCity() {
			return city;
		}
		
		public String getState() {
			return state;
		}
		
		public String getZip() {
			return zip;
		}
		
		public void setPin(int pin) {
			this.pin = pin;
		}
		
		public void setFName(String fName) {
			this.fName = fName;
		}
		
		public void setLName(String lName) {
			this.lName = lName;
		}
		
		public void setDOB(String dob) {
			this.dob = dob;
		}
		
		public void setPhoneNum(String phoneNum) {
			this.phoneNum = phoneNum;
		}
		
		public void setAddress(String address) {
			this.address = address;
		}
		
		public void setCity(String city) {
			this.city = city;
		}
		
		public void setState(String state) {
			this.state = state;
		}
		
		public void setZip(String zip) {
			this.zip = zip;
		}
		
		public int modifyPin(int oldPin) {
			if(this.pin != oldPin) {
				System.out.println("Incorrect pin.");
				return -1;
			}
			else {
				System.out.println("Please enter your new pin now: ");
				int pin1 = in.nextInt();
				setPin(pin1);
				return 1;
			}
		}
		
		public int modifyPhoneNum() {
			System.out.println("Please enter your new phone number now: ");
			String phoneNum1 = in.nextLine();
			setPhoneNum(phoneNum1);
			return 1;
		}
		
		public int modifyAddress() {
			System.out.println("Please enter your new address now: ");
			String address1 = in.nextLine();
			setAddress(address1);
			return 1;
		}
		
		public int modifyCity() {
			System.out.println("Please enter your new city now: ");
			String city1 = in.nextLine();
			setCity(city1);
			return 1;
		}
		
		public int modifyState() {
			System.out.println("Please enter your new state now: ");
			String state1 = in.nextLine();
			setState(state1);
			return 1;
		}
		
		public int modifyZip() {
			System.out.println("Please enter your new zip now: ");
			String zip1 = in.nextLine();
			setState(zip1);
			return 1;
		}
	}