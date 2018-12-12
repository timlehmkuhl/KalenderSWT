package model;

import java.util.LinkedList;
import java.util.List;

import database.Datenbank;

public class Kalender {
	private List<Termin> termine = new LinkedList<>();
	
	public Kalender(List<Termin> termine) {
		this.termine = termine;
	}
	
	public void addTermine(Termin... termine) {
		for(Termin t : termine) {
			this.termine.add(t);
			//Datenbank.getInstanz().addTermin(t);
		}
	}
	
	public String toString() {
		String temp = "";
		for(Termin t : termine) {
			temp += t +"\n";
		}
		return temp;
	}
	
}
