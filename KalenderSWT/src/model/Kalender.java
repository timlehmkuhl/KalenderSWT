package model;

import java.util.ArrayList;
import java.util.Calendar;
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
	
	//Damit keine Termine doppelt aus der db geladen werden
	public boolean terminLoaded(int terminID) {	
		for(Termin t : termine) {
			if(t.getID() == terminID) return true;
		}
		return false;
	}
	
	//Erstellt eine Liste mit allen Tagen des angezeigten Monats, an denen es Termine gibt
	public List<Integer> DaysNotFree(){
		List<Integer> days = new ArrayList<Integer>();
		Calendar c = Calendar.getInstance();
		int year = User.getInstanz().getYearViewed();
		int month = User.getInstanz().getMonthViewed();
		for(Termin t : termine) {
			c.setTime(t.getStartZeit());
			if( c.get(Calendar.MONTH) == month && c.get(Calendar.YEAR) == year) {
				days.add(c.get(Calendar.DAY_OF_MONTH));
			}
		}
		return days;
	}
	
	//Prueft ob es an einem bestimmten Tag ein Termin gibt (Methode wurde durch DaysNotFree ersetzt, 
	//wird zunächst nicht mehr benötigt	
	public boolean terminOnDay(int day) {
		Calendar c = Calendar.getInstance();
		int year = User.getInstanz().getYearViewed();
		int month = User.getInstanz().getMonthViewed();
		for(Termin t : termine) {
			c.setTime(t.getStartZeit());
			System.out.println(Integer.toString(c.get(Calendar.DAY_OF_MONTH)) + " " + Integer.toString(day) + " " + Integer.toString(c.get(Calendar.YEAR)) + " " + Integer.toString(year) + " " + Integer.toString(month) + " " + Integer.toString(c.get(Calendar.MONTH)));
			if(c.get(Calendar.DAY_OF_MONTH) == day && c.get(Calendar.YEAR) == year && c.get(Calendar.MONTH) == month) {
				return true;
			}
		}
		return false;
	}
	
	
	
}
