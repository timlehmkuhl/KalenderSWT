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

import model.Termin;
import model.User;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

public class Tagesansicht {
	
	public List<JLabel> buttonListZeit = new LinkedList<>();
	public List<JLabel> buttonList = new LinkedList<>();
	public List<JButton> buttonTermin = new LinkedList<>();
	private static final Font FONT = new Font("Sans Serif", Font.BOLD, 18);
	private static final Font ueberschrift = new Font("Sans Serif", Font.BOLD, 72);
	private JFrame frame;
	private static List<Termin> termine = new LinkedList<>();;

	/**
	 * Launch the application.. BY ECLIPSE 
	 */
//	public static void main(String[] args) {
	public static void startTagesansicht(List<Termin> tagesTermine) {
		
		termine = tagesTermine;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tagesansicht window = new Tagesansicht();
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
	public Tagesansicht() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame. SELBER ANGEPASST
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1333, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JLabel Tag;
		JPanel linksPanel = new JPanel();
		JPanel untenPanel = new JPanel();
		JPanel obenPanel = new JPanel();
		JPanel rechtsPanel = new JPanel();
		
		GridLayout links = new GridLayout(5, 1, 0, 0);
		GridLayout oben = new GridLayout(1, 1, 0, 0);
		GridLayout unten = new GridLayout(1, 2, 60, 10);
		GridLayout rechts = new GridLayout(24, 1, 0, 0);
		
		
		
		linksPanel.setLayout(links);
		obenPanel.setLayout(oben);
		untenPanel.setLayout(unten);
		rechtsPanel.setLayout(rechts);
		
		
				
		JButton terminHinzufuegen = new JButton("Termin Hinzufuegen");
		untenPanel.add(terminHinzufuegen);
		terminHinzufuegen.setFont(FONT);
		
		JButton zurueck = new JButton("Zurueck");
		untenPanel.add(zurueck);
		zurueck.setFont(FONT);
		
		untenPanel.setPreferredSize(new Dimension(200, 80));
		frame.getContentPane().add(untenPanel, BorderLayout.SOUTH);
		
		
		Tag=new JLabel((Integer.toString(User.getInstanz().getDayViewed())).concat("/").concat(Integer.toString(User.getInstanz().getMonthViewed()+1)));
		obenPanel.add(Tag);
		Tag.setFont(ueberschrift);
		
		JButton einstellungen = new JButton("Einstellungen");
		obenPanel.add(einstellungen);
		einstellungen.setFont(FONT);
		frame.getContentPane().add(obenPanel, BorderLayout.NORTH);
		
		
		/*for (int i = 1; i <= 6; i++) {
			buttonList.add(new JButton(String.valueOf(i)));
		}
		buttonList.stream().forEach(x -> x.setBackground(Color.WHITE));
		buttonList.stream().forEach(x -> x.setFont(FONT));
		
		frame.getContentPane().add(untenPanel, BorderLayout.EAST);
		*/
		
		for (int i = 0; i < termine.size(); i++) {
			Calendar c = Calendar.getInstance();
			c.setTime(termine.get(i).getStartZeit());
			String stunde =	Integer.toString(c.get(Calendar.HOUR_OF_DAY));
			
			buttonTermin.add(new JButton(stunde + termine.get(i).getStartZeit().toString()));
		}
		
		
		
String Zeit[] = {"0:00", "6:00", "12:00", "18:00", "24:00"};
		
		for (String s: Zeit) {
			buttonListZeit.add(new JLabel(s, SwingConstants.CENTER));
		}
		buttonListZeit.stream().forEach(x -> x.setFont(FONT));
		
		buttonListZeit.stream().forEach(x -> linksPanel.add(x));
		
		frame.getContentPane().add(linksPanel, BorderLayout.WEST);
		frame.getContentPane().add(rechtsPanel, BorderLayout.EAST);
	
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < termine.size(); j++) {
				if (buttonTermin.get(j).getText().substring(0, 1).equals((i + ""))) {
					rechtsPanel.add(buttonTermin.get(j));

				} else {
					JButton platzhalter = new JButton("platz");
					rechtsPanel.add(platzhalter);
					platzhalter.setVisible(false);
					//hallo
				}
			}
		}
	}
}
