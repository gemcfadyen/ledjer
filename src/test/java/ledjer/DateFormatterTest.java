package ledjer;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DateFormatterTest {

	@Test
	public void formatsDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2001, 8, 9);

		assertThat(DateFormatter.formatForStatement(calendar.getTime())).isEqualTo("Sep 9, 2001");
	}

	@Test
	public void parsesToDate() {
		String dateToParse = "May 7, 2010";
	
		Date parsedDate = DateFormatter.formatToDate("May", 7, 2010);
		assertThat(DateFormatter.formatForStatement(parsedDate)).isEqualTo(dateToParse);
	}
}
