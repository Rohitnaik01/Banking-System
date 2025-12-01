package Services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public Accounts createBankAccount(String email) {
		sc.nextLine();
		System.out.print("Enter your name: ");
		String holderName = sc.nextLine();
		System.out.print("Create your Security Pin: ");
		int securityPin = sc.nextInt();
		
		this.account = new Accounts(holderName, email, securityPin);
		
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
				connection.commit();
			} else {
				System.out.println("Bank account creation failed! Try again.");
				connection.rollback();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return this.account;
	}
	
	
	public Accounts selectAccount(String email) {
		sc.nextLine();
		ArrayList<String> bankAccounts = new ArrayList<String>();
		try {
			String query = "select account_no from accounts where holder_email = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet res = statement.executeQuery();
			
			while(res.next()) {
				bankAccounts.add(res.getString("account_no"));
			}
			
			if(!bankAccounts.isEmpty()) {
				System.out.println("Your Bank Accounts:");
				for(int i = 0; i < bankAccounts.size(); i++) {
					System.out.println((i + 1) + ".  " + bankAccounts.get(i));
				}
				
				System.out.println("Select Bank Account: ");
				int choice = sc.nextInt();
				
				String accQuery = "select * from accounts where account_no = ?;";
				PreparedStatement accStatement = connection.prepareStatement(accQuery);
				accStatement.setString(1, bankAccounts.get(choice - 1));
				ResultSet accRes = accStatement.executeQuery();
			
				if(accRes.next()) {
					this.account = new Accounts(accRes.getString("holder_name"), accRes.getString("holder_email"), accRes.getString("account_no"), accRes.getInt("security_pin"), accRes.getDouble("balance"));
				}
				System.out.println(this.account.toString());
				System.out.println();
				
				return this.account;
			} else {
				System.out.println("No Accounts created! Please create account first");
				return null;
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Accounts depositMoney(String accountNo) {
		sc.nextLine();
		System.out.println("Enter Amount:");
		double amount = sc.nextDouble();
		System.out.print("Enter Security Pin: ");
		int pin = sc.nextInt();
		
		if(pin != this.account.getSecurityPin()) {
			System.out.println("Incorrect Security Pin!");
			return this.account;
		}
		String query = "update accounts set balance = ? where account_no = ?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDouble(1, amount);
			statement.setString(2, accountNo);
			
			int rowsUpdated = statement.executeUpdate();
			
			if(rowsUpdated > 0) {
				connection.commit();
				System.out.println("Amount deposited successfully to account number: " + this.account.getAccountNumber());
				account.setBalance(amount + account.getBalance());
			} else {
				connection.rollback();
				System.out.println("Amount not deposited! Please try again");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return this.account;
	}
	
	public Accounts moneyTransfer() {
		sc.nextLine();
		System.out.println("Enter recipient account number:");
		String rAccNo = sc.nextLine();
		System.out.println("Enter amount to transfer: ");
		double amount = sc.nextDouble();
		System.out.println("Enter Security Pin:");
		int secPin = sc.nextInt();
		
		if(secPin != account.getSecurityPin()) {
			System.out.println("Invalid Security Pin!");
			return account;
		}
		
		try {
			/*
			 * String currBalQuery = "select balance from accounts where account_no = ?;";
			 * PreparedStatement balStatement = connection.prepareStatement(currBalQuery);
			 * balStatement.setString(1, account.getAccountNumber()); ResultSet balRes =
			 * balStatement.executeQuery();
			 * 
			 * double balance = 0; if(balRes.next()) { balance =
			 * balRes.getDouble("balance"); }
			 */
			
			double balance = account.getBalance();
			
			if(balance >= amount) {
				String sendQuery = "update accounts set balance = balance - ? where account_no = ?;";
				PreparedStatement sendStatement = connection.prepareStatement(sendQuery);
				sendStatement.setDouble(1, amount);
				sendStatement.setString(2, account.getAccountNumber());
				int sendRowsUpdated = sendStatement.executeUpdate();
				
				String recieveQuery = "update accounts set balance = balance + ? where account_no = ?;";
				PreparedStatement recieveStatement = connection.prepareStatement(recieveQuery);
				recieveStatement.setDouble(1, amount);
				recieveStatement.setString(2, rAccNo);
				int recieveRowsUpdated = recieveStatement.executeUpdate();
				
				if(sendRowsUpdated > 0 && recieveRowsUpdated > 0) {
					connection.commit();
					System.out.println("Amount transfered to Account no. " + rAccNo);
					account.setBalance(account.getBalance() + amount);
				} else {
					connection.rollback();
					System.out.println("Amount transfer failed!");
				}		
			} else {
				System.out.println("Insufficient balance!");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}
}
