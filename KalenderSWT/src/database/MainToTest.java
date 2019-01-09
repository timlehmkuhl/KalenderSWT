package database;

import java.sql.Date;
import java.sql.Timestamp;

import model.Termin;
import model.User;

public class MainToTest {

	public static void main(String[] args) {
		Datenbank.getInstanz();
		Datenbank.getInstanz().connect();
		//Datenbank.getInstanz().addUser("Person1", "password");	
		Datenbank.getInstanz().logIn("Person1", "password");
			
		
		
		System.out.println(User.getInstanz());
		Timestamp begin = Timestamp.valueOf("2019-02-07 11:10:10.0");
		Timestamp end = Timestamp.valueOf("2019-02-07 11:30:10.0");
		
		//User.getInstanz().addTermin(new Termin("Erster Termin", begin, end, null, null, "lol", null));
		System.out.println(User.getInstanz());
		//User.getInstanz().setView(9, 2008);

		view.Monatsansicht.main(null);
		
		//Datenbank.getInstanz().disconnect();
		//dd
	}

}
