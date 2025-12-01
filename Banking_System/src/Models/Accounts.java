package Models;
import java.security.SecureRandom;

public class Accounts {
	private String holderName ;
	private String holderEmail;
	private String accountNumber;
	private int securityPin;
	private double balance;
	
	public Accounts(String holderName, String holderEmail, int securityPin) {
		this.holderName = holderName;
		this.holderEmail = holderEmail;
		this.securityPin = securityPin;
	}
	
	public Accounts(String holderName, String holderEmail, String accountNumber, int securityPin, double balance) {
		this.holderName = holderName;
		this.holderEmail = holderEmail;
		this.accountNumber = accountNumber;
		this.securityPin = securityPin;
		this.balance = balance;
	}
	
	public void setBalance(double amount) {
		this.balance = amount;
	}
	
	public String getHolderName() {
		return holderName;
	}
	
	public String getHolderEmail() {
		return holderEmail;
	}
	
	public int getSecurityPin() {
		return securityPin;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void generateAccountNumber() {
		SecureRandom random = new SecureRandom();
		String account_no = (1000000000L + random.nextLong(9000000000L)) + "";
		this.accountNumber = account_no;
	}

	@Override
	public String toString() {
		return "Accounts [holderName=" + holderName + ", holderEmail=" + holderEmail + ", accountNumber="
				+ accountNumber + ", securityPin=" + securityPin + ", balance=" + balance + "]";
	}
	
	
}
