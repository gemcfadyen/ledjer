package ledjer;

import java.util.Date;

public class Deposit extends Transaction {

	public Deposit(int amount, Date date) {
		super(amount, date);
	}

	@Override
	public String asStatement() {
		return DateFormatter.formatForStatement(getDate()) 
				+ " " 
				+ getNumber() 
				+ ". Deposit: " 
				+ PoundConverter.convertForDisplay(getAmount()) 
				+ "\n";
	}

	@Override
	public boolean equals(Object aDeposit) {
		if (this == aDeposit) {
			return true;
		}

		if (!(aDeposit instanceof Deposit)) {
			return false;
		}

		Deposit anotherDeposit = (Deposit) aDeposit;
		return super.equals(anotherDeposit);
	}
}