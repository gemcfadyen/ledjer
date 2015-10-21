package ledjer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Ledger implements Serializable {
	private static final long serialVersionUID = 1L;
	private int balanceInPence;
	private List<Transaction> transactions = new LinkedList<Transaction>();

	public Ledger() {
		balanceInPence = 0;
	}

	public int getBalance() {
		return balanceInPence;
	}

	public void deposit(Deposit deposit) {
		balanceInPence += deposit.getAmount();
		transactions.add(deposit);
	}

	public void payment(Payment payment) {
		int paymentAmount = payment.getAmount();
		if (paymentAmount <= balanceInPence) {
			balanceInPence -= paymentAmount;
			transactions.add(payment);
		} else {
			throw new NegativeBalanceException();
		}
	}

	public String statement() {
		Collections.sort(transactions, new TransactionDateOrdering());
		return getTransactionDetailsForStatement() + getTotalForStatement();
	}

	private String getTransactionDetailsForStatement() {
		StringBuffer statement = new StringBuffer();
		for (int transaction = 0; transaction < transactions.size(); transaction++) {
			statement.append(transactions.get(transaction).asStatement());
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

		if (!(aLedger instanceof Ledger)) {
			return false;
		}

		Ledger otherLedger = (Ledger) aLedger;
		if ((this.balanceInPence == otherLedger.balanceInPence)
				&& Arrays.equals(this.transactions.toArray(), otherLedger.transactions.toArray())) {
			return true;
		}
		return false;
	}

	public void save() {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("/tmp/ledger"));
			output.writeObject(this);
			output.close();
		} catch (IOException e) {
			throw new SaveLedgerException("Error when saving ledger", e);
		}
	}

	public Ledger load() {
		Ledger loadedLedger = null;
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("/tmp/ledger"));
			loadedLedger = (Ledger) input.readObject();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new LoadLedgerException();
		} catch (ClassNotFoundException e) {
			throw new LoadLedgerException();
		}

		return loadedLedger;
	}
}