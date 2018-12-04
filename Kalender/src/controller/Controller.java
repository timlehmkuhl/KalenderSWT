package controller;

import View.LoginScreen;
import View.Menuansicht;
import View.Profilansicht;
import View.Spielansicht;
import DAO.FrageDAO;
import DAO.BenutzerDAO;
import DAO.DAO_T_;
import Verbindung zum Login und Gamezeugs.model.Benutzer;
import Verbindung zum Login und Gamezeugs.model.Frage;
import Verbindung zum Login und Gamezeugs.model.Spiel;
import View.FrageFormular;
import Verbindung zum Login und Gamezeugs.model.Antwort;
import java.util.LinkedList;

public class Controller {

	private LoginScreen loginScreen;

	private Menuansicht menuansicht;

	private Profilansicht profilansicht;

	private Spielansicht spielansicht;

	private FrageDAO frageDAO;

	private BenutzerDAO benutzerDAO;

	private DAO_T_ dAO_T_;

	private Benutzer benutzer;

	private Frage frage;

	private Spiel spiel;

	private FrageFormular frageFormular;

	public Benutzer login(String benutzername, String password) {
		return null;
	}

	public Benutzer registrieren(String name, String password, String email) {
		return null;
	}

	public void hotkeysBelegen() {

	}

	public Gegenspieler mpSuchen() {
		return null;
	}

	public Spiel spielSpielenMP(Gegenspieler gegner) {
		return null;
	}

	public Spiel spielSpielenSP() {
		return null;
	}

	public boolean antwortAbgebenMP(Antwort antwort) {
		return false;
	}

	public boolean antwortAbgebenSP(Antwort antwort) {
		return false;
	}

	public void menuAnzeigen() {

	}

	public void frageEinsenden(Frage frage) {

	}

	public Frage frageFormulieren(String text, LinkedList<Antwort> antworten) {
		return null;
	}

	public void leistungsübersichtAnzeigen() {

	}

	private int getPunktestandSpieler2FromConnection() {
		return 0;
	}

}
