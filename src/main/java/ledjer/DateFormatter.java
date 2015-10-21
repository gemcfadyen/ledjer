package ledjer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.UK);

	public static String formatForStatement(Date date) {
		return dateFormat.format(date);
	}

	private static Date formatToDate(String dateToParse) {
		Date date = new Date();
		try {
			date = dateFormat.parse(dateToParse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date formatToDate(String month, int day, int year) {
		String dateForParsing = String.format("%s %d, %d", month, day, year);
		return formatToDate(dateForParsing);
	}

}
