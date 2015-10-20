package ledjer;

public class Payment extends Transaction {
	private String payee;

	public Payment(int amount, String payee) {
		super(amount);
		this.payee = payee;
	}

	public String getPayee() {
		return payee;
	}

	@Override
	public String asStatement() {
		return getNumber() + ". Payment to " + payee + ": (" + PoundConverter.convertForDisplay(getAmount()) + ")\n";
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
		if ((this.payee.equals(anotherPayment.payee) 
				&& this.getAmount() == anotherPayment.getAmount())
				&& this.getNumber() == anotherPayment.getNumber()) {
			return true;
		}
		return false;
	}
}
