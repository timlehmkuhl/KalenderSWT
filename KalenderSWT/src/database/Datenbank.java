package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
				//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SWT2018", "Kalender", "SWT2018");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/SWT2018?user=Kalender&password=SWT2018&serverTimezone=CET");
			} catch (SQLException ex){
				ex.printStackTrace();
			}
		}
	}
	
	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection = null;
		}
	}
	
	
}
