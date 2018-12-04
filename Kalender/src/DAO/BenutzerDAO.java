package DAO;

import Verbindung zum Login und Gamezeugs.model.Benutzer;

public class BenutzerDAO extends DAO_T_ {

	public boolean existsById(int id) {
		return false;
	}

	public boolean existsByEmail(String email) {
		return false;
	}

	public Benutzer getByEmail(String email) {
		return null;
	}

}
