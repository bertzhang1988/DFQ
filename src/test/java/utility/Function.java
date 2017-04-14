package utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Function {
	public static String GetTimeValue(String timezone) {
		SimpleDateFormat f = new SimpleDateFormat("YYYY-MM-dd--HH-mm-ss");
		Calendar c = Calendar.getInstance();
		f.setTimeZone(TimeZone.getTimeZone(timezone));
		return f.format(c.getTime());

	}
}
