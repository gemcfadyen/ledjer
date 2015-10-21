package ledjer;

import java.util.Date;
 
public abstract class Transaction {
	private int amount;
	private Date date;
	private int number;
	private static int nextNumber = 1;

	public Transaction(int amount, Date date) {
		this.amount = amount;
		this.date = date;
		this.number = nextNumber;
		nextNumber++;
	}

	public int getAmount() {
		return amount;
	}

	public abstract String asStatement();

	public Date getDate() {
		return date;
	}

	public int getNumber() {
		return number;
	}

	public static void resetNumber() {
		nextNumber = 1;
	}

	public boolean equals(Object aTransaction) {
		Transaction transaction = (Transaction) aTransaction;
		if ((this.getAmount() == transaction.getAmount()) && (this.getNumber() == transaction.getNumber())
				&& (this.date.equals(transaction.date))) {
			return true;
		}
		return false;
	}

}
