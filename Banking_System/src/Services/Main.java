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
			UserManager userManager = new UserManager(connection, sc);
			AccountManager accountManager = new AccountManager(connection, sc);
			
			while(user == null) {
				
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
			}
			
			while(user != null) {
				System.out.println("1. Delete account");
				System.out.println("2. Create Bank Account");
				byte ch = sc.nextByte();
				switch(ch) {
					case 1 : user = userManager.deleteUser();
					return;
					case 2 : account = accountManager.createBankAccount();
					return;
				}

			}
	
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
