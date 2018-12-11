package model;

public class User {
	private Kalender kalender;
	private String Username;
	
	private static User loggedIn = null;
	
	private User(String Username, Kalender kalender) {
		this.Username = Username;
		this.kalender = kalender;
	}
	
	public static void regUser(String Username, Kalender kalender) {
		loggedIn = new User(Username, kalender);
	}
	
	public static User getInstanz() {
		if(loggedIn == null) {
			return null;
		}
		else return loggedIn;
	}
}
//