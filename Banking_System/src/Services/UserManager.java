package Services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Models.Users;

public class UserManager {
	private Connection connection;
	Scanner sc;
	private Users user;
	
	public UserManager(Connection connection, Scanner sc) {
		this.connection = connection;
		this.sc = sc;
	}
	
	public boolean isCred(String email, String password) {
		String query = "select * from users where email = ? and password = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return true;
			} else {
				return false;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean alreadySignUp(String email) {
		try {
			String query = "select * from users where email = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		    return true;
		}
		
	}
	
	public Users signUp() {
		sc.nextLine();
		System.out.print("Enter your name: ");
		String name = sc.nextLine();
		System.out.print("Enter your email: ");
		String email = sc.next();
		System.out.print("Enter your mobile no.: ");
		long mobile = sc.nextLong();
		System.out.print("Create your password: ");
		String password = sc.next();
		
		this.user = new Users(name, email, mobile, password);
		try {
			String query = "insert into users values(?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getName());
			statement.setString(3, user.getMobile());
			statement.setString(4, user.getPassword());
			

			if(!alreadySignUp(user.getEmail())) {
				statement.executeUpdate();
				System.out.println("User account created sccessfully");
				return this.user;
			} else {
				System.out.println("Already Signed up! Please login");
				return null;
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Users login() {
		try {
			System.out.print("Enter email: ");
			String email = sc.next();
			if(alreadySignUp(email)) {
				System.out.print("Enter password:");
				String password = sc.next();
				if(isCred(email, password)) {
					System.out.println("Logged In!");
					String query = "select * from users where email = ?";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, email);
					ResultSet result = statement.executeQuery();
					
					if(result.next()) {
						this.user = new Users(result.getString("name"),result.getString("email"),result.getLong("mobile"),result.getString("password"));
					}
					return this.user;
				} else {
					System.out.println("Wrong password!");
					return null;
				}
			} else {
				System.out.println("User not exists!");
				return null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void deleteUser() {
		try {
			String query = "delete from users where email = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getEmail());
			int rowsUpdated = statement.executeUpdate();
			if(rowsUpdated > 0) {
				System.out.println("User account deleted successfully!");
			} else {
				System.out.println("Account Deletion failed ! Please try again!");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
