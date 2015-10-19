package ledjer;

import java.text.NumberFormat;

public class Ledger {
	private int balanceInPence;
	private int[] deposits = new int[10];
	private int currentDeposit = 0;

	public Ledger() {
		balanceInPence = 0;
	}

	public int getBalance() {
		return balanceInPence;
	}

	public void deposit(Deposit deposit) {
		deposits[currentDeposit++] = deposit.getAmount();
		balanceInPence += deposit.getAmount();
	}

	public String statement() {
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMinimumFractionDigits(2);

		StringBuffer statement = writeDepositsToStatement(numberFormat);
		statement.append("Total: £" + numberFormat.format(balanceInPence/100));
		
		return statement.toString();
	}

	private StringBuffer writeDepositsToStatement(NumberFormat numberFormat) {
		StringBuffer statement = new StringBuffer();
		for (int deposit = 0; deposit < currentDeposit; deposit++) {
			statement.append("Deposit: £" + numberFormat.format(deposits[deposit]/100) + "\n");
		}
		return statement;
	}

}