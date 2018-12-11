package model;

public class User {
	private Kalender kalender;
	private String Username;
	private String Password;
	private String email;
	private Kalender Kalender;
	private Boolean loggedIn;
	
	public User(String Username, Kalender kalender, Boolean loggedIn) {
		this.loggedIn = loggedIn;
		this.Username = Username;
		this.kalender = kalender;
	}
}
