package model;

import java.util.Date;

import database.Datenbank;

public class Termin {
	
	private String Name;
	private Date StartZeit;
	private Date EndZeit;
	private String Ort;
	private String Notiz;
	private String Icon;
	private String Farbe;
	
	
	public Termin(String name, Date startZeit, Date endZeit, String farbe, String ort, String notiz, String icon) {
		Name = name;
		StartZeit = startZeit;
		EndZeit = endZeit;
		Ort = ort;
		Notiz = notiz;
		Icon = icon;
		Farbe = farbe;
	}

	

	public String toString() {
		return Name + StartZeit.toString() + EndZeit.toString();
	}

	public String getName() {
		return Name;
	}

	public Date getStartZeit() {
		return StartZeit;
	}

	public Date getEndZeit() {
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
	
	
}
