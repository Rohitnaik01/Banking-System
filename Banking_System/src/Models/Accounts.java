package Models;
import java.security.SecureRandom;

public class Accounts {
	private String holderName ;
	private String holderEmail;
	private String accountNumber;
	private int securityPin;
	private long balance;
	
	public Accounts(String holderName, String holderEmail, int securityPin) {
		this.holderName = holderName;
		this.holderEmail = holderEmail;
		this.securityPin = securityPin;
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
	
	public long getBalance() {
		return balance;
	}
	
	public void generateAccountNumber() {
		SecureRandom random = new SecureRandom();
		String account_no = (1000000000L + random.nextLong(9000000000L)) + "";
		this.accountNumber = account_no;
	}
}
