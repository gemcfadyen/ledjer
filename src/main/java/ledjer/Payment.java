package ledjer;

import java.util.Date;

public class Payment extends Transaction {
	private String payee;

	public Payment(int amount, String payee, Date date) {
		super(amount, date);
		this.payee = payee;
	}

	public String getPayee() {
		return payee;
	}

	@Override
	public String asStatement() {
		return DateFormatter.formatForStatement(getDate()) 
				+ " " 
				+ getNumber() 
				+ ". Payment to " 
				+ payee 
				+ ": (" 
				+ PoundConverter.convertForDisplay(getAmount()) 
				+ ")\n";
	}

	@Override
	public boolean equals(Object aPayment) {
		if (this == aPayment) {
			return true;
		}
		
		if(!(aPayment instanceof Payment)) {
			return false;
		}

		Payment anotherPayment = (Payment) aPayment;
		
		if (this.payee.equals(anotherPayment.payee)) {
			return true && super.equals(aPayment);
		}
		return false;
	}

}
