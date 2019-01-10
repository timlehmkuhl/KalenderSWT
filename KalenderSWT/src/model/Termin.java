package model;

import java.sql.Timestamp;

import database.Datenbank;

public class Termin {
	
	private int ID;
	private String Name;
	private Timestamp StartZeit;
	private Timestamp EndZeit;
	private String Ort;
	private String Notiz;
	private String Icon;
	private String Farbe;
	
	/**
	 * Konstruktor fuer Termine die aus der DB geladen werden 
	 * @param iD
	 * @param name
	 * @param startZeit
	 * @param endZeit
	 * @param farbe
	 * @param ort
	 * @param notiz
	 * @param icon
	 */
	public Termin(int iD, String name, Timestamp startZeit, Timestamp endZeit, String farbe, String ort, String notiz, String icon) {
		Name = name;
		StartZeit = startZeit;
		EndZeit = endZeit;
		Ort = ort;
		Notiz = notiz;
		Icon = icon;
		Farbe = farbe;
		ID = iD;
	}

	/**
	 * Konstruktor ohne ID zum erstellen neuer Termine. ID wird spaeter von DB zugewiesen
	 * @param name
	 * @param startZeit
	 * @param endZeit
	 * @param farbe
	 * @param ort
	 * @param notiz
	 * @param icon
	 */
	public Termin(String name, Timestamp startZeit, Timestamp endZeit, String farbe, String ort, String notiz, String icon) {
		Name = name;
		StartZeit = startZeit;
		EndZeit = endZeit;
		Ort = ort;
		Notiz = notiz;
		Icon = icon;
		Farbe = farbe;
	}

	public String toString() {
		return  Integer.toString(ID) + " " + Name + StartZeit.toString() + EndZeit.toString();
	}

	public String getName() {
		return Name;
	}

	public Timestamp getStartZeit() {
		return StartZeit;
	}

	public Timestamp getEndZeit() {
		return EndZeit;
	}

	public String getOrt() {
		return Ort;
	}

	public String getNotiz() {
		return Notiz;
	}

	public String getIcon() {
		return Icon;
	}

	public String getFarbe() {
		return Farbe;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int i) {
		this.ID = i;
	}
	
	
}
