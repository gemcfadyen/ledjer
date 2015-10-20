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
		int paymentAmount = payment.getAmount();
		if (paymentAmount <= balanceInPence) {
			balanceInPence -= paymentAmount;
			transactions[transactionIndex++] = payment;
		} else {
			throw new NegativeBalanceException();
		}
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

	public boolean equals(Object aLedger) {
		if (this == aLedger) {
			return true;
		}
		
		if(!(aLedger instanceof Ledger)) {
			return false;
		}

		Ledger otherLedger = (Ledger) aLedger;
		if ((this.balanceInPence == otherLedger.balanceInPence) 
				&& allTransactionsMatch(otherLedger)) {
			return true;
		}
		return false;
	}

	private boolean allTransactionsMatch(Ledger otherLedger) {
		if (this.transactions.length == otherLedger.transactions.length) {
			for (int i = 0; i < this.transactions.length; i++) {
				if (transactions[i] != null 
						&& otherLedger.transactions[i] != null
						&& !transactions[i].equals(otherLedger.transactions[i])) {
					return false;
				}
			}
		}
		return true;
	}
}