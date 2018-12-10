package database;

public class MainToTest {

	public static void main(String[] args) {
		Datenbank.getInstanz();
		Datenbank.getInstanz().connect();
		Datenbank.getInstanz().addUser("BobDerBaumeister", "CanWeFixIT");
		Datenbank.getInstanz().disconnect();

	}

}
