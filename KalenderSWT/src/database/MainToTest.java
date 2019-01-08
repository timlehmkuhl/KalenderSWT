package database;

import java.sql.Date;
import java.sql.Timestamp;

import model.Termin;
import model.User;

public class MainToTest {

	public static void main(String[] args) {
		Datenbank.getInstanz();
		Datenbank.getInstanz().connect();
		//Datenbank.getInstanz().addUser("SörenWerth", "asdw");	
		Datenbank.getInstanz().logIn("SörenWerth", "asdw");
			
		
		
		System.out.println(User.getInstanz());
		Timestamp begin = Timestamp.valueOf("2019-01-07 11:10:10.0");
		Timestamp end = Timestamp.valueOf("2019-01-07 11:30:10.0");
		
		//User.getInstanz().addTermin(new Termin("Auch Im monat", begin, end, "rot", null, "lol", null));
		System.out.println(User.getInstanz());
		//User.getInstanz().setView(9, 2008);
		System.out.println(User.getInstanz());
		view.Monatsansicht.main(null);
		
		Datenbank.getInstanz().disconnect();

	}

}
