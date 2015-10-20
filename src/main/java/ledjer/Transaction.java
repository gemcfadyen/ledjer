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
}
