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
			Class.forName("org.mariadb.jdbc.Driver");
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public void connect() {
		if(connection == null) {
			try {
			connection = DriverManager.getConnection("jdbc:mariadb://localhost/SWT2018?user=Kalender&password=SWT2018");
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
