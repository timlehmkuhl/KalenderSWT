package database;

import model.User;

public class MainToTest {

	public static void main(String[] args) {
		Datenbank.getInstanz();
		Datenbank.getInstanz().connect();
		Datenbank.getInstanz().logIn("Hans", "Peter");
		//Datenbank.getInstanz().addUser("Hans", "Peter");		
		
		
		System.out.println(User.getInstanz());
		
		Datenbank.getInstanz().disconnect();

	}

}
