package ledjer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class LedgerTest {
	
	@Before
	public void resetTransactionNumber() {
		Transaction.resetNumber();
	}

	@Test
	public void balanceIs0WhenNewLedgerCreated() {
		Ledger ledger = new Ledger();
		assertThat(ledger.getBalance()).isEqualTo(0);
	}
	
	@Test
	public void aNewLedgerStartsCountingTransactionsFrom1() {
		Ledger ledger = new Ledger();
		Deposit deposit = new Deposit(234);
		ledger.deposit(deposit);
		assertThat(ledger.statement()).isEqualTo("1. Deposit: £2.34\nTotal: £2.34");
		
		Ledger anotherLedger = new Ledger();
		anotherLedger.deposit(deposit);
		assertThat(anotherLedger.statement()).isEqualTo("1. Deposit: £2.34\nTotal: £2.34");
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
		Deposit deposit = new Deposit(100);
		ledger.deposit(deposit);

		String expectedStatement = "1. Deposit: £1.00\nTotal: £1.00";

		assertThat(ledger.statement()).isEqualTo(expectedStatement);
	}

	@Test
	public void statementShowsMultipleDepositsMadeWithTotalBalance() {
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(410));
		ledger.deposit(new Deposit(200));

		String expectedStatement = "1. Deposit: £4.10\n2. Deposit: £2.00\nTotal: £6.10";

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
		
		String expectedStatement = "1. Deposit: £5.00\n2. Payment to Amazon: (£1.00)\nTotal: £4.00"; 
		assertThat(ledger.statement()).isEqualTo(expectedStatement);
	}
	
	@Test (expected=NegativeBalanceException.class)
	public void negativeBalancesThrowException() {
		Ledger ledger = new Ledger();
		ledger.payment(new Payment(1000, "Apple"));	
		
	}
	
	@Test
	public void ledgersAtTheSameAddressAreEqual() {
		Ledger ledger = new Ledger();
		Ledger theSameLedger = ledger;
		
		assertThat(ledger).isEqualTo(theSameLedger);
	}
	
	@Test
	public void ledgersWithTheSameTransactionsAreEqual() {
		Transaction.resetNumber();
		Ledger ledger = new Ledger();
		
		Transaction.resetNumber();
		Ledger anotherLedger = new Ledger();
		
		assertThat(ledger).isEqualTo(anotherLedger);
	}
	
	@Test
	public void ledgersWithDifferentTransactionsAreNotEqual() {
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(100));
		
		Ledger anotherLedger = new Ledger();
		anotherLedger.deposit(new Deposit(6000));  
		
		assertThat(ledger).isNotEqualTo(anotherLedger);
	}
	
	@Test
	public void ledgersThatHaveADifferentNumberOfTransactionsAreNotEqual() {
		Transaction.resetNumber();
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(234));
		
		Transaction.resetNumber();
		Ledger anotherLedger = new Ledger();
		anotherLedger.deposit(new Deposit(234));
		anotherLedger.deposit(new Deposit(231));
		
		assertThat(ledger).isNotEqualTo(anotherLedger);
	}
	
	@Test
	public void ledgersThatHaveADifferentNumberOfTransactionsAreNotEqualEvenIfTheirAmountsMatch() {
		Transaction.resetNumber();
		Ledger ledger = new Ledger();
		ledger.deposit(new Deposit(234));
		
		Transaction.resetNumber();
		Ledger anotherLedger = new Ledger();
		anotherLedger.deposit(new Deposit(234));
		anotherLedger.deposit(new Deposit(234));
		
		assertThat(ledger).isNotEqualTo(anotherLedger);
	}
	
	@Test
	public void aLedgerComparedToAnotherObjectIsNotEqual() {
		Ledger ledger = new Ledger();
		Deposit deposit = new Deposit(234);
		
		assertThat(ledger).isNotEqualTo(deposit);
	}
}