package model;

import java.util.LinkedList;
import java.util.List;

import database.Datenbank;

public class Kalender {
	private List<Termin> termine = new LinkedList<>();
	
	public Kalender(List<Termin> termine) {
		if(termine != null) {
			this.termine = termine;
		}
	}
	
	public void addTermine(Termin... termine) {		//Zum erstellen von Terminen. Diese werden auch in der Db gespeichert
		for(Termin t : termine) {
			this.termine.add(t);
			Datenbank.getInstanz().addTermin(t);
		}
	}
	
	public void loadTermin(Termin t) {		//Zum laden aus der Datenbank
		this.termine.add(t);
	}
	
	public String toString() {
		String temp = "";
		for(Termin t : termine) {
			temp += t +"\n";
		}
		return temp;
	}
	
	public boolean terminLoaded(int terminID) {
		for(Termin t : termine) {
			if(t.getID() == terminID) return true;
		}
		return false;
	}
	
}
