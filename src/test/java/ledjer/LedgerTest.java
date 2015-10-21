package ledjer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class LedgerTest {
	private Calendar earlierTransactionTime;
	private Calendar transactionTime;


	@Before
	public void resetTransactionNumber() {
		Transaction.resetNumber();
	
		earlierTransactionTime = Calendar.getInstance();
		earlierTransactionTime.set(2000, 2, 3);
		
		transactionTime = Calendar.getInstance();
		transactionTime.set(2001, 8, 5);
	}

	@Test
	public void balanceIs0WhenNewLedgerCreated() {
		Ledger ledger = new Ledger();
		assertThat(ledger.getBalance()).isEqualTo(0);
	}

	@Test
	public void aNewLedgerStartsCountingTransactionsFrom1() {
		Ledger ledger = new Ledger();
		Deposit deposit = new Deposit(234, transactionTime.getTime());
		ledger.deposit(deposit);
		assertThat(ledger.statement()).isEqualTo("Sep 5, 2001 1. Deposit: £2.34\nTotal: £2.34");

		Ledger anotherLedger = new Ledger();
		anotherLedger.deposit(deposit);
		assertThat(anotherLedger.statement()).isEqualTo("Sep 5, 2001 1. Deposit: £2.34\nTotal: £2.34");
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
		ledger.deposit(new Deposit(100, new Date()));

		assertThat(ledger.getBalance()).isEqualTo(100);
	}

	@Test
	public void statementShowsDepositOf£1AndBalanceOf£1() {
		Ledger ledger = new Ledger();
		Deposit deposit = new Deposit(100, transactionTime.getTime());
		ledger.deposit(deposit);

		String expectedStatement = "Sep 5, 2001 1. Deposit: £1.00\nTotal: £1.00";

		assertThat(ledger.statement()).isEqualTo(expectedStatement);
	}

	@Test
	public void statementShowsMultipleDepositsMadeWithTotalBalance() {
		Calendar anotherTransactionTime = Calendar.getInstance();
		anotherTransactionTime.set(2008, 10, 9);

		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(410, transactionTime.getTime()));
		ledger.deposit(new Deposit(200, anotherTransactionTime.getTime()));

		String expectedStatement = "Sep 5, 2001 1. Deposit: £4.10\nNov 9, 2008 2. Deposit: £2.00\nTotal: £6.10";

		assertThat(ledger.statement()).isEqualTo(expectedStatement);
	}

	@Test
	public void deductsPaymentOf10pFromBalance() {
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(100, new Date()));
		ledger.payment(new Payment(10, "Ikea", new Date()));

		assertThat(ledger.getBalance()).isEqualTo(90);
	}

	@Test
	public void printsPaymentAndPayeeOnStatement() {
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(500, transactionTime.getTime()));
		ledger.payment(new Payment(100, "Amazon", transactionTime.getTime()));

		String expectedStatement = "Sep 5, 2001 1. Deposit: £5.00\nSep 5, 2001 2. Payment to Amazon: (£1.00)\nTotal: £4.00";
		assertThat(ledger.statement()).isEqualTo(expectedStatement);
	}

	@Test(expected = NegativeBalanceException.class)
	public void negativeBalancesThrowException() {
		Ledger ledger = new Ledger();
		ledger.payment(new Payment(1000, "Apple", new Date()));

	}

	@Test
	public void ledgersAtTheSameAddressAreEqual() {
		Ledger ledger = new Ledger();
		Ledger theSameLedger = ledger;

		assertThat(ledger).isEqualTo(theSameLedger);
	}

	@Test
	public void ledgersWithNoTransactionsAreEqual() {
		Transaction.resetNumber();
		Ledger ledger = new Ledger();

		Transaction.resetNumber();
		Ledger anotherLedger = new Ledger();

		assertThat(ledger).isEqualTo(anotherLedger);
	}

	@Test
	public void ledgersWithTheSameTransactionsAreEqual() {
		Deposit deposit = new Deposit(213, new Date());
		Transaction.resetNumber();

		Ledger ledger = new Ledger();
		ledger.deposit(deposit);

		Transaction.resetNumber();
		Ledger anotherLedger = new Ledger();
		anotherLedger.deposit(deposit);

		assertThat(ledger).isEqualTo(anotherLedger);
	}

	@Test
	public void ledgersWithDifferentTransactionsAreNotEqual() {
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(100, new Date()));

		Ledger anotherLedger = new Ledger();
		anotherLedger.deposit(new Deposit(6000, new Date()));

		assertThat(ledger).isNotEqualTo(anotherLedger);
	}

	@Test
	public void ledgersThatHaveADifferentNumberOfTransactionsAreNotEqual() {
		Transaction.resetNumber();
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(234, new Date()));

		Transaction.resetNumber();
		Ledger anotherLedger = new Ledger();
		anotherLedger.deposit(new Deposit(234, new Date()));
		anotherLedger.deposit(new Deposit(231, new Date()));

		assertThat(ledger).isNotEqualTo(anotherLedger);
	}

	@Test
	public void ledgersThatHaveADifferentNumberOfTransactionsAreNotEqualEvenIfTheirAmountsMatch() {
		Transaction.resetNumber();
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(234, new Date()));

		Transaction.resetNumber();
		Ledger anotherLedger = new Ledger();
		anotherLedger.deposit(new Deposit(234, new Date()));
		anotherLedger.deposit(new Deposit(234, new Date()));

		assertThat(ledger).isNotEqualTo(anotherLedger);
	}

	@Test
	public void aLedgerComparedToAnotherObjectIsNotEqual() {
		Ledger ledger = new Ledger();
		Deposit deposit = new Deposit(234, new Date());

		assertThat(ledger).isNotEqualTo(deposit);
	}

	@Test
	public void transactionsInTheLedgerAreOrderedByDate() {
		Deposit earliestDeposit = new Deposit(1000, earlierTransactionTime.getTime());

		Deposit latestDeposit = new Deposit(2000, transactionTime.getTime());

		Ledger ledger = new Ledger();
		ledger.deposit(latestDeposit);
		ledger.deposit(earliestDeposit);

		String expectedStatement = "Mar 3, 2000 1. Deposit: £10.00\nSep 5, 2001 2. Deposit: £20.00\nTotal: £30.00";
		assertThat(ledger.statement()).isEqualTo(expectedStatement);
	}

	@Test
	public void saveAndLoadLedger() {
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(1000, transactionTime.getTime()));
		ledger.payment(new Payment(100, "Amazon", transactionTime.getTime()));

		ledger.save();
		Ledger loadedLedger = ledger.load();

		assertThat(loadedLedger.getBalance()).isEqualTo(ledger.getBalance());
		assertThat(loadedLedger.statement()).isEqualTo(ledger.statement());

	}

}