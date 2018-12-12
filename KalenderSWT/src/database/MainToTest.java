package database;

import java.sql.Date;

import model.Termin;
import model.User;

public class MainToTest {

	public static void main(String[] args) {
		Datenbank.getInstanz();
		Datenbank.getInstanz().connect();
		Datenbank.getInstanz().addUser("User1", "wtf");	
		Datenbank.getInstanz().logIn("User1", "wtf");
			
		
		
		System.out.println(User.getInstanz());
		User.getInstanz().addTermin(new Termin("basteln", new Date(10, 11, 12), new Date(10, 11, 12), "rot", null, "lol", null));
		System.out.println(User.getInstanz());
		Datenbank.getInstanz().disconnect();

	}

}
