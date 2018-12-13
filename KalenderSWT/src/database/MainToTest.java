package database;

import java.sql.Date;
import java.sql.Timestamp;

import model.Termin;
import model.User;

public class MainToTest {

	public static void main(String[] args) {
		Datenbank.getInstanz();
		Datenbank.getInstanz().connect();
		Datenbank.getInstanz().addUser("DeineMudda", "keineAhnung");	
		Datenbank.getInstanz().logIn("DeineMudda", "keineAhnung");
			
		
		
		System.out.println(User.getInstanz());
		User.getInstanz().addTermin(new Termin("Gassi Gehn", new Timestamp(1_220_227_600L * 1_000L ), new Timestamp(1_220_227_200L * 1_000L), "rot", null, "lol", null));
		System.out.println(User.getInstanz());
		Datenbank.getInstanz().disconnect();

	}

}
