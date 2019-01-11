package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.User;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Monatsansicht {
	public List<JButton> buttonList = new LinkedList<>();
	public List<JLabel> buttonListWochen = new LinkedList<>();
	public List<JLabel> buttonListWochentage = new LinkedList<>();
	private static final Font FONT = new Font("Sans Serif", Font.BOLD, 24);
	private static final Font ButtonFont = new Font("Sans Serif", Font.BOLD, 42);
	private static final Font FONTMONAT = new Font("Sans Serif", Font.BOLD, 45);
	private JFrame frame;
	
    

	/**
	 * Monatsansicht starten (Aus MainToTest DB)
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Monatsansicht window = new Monatsansicht();
					windowP = window;
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	static Monatsansicht windowP;
	
	/**
	 * Fenster neu laden, Termine die ganz neu hinzugefuegt worden sind werden angezeigt
	 */
	public void refreshView() {
		frame.dispose();
		Monatsansicht window = new Monatsansicht();
		JFrame frame = new JFrame();
		window.frame.setVisible(true);
		window.frame.setLocationRelativeTo(null);
		windowP = window;
	}
	/**
	 * Inizialisieren der Ansicht
	 */
	public Monatsansicht() {
		initialize();
	}
	
	/**
	 * Nur zum rotieren von Wochentagen also Arrays mit 7 Eintraegen
	 * @param array
	 * @param times
	 * @return rotiertes Array
	 */
	private String[] rotate(String[] array, int times) {
		String[] returnArray = new String[7];
		int counter = times;
		for(int i = 0; i <= 6; i++) {
			returnArray[i] = array[counter];
			if(counter >= 6) {
				counter = 0;
			} else {
				counter++;
			}
			
		}
		return returnArray;
		
	}
	
	private int getShifted() {
		Calendar c = Calendar.getInstance();
		String m = String.format("%02d", User.getInstanz().getMonthViewed() + 1);
		String values = Integer.toString(User.getInstanz().getYearViewed()) + "-" + m + "-01 11:10:10.0";
		Timestamp firstDay = Timestamp.valueOf(values);
		c.setTime(firstDay);
		
		int shifted;
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			shifted = 0;
		}else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
			shifted = 1;
		}else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
			shifted = 2;
		}else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
			shifted = 3;
		}else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			shifted = 4;
		}else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			shifted = 5;
		}else {
			shifted = 6;
		}
		return shifted;
	}
	
	private int getDaysInMonth() {
		YearMonth yearMonthObject = YearMonth.of(User.getInstanz().getYearViewed(), User.getInstanz().getMonthViewed()+1);
		return yearMonthObject.lengthOfMonth();
	}
	
	private void addHiddenButtons(int anzahl) {
		for (int i = 0; i < anzahl; i++) {
			buttonList.add(new JButton(String.valueOf(0)));
			buttonList.get(i).setVisible(false);
		}
	}
	/**
	 * Frame inhalte laden
	 */
	private void initialize() {
		
		int wochenanzeige;
		int hiddenToAdd;
		
		User.getInstanz().setDayViewed(0);// Monatsansciht, nicht tagesansicht
		frame = new JFrame();
		
		frame.setBounds(100, 100, 1333, 1000);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel tagePanel = new JPanel();
		JPanel menuPanel = new JPanel();
		JPanel wochenPanel = new JPanel();
		
		GridLayout grid;
		GridLayout gridWochen;
		if(getDaysInMonth() + getShifted() > 35 ) {	//Extra reihe benötigt
			grid = new GridLayout(7, 7, 0, 0); //6,7
			gridWochen = new GridLayout(7, 1, 0, 0); //6
			wochenanzeige = 6;
			hiddenToAdd = 36;
		} else {
			grid = new GridLayout(6, 7, 0, 0); //6,7
			gridWochen = new GridLayout(6, 1, 0, 0); //6
			wochenanzeige = 5;
			hiddenToAdd = 31;
		}
		
		GridLayout gridMenu = new GridLayout(3, 3, 30, 10);

		

		tagePanel.setPreferredSize(new Dimension(1200, 800));
		
		tagePanel.setLayout(grid);
		wochenPanel.setLayout(gridWochen);
		menuPanel.setLayout(gridMenu);
		
		frame.getContentPane().add(tagePanel, BorderLayout.EAST);
		
		
		ImageIcon hinzufuegenIcon = new ImageIcon("add.png");		
		JButton terminHinzufuegen = new JButton(hinzufuegenIcon);
		menuPanel.add(terminHinzufuegen);
		terminHinzufuegen.setFont(FONT);
		terminHinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				TerminErstellen.terminErstellen(windowP);
			}
		});
		
		JButton platz1 = new JButton("1");
		menuPanel.add(platz1);
		platz1.setVisible(false);
		
		ImageIcon suchenIcon = new ImageIcon("suchen.png");	
		JButton suche = new JButton(suchenIcon);
		menuPanel.add(suche);
		suche.setFont(FONT);
		
		ImageIcon einstellungenIcon = new ImageIcon("einstellungen.png");	
		JButton einstellungen = new JButton(einstellungenIcon);
		menuPanel.add(einstellungen);
		einstellungen.setFont(FONT);
		
		JLabel monat = new JLabel(User.getMonthName(User.getInstanz().getMonthViewed()).concat(" ").concat(Integer.toString(User.getInstanz().getYearViewed())), SwingConstants.CENTER);
		menuPanel.add(monat);
		monat.setFont(FONTMONAT);
		
		ImageIcon einladenIcon = new ImageIcon("einladen.png");	
		JButton einladungen = new JButton(einladenIcon);
		menuPanel.add(einladungen);
		einladungen.setFont(FONT);
		
		
		ImageIcon linksIcon = new ImageIcon("pfeillinks.png");	
		JButton vorherigerMonat = new JButton(linksIcon);
		menuPanel.add(vorherigerMonat);
		vorherigerMonat.setFont(FONT);
		vorherigerMonat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				User.getInstanz().viewPrevMonth();
				refreshView();
			}
		});
		
		JButton platz2 = new JButton("2");
		menuPanel.add(platz2);
		platz2.setVisible(false);
		
		
		ImageIcon rechtsIcon = new ImageIcon("pfeilrechts.png");	
		JButton naechsterMonat = new JButton(rechtsIcon);
		menuPanel.add(naechsterMonat);
		naechsterMonat.setFont(FONT);
		naechsterMonat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				User.getInstanz().viewNextMonth();
				refreshView();
			}
		});
		
		
		
		

		

		
		
	
		
		
		frame.getContentPane().add(menuPanel, BorderLayout.NORTH);
		
		frame.getContentPane().add(wochenPanel, BorderLayout.WEST);
		
		
		
		//WOCHENTAGE
		
		String wochentage[] = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
		
		addHiddenButtons(getShifted());
		
		for (String s: wochentage) {
			buttonListWochentage.add(new JLabel(s, SwingConstants.CENTER));
		}
		buttonListWochentage.stream().forEach(x -> x.setFont(FONT));
		buttonListWochentage.stream().forEach(x -> tagePanel.add(x));
		
		
		//ALLE TAGE ALS BUTTONS
		
		YearMonth yearMonthObject = YearMonth.of(User.getInstanz().getYearViewed(), User.getInstanz().getMonthViewed()+1);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		
		for (int i = 1; i <= daysInMonth; i++) {
			buttonList.add(new JButton(String.valueOf(i)));
			int tag = i;
			buttonList.get(tag -1 + getShifted()).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					frame.dispose();
					User.getInstanz().setDayViewed(tag);
					Tagesansicht.startTagesansicht(User.getInstanz().getKalender().termineDesTages(tag));
				}
			});
		}
		for (int i = daysInMonth; i + getShifted() <= hiddenToAdd; i++) {
			buttonList.add(new JButton(String.valueOf(0)));
			buttonList.get(i+getShifted()).setVisible(false);
		}
		
		//Tage mit Termin bekommen eine Rote Zahl
		for(int i : User.getInstanz().getKalender().DaysNotFree()) {
			buttonList.get(i-1 + getShifted()).setForeground(Color.RED);
		}
		
		
		buttonList.stream().forEach(x -> x.setBackground(Color.WHITE));
		buttonList.stream().forEach(x -> x.setFont(ButtonFont));
		buttonList.stream().forEach(x -> tagePanel.add(x));
		
		
		//WOCHENANZEIGE
				Calendar c1 = Calendar.getInstance();
				String m1 = String.format("%02d", User.getInstanz().getMonthViewed() + 1);
				String s = Integer.toString(User.getInstanz().getYearViewed()) + "-" + m1 + "-01 11:10:10.0";
				Timestamp firstDay1 = Timestamp.valueOf(s);
				
				c1.setTime(firstDay1);
				int w = c1.get(Calendar.WEEK_OF_YEAR);
				System.err.println(w);
				for (int j = 0; j <= wochenanzeige; j++) {
					buttonListWochen.add(new JLabel(String.valueOf("Woche " + (j + w - 1))));
					if (j == 0) buttonListWochen.get(j).setVisible(false);
					buttonListWochen.get(j).setFont(FONT);
					wochenPanel.add(buttonListWochen.get(j));
					
				}
		
		
	}

}
