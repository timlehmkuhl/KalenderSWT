package database;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelp {
	public static void getMills() throws ParseException {
		
		Timestamp timestamp = Timestamp.valueOf("2007-09-23 10:10:10.0");
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("23/09/2007");
		long time = date.getTime();
		new Timestamp(time);
	}
}
