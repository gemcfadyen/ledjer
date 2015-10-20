package ledjer.features;

import static org.assertj.core.api.Assertions.assertThat;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
		ledger.deposit(new Deposit(balance));
	}

	@When("^a deposit of (\\d+)p is made$")
	public void aDepositIsMade(int amount) {
		ledger.deposit(new Deposit(amount));
	}

	@When("^trying to make a payment of (\\d+)p$")
	public void tryingToMakeAPaymentOf(int amount) {
		try {
			ledger.payment(new Payment(amount, "Test"));
		} catch (NegativeBalanceException e) {
			this.exceptionThrown = true;
		}
	}

	@Then("^the balance is (\\d+)p$")
	public void theBalanceIs(int expectedBalance) {
		assertThat(ledger.getBalance()).isEqualTo(expectedBalance);
	}

	@Then("^a payment of (\\d+)p is made to (\\w+.+)$")
	public void aPaymentIsMade(int amount, String payee) {
		ledger.payment(new Payment(amount, payee));
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
