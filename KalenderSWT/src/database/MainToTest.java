package database;

import java.sql.Date;
import java.sql.Timestamp;

import model.Termin;
import model.User;

public class MainToTest {

	public static void main(String[] args) {
		Datenbank.getInstanz();
		Datenbank.getInstanz().connect();
		//Datenbank.getInstanz().addUser("S�renWerth", "asdw");	
		Datenbank.getInstanz().logIn("S�renWerth", "asdw");
			
		
		
		System.out.println(User.getInstanz());
		User.getInstanz().addTermin(new Termin("Aufstehen", new Timestamp(1_220_267_600L * 1_000L ), new Timestamp(1_220_227_200L * 1_000L), "rot", null, "lol", null));
		System.out.println(User.getInstanz());
		Datenbank.getInstanz().disconnect();

	}

}
