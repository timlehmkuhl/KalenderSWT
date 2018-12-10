package model;

import java.util.ArrayList;
import java.util.List;

import database.Datenbank;

public class Kalender {
	private List<Termin> termine = new ArrayList<>();
	private final User keeper;
	
	public Kalender(User u, Termin... termine) {
		this.keeper = u;
		for(Termin t : termine) {
			this.termine.add(t);
		}
	}
	
	public void addTermine(Termin... termine) {
		for(Termin t : termine) {
			this.termine.add(t);
			Datenbank.getInstanz().addTermin(keeper, t);
		}
	}
	
}
