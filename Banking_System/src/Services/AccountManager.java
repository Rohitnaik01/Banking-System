package Services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Models.Accounts;
import Models.Users;

public class AccountManager {
	
	private Connection connection;
	Scanner sc;
	private Users user;
	private Accounts account;
	
	public AccountManager(Connection connection, Scanner sc) {
		this.connection = connection;
		this.sc = sc;
	}
	
	public Accounts createBankAccount() {
		sc.nextLine();
		System.out.print("Enter your name: ");
		String holderName = sc.nextLine();
		System.out.print("Enter your email: ");
		String holderEmail = sc.nextLine();
		System.out.print("Create your Security Pin: ");
		int securityPin = sc.nextInt();
		
		this.account = new Accounts(holderName, holderEmail, securityPin);
		
		this.account.generateAccountNumber();
		
		try {
			String query = "insert into accounts (account_no, holder_name, holder_email, security_pin) values(?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, this.account.getAccountNumber());
			statement.setString(2, this.account.getHolderName());
			statement.setString(3, this.account.getHolderEmail());
			statement.setInt(4, this.account.getSecurityPin());
			
			int rowsUpdated = statement.executeUpdate();
			if(rowsUpdated > 0) {
				System.out.println("Bank account successfully created!! Here is your account number");
				System.out.println(this.account.getAccountNumber());
			} else {
				System.out.println("Bank account creation failed! Try again.");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return this.account;
	}
	
}
