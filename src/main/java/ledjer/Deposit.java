package ledjer;

public class Deposit extends Transaction {

	public Deposit(int amount) {
		super(amount);
	}

	@Override
	public String asStatement() {
		return getNumber() + ". Deposit: " + PoundConverter.convertForDisplay(getAmount()) + "\n";
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
		if ((this.getAmount() == anotherDeposit.getAmount()) && (this.getNumber() == anotherDeposit.getNumber())) {
			return true;
		}
		return false;
	}
}