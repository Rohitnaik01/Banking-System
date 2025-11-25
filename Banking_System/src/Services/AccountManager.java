package Services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import Models.Users;

public class AccountManager {
	
	private Connection connection;
	Scanner sc;
	private Users user;
	
	public AccountManager(Connection connection, Scanner sc) {
		this.connection = connection;
		this.sc = sc;
	}
	
}
