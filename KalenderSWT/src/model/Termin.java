package model;

import java.util.Date;

public class Termin {
	private String Farbe;
	private String Name;
	private String Ort;
	private Date StartZeit;
	private Date EndZeit;
	private String Notiz;
	private String Icon;
	
	public Termin(String Name, Date StartZeit, Date EndZeit) {
		this.Name = Name;
		this.EndZeit = EndZeit;
		this.StartZeit = StartZeit;
	}
	
	public String toString() {
		return Name + StartZeit.toString() + EndZeit.toString();
	}
	
}
