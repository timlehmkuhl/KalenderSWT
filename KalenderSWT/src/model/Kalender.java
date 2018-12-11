package model;

import java.util.ArrayList;
import java.util.List;

import database.Datenbank;

public class Kalender {
	private List<Termin> termine = new ArrayList<>();
	
	public Kalender(Termin... termine) {
		for(Termin t : termine) {
			this.termine.add(t);
		}
	}
	
	public void addTermine(Termin... termine) {
		for(Termin t : termine) {
			this.termine.add(t);
			Datenbank.getInstanz().addTermin(t);
		}
	}
	
}
