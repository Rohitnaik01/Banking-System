package Models;
import java.security.SecureRandom;

public class Accounts {
	private String holderName ;
	private String holderEmail;
	private String accountNumber;
	private int securityPin;
	
	Accounts(String holderName, String holderEmail, int securityPin) {
		this.holderName = holderName;
		this.holderEmail = holderEmail;
		this.securityPin = securityPin;
	}
	
	String getHolderName() {
		return holderName;
	}
	
	String getHolderEmail() {
		return holderEmail;
	}
	
	int getSecurityPin() {
		return securityPin;
	}
	
	String getAccountNumber() {
		return accountNumber;
	}
	
	String generateAccountNumber() {
		SecureRandom random = new SecureRandom();
		String account_no = (1000000000L + random.nextLong(9000000000L)) + "";
		this.accountNumber = account_no;
		return accountNumber;
	}
}
