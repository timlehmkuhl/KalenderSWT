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
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

public class Monatsansicht {
	public List<JButton> buttonList = new LinkedList<>();
	public List<JLabel> buttonListWochen = new LinkedList<>();
	public List<JLabel> buttonListWochentage = new LinkedList<>();
	private static final Font FONT = new Font("Sans Serif", Font.BOLD, 18);
	private static final Font FONTMONAT = new Font("Sans Serif", Font.BOLD, 22);
	private JFrame frame;

	/**
	 * Launch the application.. BY ECLIPSE 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Monatsansicht window = new Monatsansicht();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application. BY ECLIPSE
	 */
	public Monatsansicht() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame. SELBER ANGEPASST
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1333, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel tagePanel = new JPanel();
		JPanel menuPanel = new JPanel();
		JPanel wochenPanel = new JPanel();
		
		GridLayout grid = new GridLayout(6, 7, 0, 0);
		GridLayout gridWochen = new GridLayout(6, 1, 0, 0);
		GridLayout gridMenu = new GridLayout(4, 2, 600, 10);

		

		tagePanel.setPreferredSize(new Dimension(1200, 800));
		
		tagePanel.setLayout(grid);
		wochenPanel.setLayout(gridWochen);
		menuPanel.setLayout(gridMenu);
		
		frame.getContentPane().add(tagePanel, BorderLayout.EAST);
		
		

		
		JButton terminHinzufuegen = new JButton("Termin hinzuf�gen");
		menuPanel.add(terminHinzufuegen);
		terminHinzufuegen.setFont(FONT);
		
		JButton suche = new JButton("Suche");
		menuPanel.add(suche);
		suche.setFont(FONT);
		
		JButton einstellungen = new JButton("Einstellungen");
		menuPanel.add(einstellungen);
		einstellungen.setFont(FONT);
		
		JButton einladungen = new JButton("Einladungen");
		menuPanel.add(einladungen);
		einladungen.setFont(FONT);
		
		JButton naechsterMonat = new JButton("Naechster Monat");
		menuPanel.add(naechsterMonat);
		naechsterMonat.setFont(FONT);
		
		JButton vorherigerMonat = new JButton("Vorheriger Monat");
		menuPanel.add(vorherigerMonat);
		vorherigerMonat.setFont(FONT);
		
		JLabel monat = new JLabel("Monat", SwingConstants.CENTER);
		menuPanel.add(monat);
		monat.setFont(FONTMONAT);
		
		
		
		
		
	
		
		
		frame.getContentPane().add(menuPanel, BorderLayout.NORTH);
		
		frame.getContentPane().add(wochenPanel, BorderLayout.WEST);
		
		
		
		//WOCHENTAGE
		String wochentage[] = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
		
		for (String s: wochentage) {
			buttonListWochentage.add(new JLabel(s, SwingConstants.CENTER));
		}
		buttonListWochentage.stream().forEach(x -> x.setFont(FONT));
		buttonListWochentage.stream().forEach(x -> tagePanel.add(x));
		
		
		//ALLE TAGE ALS BUTTONS
		for (int i = 1; i <= 35; i++) {
			buttonList.add(new JButton(String.valueOf(i)));
			//buttonList.get(i).setBackground(Color.WHITE);
			//buttonList.get(i).setFont(FONT);
			//tagePanel.add(buttonList.get(i));
			int tag = i-1;
			buttonList.get(tag).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					Tagesansicht.startTagesansicht(User.getInstanz().getKalender().termineDesTages(tag));
				}
			});
		}
		
		//Tage mit Termin bekommen eine Rote Zahl
		for(int i : User.getInstanz().getKalender().DaysNotFree()) {
			buttonList.get(i-1).setForeground(Color.RED);
		}
		
		
		buttonList.stream().forEach(x -> x.setBackground(Color.WHITE));
		buttonList.stream().forEach(x -> x.setFont(FONT));
		buttonList.stream().forEach(x -> tagePanel.add(x));
		
		
		//WOCHENANZEIGE
		for (int j = 0; j <= 5; j++) {
			buttonListWochen.add(new JLabel(String.valueOf("Woche " + j)));
			if (j == 0) buttonListWochen.get(j).setVisible(false);
			buttonListWochen.get(j).setFont(FONT);
			wochenPanel.add(buttonListWochen.get(j));
			
		}
		
		
	}

}
