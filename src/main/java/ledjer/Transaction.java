package ledjer;

public abstract class Transaction {
	private int amount;
	private int number;
	private static int nextNumber = 1;

	public Transaction(int amount) {
		this.amount = amount;
		number = nextNumber;
		nextNumber++;
	}

	public int getAmount() {
		return amount;
	}

	public abstract String asStatement();

	public int getNumber() {
		return number;
	}

	public static void resetNumber() {
		nextNumber = 1;
	}

	public boolean equals(Object aTransaction) {
		Transaction transaction = (Transaction)aTransaction;
		if ((this.getAmount() == transaction.getAmount()) 
				&& (this.getNumber() == transaction.getNumber())) {
			return true;
		}
		return false;
	}
}
