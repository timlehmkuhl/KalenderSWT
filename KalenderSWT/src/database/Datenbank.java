package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.CallableStatement;

public class Datenbank {
	
	private static Datenbank instanz;
	private static Connection connection;
	public static Datenbank getInstanz() {
		if(instanz == null) {
			instanz = new Datenbank();
		}
		return instanz;
	}
	
	private Datenbank() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} 
	}
	
	public void connect() {
		if(connection == null) {
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost/SWT2018?user=Kalender&password=SWT2018&serverTimezone=CET");
				System.out.println("Connection Sucsessfull");
			} catch (SQLException ex){
				ex.printStackTrace();
			}
		}
	}
	
	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
				System.out.println("Connection Sucsessfully closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	public void addUser(String userName, String  Password) {
		
		String call = "call addUser(?, ?)";
		try (java.sql.CallableStatement stmt = connection.prepareCall(call)) {
			stmt.setString(1, userName);
			stmt.setString(2, Password);
		stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
