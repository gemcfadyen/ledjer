package ledjer;

import java.text.NumberFormat;

public class PoundConverter {

	public static String convertForDisplay(int amountInPence) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMinimumFractionDigits(2);
		
		return "£" + numberFormat.format(amountInPence/100d);
	}

}
