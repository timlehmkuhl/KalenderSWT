package model;

public class Termin {
	private String Farbe;
	private String Name;
	private String Ort;
	private double Datum;
	private String Notiz;
	private String Icon;
	
	public Termin(String Name, String Ort, double Datum) {
		this.Name = Name;
		this.Ort = Ort;
		this.Datum = Datum;
	}
	
}
