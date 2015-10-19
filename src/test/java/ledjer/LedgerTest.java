package ledjer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class LedgerTest {

	@Test
	public void balanceIs0WhenNewLedgerCreated() {
		Ledger ledger = new Ledger();
		assertThat(ledger.getBalance()).isEqualTo(0);
	}

	@Test
	public void statementShowsTotalOfZeroWhenNewLedgerCreated() {
		Ledger ledger = new Ledger();

		String expectedStatement = "Total: £0.00";
		assertThat(ledger.statement()).isEqualTo(expectedStatement);
	}

	@Test
	public void depositing100pUpdatesBalanceTo100p() {
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(100));

		assertThat(ledger.getBalance()).isEqualTo(100);
	}

	@Test
	public void statementShowsDepositOf£1AndBalanceOf£1() {
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(100));

		String expectedStatement = "Deposit: £1.00\nTotal: £1.00";

		assertThat(ledger.statement()).isEqualTo(expectedStatement);
	}

	@Test
	public void statementShowsMultipleDepositsMadeWithTotalBalance() {
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(400));
		ledger.deposit(new Deposit(200));

		String expectedStatement = "Deposit: £4.00\nDeposit: £2.00\nTotal: £6.00";

		assertThat(ledger.statement()).isEqualTo(expectedStatement);
	}

	@Test
	public void deductsPaymentOf10pFromBalance() {
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(100));
		ledger.payment(new Payment(10, "Ikea"));

		assertThat(ledger.getBalance()).isEqualTo(90);
	}
	
	@Test
	public void printsPaymentAndPayeeOnStatement() {
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(500));
		ledger.payment(new Payment(100, "Amazon"));
		
		String expectedStatement = "Deposit: £5.00\nPayment to Amazon: (£1.00)\nTotal: £4.00"; 
		assertThat(ledger.statement()).isEqualTo(expectedStatement);
	}

}