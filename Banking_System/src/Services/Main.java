package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import Models.Users;

public class Main {
	static private final String url = "jdbc:mysql://127.0.0.1:3306/bankdb";
	static private final String username = "root";
	static private final String password = "Rohit123";
	static private Users user;
	
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
			
			System.out.println("1. Delete account");
			byte ch = sc.nextByte();
			if(ch == 1) {
				userManager.deleteUser();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
