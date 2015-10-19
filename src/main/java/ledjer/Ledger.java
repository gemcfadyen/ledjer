package ledjer;

public class Ledger {
	private int balanceInPence;
	private int transactionIndex = 0;
	private Transaction[] transactions = new Transaction[20];

	public Ledger() {
		balanceInPence = 0;
	}

	public int getBalance() {
		return balanceInPence;
	}

	public void deposit(Deposit deposit) {
		balanceInPence += deposit.getAmount();
		transactions[transactionIndex++] = deposit;
	}
	
	public void payment(Payment payment) {
		balanceInPence -= payment.getAmount();
		transactions[transactionIndex++] = payment;
	}

	public String statement() {
		return getTransactionDetailsForStatement() + getTotalForStatement();
	}
	
	private String getTransactionDetailsForStatement() {
		StringBuffer statement = new StringBuffer();
		for (int transaction = 0; transaction < transactionIndex; transaction++) {
			statement.append(transactions[transaction].asStatement());
		}
		return statement.toString();
	}	
	
	private String getTotalForStatement() {
		return "Total: " + PoundConverter.convertForDisplay(balanceInPence);
	}
}