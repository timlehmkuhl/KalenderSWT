package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.cj.jdbc.CallableStatement;
import com.mysql.cj.xdevapi.Statement;

import model.Kalender;
import model.Termin;
import model.User;

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
		
		String call = "call addUser(?, ?)"; //stored procedure
		try (java.sql.CallableStatement stmt = connection.prepareCall(call)) {
			stmt.setString(1, userName);
			stmt.setString(2, Password);
		stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	
	public void logIn(String userName, String Password) {
			
			//Methode die userName und Password auf ungueltige Zeichen checkt
		try {
			String sql = "select * from users where " + "name = '" + userName + "' and passwordHash = '" + Password +"'";
			java.sql.Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery(sql);
	
			if(res.next()) {
				//login succesfull
				try {
					String getTermine = "select * from " + userName;
					java.sql.Statement stmt2 = connection.createStatement();
					
					ResultSet termine = stmt2.executeQuery(getTermine);
					List<Termin> dbTermine = new LinkedList<>();
					while (termine.next()) {
						dbTermine.add(new Termin(termine.getString("name"), termine.getTimestamp("startZeit"), termine.getTimestamp("endZeit"), 
								termine.getString("Farbe"), termine.getString("Ort"), termine.getString("Notiz"), termine.getString("Icon")));
					}
					User.regUser(userName, new Kalender(dbTermine));
					System.out.println("Login erfolgreich");
				} catch (SQLException e) {
					e.printStackTrace(); 
				}
				
			} else {
				//failed
			}
			
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}



	public void addTermin(Termin t) {
		if(User.getInstanz() == null) {
			//error
		} else {
			try {
				
				//prepare Variables
				String name;
				String sZeit;
				String eZeit;
				String farbe;
				String ort;
				String notiz;
				String icon;
				
				if (t.getName() == null) {
					name = "NULL, ";
				} else {
					name = "'" + t.getName() + "', ";
				}
				if (t.getStartZeit() == null) {
					 sZeit = "NULL, ";
				} else {
					 sZeit = "'" + t.getStartZeit() + "', ";
				}
				if (t.getEndZeit() == null) {
					 eZeit = "NULL, ";
				} else {
					 eZeit = "'" + t.getEndZeit() + "', ";
				}
				if (t.getFarbe() == null) {
					 farbe = "NULL, ";
				} else {
					 farbe = "'" + t.getFarbe() + "', ";
				}
				if (t.getOrt() == null) {
					 ort = "NULL, ";
				} else {
					 ort = "'" + t.getOrt() + "', ";
				}
				if (t.getNotiz() == null) {
					 notiz = "NULL, ";
				} else {
					 notiz = "'" + t.getNotiz() + "', ";
				}
				if (t.getIcon() == null) {
					 icon = "NULL";
				} else {
					 icon = "'" + t.getIcon() + "'";
				}
				
				String addTermin = "insert into " + User.getInstanz().getUsername() 
								+ " (name, startZeit, endZeit, Farbe, Ort, Notiz, Icon)"
						+ "values(" + name + sZeit + eZeit + farbe + ort + notiz + icon + ")";
				
				System.out.println(addTermin);
				java.sql.Statement add = connection.createStatement();
				add.executeUpdate(addTermin);
				
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		}
	}
	
	
	
}
