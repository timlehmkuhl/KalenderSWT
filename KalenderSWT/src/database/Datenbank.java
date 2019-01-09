package database;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
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
	
	/**
	 * 
	 */
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
				connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306?user=kalender&password=swt2018/2019&serverTimezone=CET");
				System.out.println("Connection Sucsessfull");
				
				String selectTable = "use swt20182019;";
				java.sql.Statement stmt2 = connection.createStatement();
				ResultSet termine = stmt2.executeQuery(selectTable);
				
				
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
			Password = PasswordManagement.generateStorngPasswordHash(Password);
			System.out.println(Password);
			stmt.setString(1, userName);
			stmt.setString(2, Password);
		stmt.execute();
		} catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace(); 
		}
	}
	
	/**
	 * 
	 * @param userName
	 * @param Password
	 */
	public void logIn(String userName, String Password) {
			
			//Methode die userName und Password auf ungueltige Zeichen checkt
		try {
			String sql = "select * from users where " + "name = '" + userName +"'";
			java.sql.Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			System.out.println("just outside");
			if(res.next() && PasswordManagement.validatePassword(Password, res.getString("passwordHash"))) {
				//login succesfull
				System.out.println("in the if");
				try {
					int MonthViewed = Calendar.getInstance().get(Calendar.MONTH) + 1; //+1 Weil Januar sonnst 0 Waere bei SQL Januar aber 1 ist.
					int YearViewed = Calendar.getInstance().get(Calendar.YEAR);
					
					String getTermine = "select * from " + userName + " WHERE MONTH(startZeit) = " + MonthViewed + " AND YEAR(startZeit) = " + YearViewed;
					System.out.println(getTermine);
					java.sql.Statement stmt2 = connection.createStatement();
					
					ResultSet termine = stmt2.executeQuery(getTermine);
					List<Termin> dbTermine = new LinkedList<>();
					while (termine.next()) {
						dbTermine.add(new Termin(termine.getInt("terminID"), termine.getString("name"), termine.getTimestamp("startZeit"), termine.getTimestamp("endZeit"), 
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
			
		} catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace(); 
		}
	}

	public void syncMonth(int month, int year){
		month++;
		try {
			String getTermine = "select * from " + User.getInstanz().getUsername() + " WHERE MONTH(startZeit) = " + month + " AND YEAR(startZeit) = " + year;
			java.sql.Statement stmt3 = connection.createStatement();
			System.err.println(getTermine);
			ResultSet termine = stmt3.executeQuery(getTermine);
			System.err.println("Sync Executed");
			while (termine.next()) {
				if(!User.getInstanz().getKalender().terminLoaded(termine.getInt("terminID"))) {
					User.getInstanz().getKalender().loadTermin(new Termin(termine.getInt("terminID"), termine.getString("name"), termine.getTimestamp("startZeit"), termine.getTimestamp("endZeit"), 
							termine.getString("Farbe"), termine.getString("Ort"), termine.getString("Notiz"), termine.getString("Icon")));
					System.err.println(termine);
				}
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
				
				
				//der Termin bekommt von der Datenbank eine ID, die muessen wir abfragen um im lokalen System Die ID zu haben
				try {
					String getID = "select MAX(terminID) as lastID from " + User.getInstanz().getUsername();
					java.sql.Statement stmt4 = connection.createStatement();
					
					ResultSet ID = stmt4.executeQuery(getID);
					int foundID = 0;
					while (ID.next()) {
						foundID = ID.getInt("lastID");
					}
			
					t.setID(foundID);	
					
				} catch (SQLException e) {
					e.printStackTrace(); 
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		}
	}
	
	
	
}
