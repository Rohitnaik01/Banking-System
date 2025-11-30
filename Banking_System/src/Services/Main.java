package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import Models.Accounts;
import Models.Users;

public class Main {
	static private final String url = "jdbc:mysql://127.0.0.1:3306/bankdb";
	static private final String username = "root";
	static private final String password = "Rohit123";
	static private Users user;
	static private Accounts account;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(false);
			UserManager userManager = new UserManager(connection, sc);
			AccountManager accountManager = new AccountManager(connection, sc);
			
			while(true) {
				
				System.out.println("Welcome to Banking Management System");
				System.out.println("----------------------------------------------");
				System.out.println();
				System.out.println("What do you want to do?");
				System.out.println();
				System.out.println("1. Create a new account? Sign up");
				System.out.println("2. Already have an account! Log in");
				
				byte choice = sc.nextByte();
				switch(choice) {
					case 1: user = userManager.signUp();
					break;
					case 2: user = userManager.login();
					break;
					default: System.out.println("Invalid choice! please choose valid choice");
				}
				
				if(user != null) {
					account = accountManager.selectAccount(user.getEmail());
				}
				
				innerloop:
				while(user != null) {
					System.out.println("1. Create Bank Account");
					System.out.println("2. Select Bank Account");
					System.out.println("3. Deposit Money");
					System.out.println("4. Delete user account");
					System.out.println("5. Log out");
					
					int ch = sc.nextInt();
					switch(ch) {
						case 1 : account = accountManager.createBankAccount(user.getEmail());
						break;
						case 2 : account = accountManager.selectAccount(user.getEmail());
						break;
						case 3: account = accountManager.depositMoney(account.getAccountNumber());
						break;
						case 4 : user = userManager.deleteUser();
						break;
						case 5: System.out.println("Logging out!");
								user = null;
								account = null;
						break innerloop;
					}
				}
			}
	
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
