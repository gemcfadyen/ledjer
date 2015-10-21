package ledjer;

import java.util.Comparator;

public class TransactionDateOrdering implements Comparator<Transaction> {

	public int compare(Transaction transactionOne, Transaction transactionTwo) {
		return transactionOne.getDate().compareTo(transactionTwo.getDate());
	}

}
