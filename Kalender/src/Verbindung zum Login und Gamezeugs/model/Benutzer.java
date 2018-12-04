package Verbindung zum Login und Gamezeugs.model;

import java.util.LinkedList;

public class Benutzer {

	private int id;

	private String benutzername;

	private String password;

	private String email;

	private int berechtigungslevel;

	private LinkedList<Antwort> antwortenhistorie;

	private int highscore;

	private LinkedList<Achievement> achievements;

	public Benutzer(String benutzername, String password, String email) {

	}

	public int getId() {
		return 0;
	}

	public String getBenutzername() {
		return null;
	}

	public void setBenutzername(String benutzername) {

	}

	public void setPassword(String password) {

	}

	public String getEmail() {
		return null;
	}

	public void setEmail(String email) {

	}

	public int getBerechtigungslevel() {
		return 0;
	}

	public int getHighscore() {
		return 0;
	}

	public void setHighscore(int highscore) {

	}

	public LinkedList<Achievement> getAchievements() {
		return null;
	}

	public void addAchievement(Achievement achievement) {

	}

	private void exportToPdf() {

	}

	public LinkedList<Antwort> bildeHistorie() {
		return null;
	}

}
