package ledjer;

import java.text.NumberFormat;

public class Ledger {
	private int balanceInPence;
	private Deposit[] deposits = new Deposit[10];
	private Payment[] payments = new Payment[10];
	private int depositsIndex = 0;
	private int paymentIndex = 0;

	public Ledger() {
		balanceInPence = 0;
	}

	public int getBalance() {
		return balanceInPence;
	}

	public void deposit(Deposit deposit) {
		deposits[depositsIndex++] = deposit;
		balanceInPence += deposit.getAmount();
	}
	
	public void payment(Payment payment) {
		balanceInPence -= payment.getAmount();
		payments[paymentIndex++] = payment;
	}

	public String statement() {
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMinimumFractionDigits(2);

		String statement = "";
		statement += getDepositDetailsForStatement(numberFormat);
		statement += getPaymentDetailsForStatement(numberFormat);
		statement += getTotalForStatement(numberFormat);
		
		return statement;
	}
	
	private String getDepositDetailsForStatement(NumberFormat numberFormat) {
		StringBuffer statement = new StringBuffer();
		for (int deposit = 0; deposit < depositsIndex; deposit++) {
			statement.append("Deposit: £" + numberFormat.format(deposits[deposit].getAmount()/100) + "\n");
		}
		return statement.toString();
	}	
	
	private String getPaymentDetailsForStatement(NumberFormat numberFormat) {
		StringBuffer statement = new StringBuffer();
		
		for(int payment = 0; payment < paymentIndex; payment++) {
			statement.append("Payment to " + payments[payment].getPayee() 
					+ ": (£" 
					+ numberFormat.format(payments[payment].getAmount()/100) 
					+ ")\n");
		}
		return statement.toString();
	}
	
	private String getTotalForStatement(NumberFormat numberFormat) {
		return "Total: £" + numberFormat.format(balanceInPence/100);
	}
	

}