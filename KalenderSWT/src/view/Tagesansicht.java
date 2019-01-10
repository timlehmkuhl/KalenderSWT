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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

public class Tagesansicht {
	
	public List<JLabel> buttonListZeit = new LinkedList<>();
	public List<JLabel> buttonList = new LinkedList<>();
	public List<JLabel> buttonTermin = new LinkedList<>();
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
					windowP = window;
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	static Tagesansicht windowP;
	/**
	 * Create the application. BY ECLIPSE
	 */
	public Tagesansicht() {
		initialize();
	}

	
	public void refreshView() {
		
		termine = User.getInstanz().getKalender().termineDesTages(User.getInstanz().getDayViewed());
		windowP.frame.dispose();
		Tagesansicht window = new Tagesansicht();
		windowP = window;
		window.frame.setVisible(true);
		window.frame.setLocationRelativeTo(null);
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
		GridLayout rechts = new GridLayout(4, 4, 0, 0);
		
		
		
		linksPanel.setLayout(links);
		obenPanel.setLayout(oben);
		untenPanel.setLayout(unten);
		rechtsPanel.setLayout(rechts);
		
		
				
		JButton terminHinzufuegen = new JButton("Termin Hinzufuegen");
		untenPanel.add(terminHinzufuegen);
		terminHinzufuegen.setFont(FONT);
		terminHinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				TerminErstellen.terminErstellen(windowP);
			}
		});
		
		JButton zurueck = new JButton("Zurueck");
		untenPanel.add(zurueck);
		zurueck.setFont(FONT);
		zurueck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				windowP.frame.dispose();
				Monatsansicht.main(null);
			}
		});
		
		untenPanel.setPreferredSize(new Dimension(200, 80));
		frame.getContentPane().add(untenPanel, BorderLayout.SOUTH);
		
		
		Tag=new JLabel((Integer.toString(User.getInstanz().getDayViewed())).concat("/").concat(Integer.toString(User.getInstanz().getMonthViewed()+1)));
		obenPanel.add(Tag);
		Tag.setFont(ueberschrift);
		
		JButton einstellungen = new JButton("Einstellungen");
		obenPanel.add(einstellungen);
		einstellungen.setFont(FONT);
		frame.getContentPane().add(obenPanel, BorderLayout.NORTH);
		
		
		
		
		for (int i = 0; i < termine.size(); i++) {
			Calendar c = Calendar.getInstance();
			c.setTime(termine.get(i).getStartZeit());
			String stunde =	Integer.toString(c.get(Calendar.HOUR_OF_DAY));
			String minuten = String.format("%02d", c.get(Calendar.MINUTE));
			
			c.setTime(termine.get(i).getEndZeit());
			String stundeEnd =	Integer.toString(c.get(Calendar.HOUR_OF_DAY));
			String minutenEnd = String.format("%02d", c.get(Calendar.MINUTE));
			String name = termine.get(i).getName().toString();
			
			String ort = "keiner";
			String notiz = "keine";
			if (termine.get(i).getOrt() != null)
				ort = termine.get(i).getOrt().toString();
			if(termine.get(i).getNotiz() != null)
				notiz = termine.get(i).getNotiz().toString();
			
			buttonTermin.add(new JLabel("<html>" + stunde + ":" + minuten + " Uhr bis " + stundeEnd + ":" + minutenEnd + " Uhr<p/>" + name + "<p/>Ort: " + ort + "<p/> Notiz: " + notiz + "</html>", SwingConstants.CENTER));
			buttonTermin.get(i).setFont(new Font("Areal", Font.BOLD, 18));
			if(termine.get(i).getFarbe() != null) {
				System.err.println(termine.get(i).getFarbe().substring(1));
				Color bg = new Color(Integer.parseInt(termine.get(i).getFarbe().substring(1), 16), true);
				buttonTermin.get(i).setForeground(bg);
				
			}
			
		}
		
		
		
/*String Zeit[] = {"0:00", "6:00", "12:00", "18:00", "24:00"};
		
		for (String s: Zeit) {
			buttonListZeit.add(new JLabel(s, SwingConstants.CENTER));
		}
		buttonListZeit.stream().forEach(x -> x.setFont(FONT));
		
		buttonListZeit.stream().forEach(x -> linksPanel.add(x));
		*/
	//	buttonTermin.stream().forEach(x -> x.setSize(width, height););
		
		
		buttonTermin.stream().forEach(x -> rechtsPanel.add(x));
		
		frame.getContentPane().add(linksPanel, BorderLayout.WEST);
		frame.getContentPane().add(rechtsPanel, BorderLayout.CENTER);
	
		
			
		
	}
}
