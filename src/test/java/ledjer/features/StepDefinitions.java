package ledjer.features;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ledjer.DateFormatter;
import ledjer.Deposit;
import ledjer.Ledger;
import ledjer.NegativeBalanceException;
import ledjer.Payment;
import ledjer.Transaction;

public class StepDefinitions {
	private Ledger ledger;
	private boolean exceptionThrown = false;

	@Before
	public void resetTransactionNumber() {
		Transaction.resetNumber();
	}

	@Given("^an empty ledger$")
	public void anEmptyLedger() {
		ledger = new Ledger();
	}

	@Given("^a ledger with a balance of (\\d+)p$")
	public void aLedgerWithABalanceOf(int balance) {
		ledger = new Ledger();
		ledger.deposit(new Deposit(balance, new Date()));
	}

	@When("^a deposit of (\\d+)p is made on (\\w+) (\\d+), (\\d+)$")
	public void aDepositIsMade(int amount, String month, int day, int year) {
		ledger.deposit(new Deposit(amount, DateFormatter.formatToDate(month, day, year))) ;
	}

	@When("^trying to make a payment of (\\d+)p on (\\w+) (\\d+), (\\d+)$")
	public void tryingToMakeAPaymentOf(int amount, String month, int day, int year) {
		try {
			ledger.payment(new Payment(amount, "Test", DateFormatter.formatToDate(month, day, year)));
		} catch (NegativeBalanceException e) {
			this.exceptionThrown = true;
		}
	}

	@When("^(\\d+) deposits of (\\d+)p are made on (\\w+) (\\d+), (\\d+)$")
	public void multipleDepositsAreMade(int numberOfDeposits, int amount, String month, int day, int year) {
		for (int i = 0; i < numberOfDeposits; i++) {
			aDepositIsMade(amount, month, day, year);
		}
	}
	
	@When("^saving the ledger$")
	public void savingTheLedger() {
		ledger.save();
	}
	
	@When("^loading the saved ledger$") 
	public void loadingTheLedger() {
		ledger = ledger.load();
	}

	@Then("^the balance is (\\d+)p$")
	public void theBalanceIs(int expectedBalance) {
		assertThat(ledger.getBalance()).isEqualTo(expectedBalance);
	}

	@Then("^a payment of (\\d+)p is made on (\\w+) (\\d+), (\\d+) to (\\w+.+)$")
	public void aPaymentIsMade(int amount, String month, int day, int year, String payee) {
		ledger.payment(new Payment(amount, payee, DateFormatter.formatToDate(month, day, year)));
	}

	@Then("^the statement contains$")
	public void theStatementContains(String expectedStatement) {
		assertThat(ledger.statement()).isEqualTo(expectedStatement);
	}

	@Then("^the payment will be rejected$")
	public void thePaymentWillBeRejected() {
		assertThat(exceptionThrown).isEqualTo(true);
	}
}
