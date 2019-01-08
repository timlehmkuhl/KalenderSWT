package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Termin;
import model.User;

public class TerminErstellen extends JFrame {

	public static void terminErstellen() {
		terminerstellen = new TerminErstellen();
		terminerstellen.setBackground(Color.WHITE);
	}
	static JFrame terminerstellen;
	JLabel ueberschrift;

	JLabel name;
	JTextField namefeld;

	JLabel datum;
	JComboBox datumfeld;
	JComboBox monatfeld;
	JComboBox jahrfeld;

	Color color = Color.RED;

	JLabel uhrzeit;

	JLabel uhrzeitvon;
	JComboBox stdbeginnfeld;
	JComboBox minbeginnfeld;

	JLabel uhrzeitbis;
	JComboBox stdendefeld;
	JComboBox minendefeld;

	JLabel ort;
	JTextField ortfeld;

	JLabel icon;
	JLabel iconbild;
	JButton iconwaehlen;

	JLabel farbe;
	JLabel farbwahl;
	JButton farbewaehlen;

	JLabel einladen;
	JList einladungsliste;
	JButton einladungbearbeiten;

	JLabel notiz;
	JTextArea notizfeld;

	JButton speichern;
	JButton abbrechen;

	public TerminErstellen() {
		
		
		setLayout(null);
		setVisible(true);
		setSize(800, 650);
		setTitle("Termin erstellen");

		// setAlwaysOnTop(true); // Fenster bleibt immer an erster Stelle.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * Überschrift
		 */

		ueberschrift = new JLabel("Termin erstellen");
		ueberschrift.setFont(new Font("Arial", Font.BOLD, 32));
		ueberschrift.setBounds(172, 0, 252, 34);
		add(ueberschrift);

		/*
		 * Name
		 */

		name = new JLabel("Name:");
		name.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		name.setBounds(46, 53, 41, 20);
		add(name);

		namefeld = new JTextField(12);
		namefeld.setBounds(148, 53, 556, 20);
		add(namefeld);

		/*
		 * Datum
		 */

		datum = new JLabel("Datum:");
		datum.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		datum.setBounds(43, 91, 47, 20);
		add(datum);

		// String[] tagString = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
		// "11", "12", "13", "14", "15", "16",
		// "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
		// "30", "31" };

		ArrayList<String> tagString = new ArrayList<String>();
		for (int tagesbegin = 1; tagesbegin <= 31; tagesbegin++) {
			tagString.add(tagesbegin + "");
		}

		datumfeld = new JComboBox(tagString.toArray());
		datumfeld.setSelectedItem(1);
		datumfeld.setBounds(148, 91, 89, 20);
		datumfeld.setBackground(Color.WHITE);
		add(datumfeld);

		String[] monatString = { "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September",
				"Oktober", "November", "Dezember" };
		monatfeld = new JComboBox(monatString);
		monatfeld.setSelectedItem(1);
		monatfeld.setBounds(256, 91, 89, 20);
		monatfeld.setBackground(Color.WHITE);
		add(monatfeld);

		ArrayList<String> jahre = new ArrayList<String>();
		for (int jahresbegin = 2010; jahresbegin <= Calendar.getInstance().get(Calendar.YEAR) + 5; jahresbegin++) {
			jahre.add(jahresbegin + "");
		}
		jahrfeld = new JComboBox(jahre.toArray());
		jahrfeld.setSelectedItem(1);
		jahrfeld.setBounds(359, 91, 130, 20);
		jahrfeld.setBackground(Color.WHITE);
		add(jahrfeld);
		/*
		 * Uhrzeit
		 */

		uhrzeit = new JLabel("Uhrzeit:");
		uhrzeit.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		uhrzeit.setBounds(41, 129, 48, 15);
		add(uhrzeit);

		uhrzeitvon = new JLabel("Von");
		uhrzeitvon.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		uhrzeitvon.setBounds(189, 130, 25, 16);
		add(uhrzeitvon);

		ArrayList<String> stundebeginnString = new ArrayList<String>();
		for (int stdbegin = 0; stdbegin <= 23; stdbegin++) {
			stundebeginnString.add(String.format("%02d", stdbegin) + "");
		}

		stdbeginnfeld = new JComboBox(stundebeginnString.toArray());
		stdbeginnfeld.setSelectedItem(1);
		stdbeginnfeld.setBounds(252, 129, 89, 20);
		stdbeginnfeld.setBackground(Color.WHITE);
		add(stdbeginnfeld);

		ArrayList<String> minutenbeginnString = new ArrayList<String>();
		for (int minbegin = 00; minbegin <= 59; minbegin++) {
			minutenbeginnString.add(String.format("%02d", minbegin) + "");
		}

		minbeginnfeld = new JComboBox(minutenbeginnString.toArray());
		minbeginnfeld.setSelectedItem(1);
		minbeginnfeld.setBounds(359, 129, 76, 20);
		minbeginnfeld.setBackground(Color.WHITE);
		add(minbeginnfeld);

		uhrzeitbis = new JLabel("Bis");
		uhrzeitbis.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		uhrzeitbis.setBounds(454, 132, 19, 15);
		add(uhrzeitbis);

		stdendefeld = new JComboBox(stundebeginnString.toArray());
		stdendefeld.setSelectedItem(1);
		stdendefeld.setBounds(507, 129, 89, 20);
		stdendefeld.setBackground(Color.WHITE);
		add(stdendefeld);

		minendefeld = new JComboBox(minutenbeginnString.toArray());
		minendefeld.setSelectedItem(1);
		minendefeld.setBounds(615, 129, 76, 20);
		minendefeld.setBackground(Color.WHITE);
		add(minendefeld);
		/*
		 * Ort
		 */
		ort = new JLabel("Ort:");
		ort.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		ort.setBounds(63, 163, 25, 16);
		add(ort);

		ortfeld = new JTextField(12);
		ortfeld.setBounds(148, 163, 556, 20);
		add(ortfeld);

		/*
		 * Icon
		 */
		icon = new JLabel("Icon:");
		icon.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		icon.setBounds(54, 215, 31, 16);
		add(icon);
		
		iconbild = new JLabel();
		iconbild.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		iconbild.setBounds(156, 195, 111, 55);
		add(iconbild);
		

		iconwaehlen = new JButton("Icon Wählen");
		iconwaehlen.setBounds(311, 204, 250, 36);
		iconwaehlen.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 13));
		iconwaehlen.setBackground(Color.WHITE);
		add(iconwaehlen);

		iconwaehlen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*Images", "jpg","gif","png");
				file.addChoosableFileFilter(filter);
				int result = file.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = file.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					iconbild.setIcon(ResizeImage(path));
				}
				else if(result == JFileChooser.CANCEL_OPTION) {
					System.out.println("Kein Bild ausgewählt!");
				}

			}
			public ImageIcon ResizeImage(String ImagePath) {
				ImageIcon myImage = new ImageIcon(ImagePath);
				Image img = myImage.getImage();
				Image newImg = img.getScaledInstance(iconbild.getWidth(), iconbild.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon image = new ImageIcon(newImg);
				return image;
			}
		});
		

		

		/*
		 * Farbe
		 */
		farbe = new JLabel("Farbe:");
		farbe.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		farbe.setBounds(33, 273, 43, 16);
		add(farbe);

		farbwahl = new JLabel();
		farbwahl.setBounds(159, 261, 93, 48);
		farbwahl.setBackground(color);
		farbwahl.setOpaque(true);
		add(farbwahl);

		farbewaehlen = new JButton("Farbe Wählen");
		farbewaehlen.setBounds(311, 265, 250, 36);
		farbewaehlen.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 13));
		farbewaehlen.setBackground(Color.WHITE);

		farbewaehlen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				color = JColorChooser.showDialog(null, "Wähle eine Farbe für deinen Termin", color);
				if (color == null)
					color = (Color.RED);

				farbwahl.setBackground(color);
			}
		});

		add(farbewaehlen);

		/*
		 * Einladen
		 */

		einladen = new JLabel("Einladen:");
		einladen.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		einladen.setBounds(26, 330, 59, 16);
		add(einladen);

		String[] freunde = { "Diese Funktion ist noch in Planung :-)" };

		einladungsliste = new JList(freunde);
		einladungsliste.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		einladungsliste.setBounds(159, 319, 337, 73);
		add(einladungsliste);

		einladungbearbeiten = new JButton("Einladungen bearbeiten");
		einladungbearbeiten.setBounds(515, 337, 250, 36);
		einladungbearbeiten.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 13));
		einladungbearbeiten.setBackground(Color.WHITE);
		add(einladungbearbeiten);

		/*
		 * Notizen
		 */

		notiz = new JLabel("Notiz:");
		notiz.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		notiz.setBounds(48, 425, 35, 16);
		add(notiz);

		notizfeld = new JTextArea();
		notizfeld.setBounds(159, 425, 556, 100);
		add(notizfeld);

		/*
		 * Speichern, Abbrechen
		 */

		speichern = new JButton("Speichern");
		speichern.setBounds(50, 555, 222, 35);
		speichern.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 13));
		speichern.setBackground(Color.WHITE);
		add(speichern);
		speichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!namefeld.getText().isEmpty()) {
					String jahr = "2019";
					String monat = "" + (monatfeld.getSelectedIndex() + 1);
					String tag = "" + (datumfeld.getSelectedIndex() + 1);
					String stunde = "" + stdbeginnfeld.getSelectedIndex();
					String minute = "" + minbeginnfeld.getSelectedIndex();
					Timestamp begin = Timestamp.valueOf(jahr + "-" + monat + "-" + tag + " " + stunde + ":" + minute + ":" + "10.0");
					
					stunde = "" + stdendefeld.getSelectedIndex();
					minute = "" + minendefeld.getSelectedIndex();
					Timestamp end = Timestamp.valueOf(jahr + "-" + monat + "-" + tag + " " + stunde + ":" + minute + ":" + "10.0");
					
					User.getInstanz().addTermin(new Termin(namefeld.getText(), begin, end, null, null, null, null));
					terminerstellen.dispose();
				}
			}
		});

		abbrechen = new JButton("Abbrechen");
		abbrechen.setBounds(533, 555, 222, 35);
		abbrechen.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 13));
		abbrechen.setBackground(Color.WHITE);
		add(abbrechen);

		abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				terminerstellen.dispose();
			}
		});

	} //,mv,xcmvn

}
