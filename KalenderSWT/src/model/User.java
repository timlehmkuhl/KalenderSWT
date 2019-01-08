package model;

import java.util.Calendar;

import database.Datenbank;

public class User {
	private Kalender kalender;
	private String Username;
	private static User loggedIn = null;
	private int MonthViewed;
	private int YearViewed;
	private int DayViewed;
	private static final String[] MonatsName = {"Januar", "Februar", "Maerz", "April", "Mai", "Juni", "Juli", "August", "Sepetember",
			"Oktober", "November", "Dezember"};
	private static final String[] WochenTage = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Sammstag", "Sonntag"};
	
	private User(String Username, Kalender kalender) {
		this.Username = Username;
		this.kalender = kalender;
		MonthViewed = Calendar.getInstance().get(Calendar.MONTH);
		YearViewed = Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static void regUser(String Username, Kalender kalender) {
		loggedIn = new User(Username, kalender);
	}
	
	public static User getInstanz() {
		if(loggedIn == null) {
			return null;
		}
		else return loggedIn;
	}
	
	
	public void addTermin(Termin t) {
		kalender.addTermine(t);
	}
	
	
	public String toString() {
		return Username + "\n" + kalender.toString();
	}
	
	public String getUsername() {
		return Username;
	}
	
	public Kalender getKalender() {
		return kalender;
	}
	
	public int getYearViewed() {
		return YearViewed;
	}
	
	public int getMonthViewed() {
		return MonthViewed;
	}
	
	public void setView(int Month, int Year) {
		if(Month >= 1 && Month <= 12 ) {
			MonthViewed = Month;
			YearViewed = Year;
			Datenbank.getInstanz().syncMonth(MonthViewed, YearViewed);
		}
	}
	
	public void viewNextMonth() {
		if(MonthViewed == 12) {
			MonthViewed = 0;
			YearViewed++;
		} else {
			MonthViewed++;
		}
	}
	
	public void viewPrevMonth() {
		if(MonthViewed == 1) {
			MonthViewed = 12;
			YearViewed--;
		} else {
			MonthViewed--;
		}
	}
	
	public int getDayViewed() {
		return DayViewed;
	}
	
	public void setDayViewed(int day) {
		DayViewed = day;
	}
	
	public static String getMonthName(int month) {
		return MonatsName[month];
	}
	public static String getDayName(int day) {
		day--;
		return WochenTage[day];
	}
	
}
//