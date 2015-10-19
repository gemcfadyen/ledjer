package ledjer.features;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ledjer.Deposit;
import ledjer.Ledger;
import ledjer.Payment;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {
  private Ledger ledger;

  @Given("^an empty ledger$")
  public void anEmptyLedger() {
    ledger = new Ledger();
  }

  @When("^a deposit of (\\d+)p is made$")
  public void aDepositIsMade(int amount) {
    ledger.deposit(new Deposit(amount));
  }

  @Then("^the balance is (\\d+)p$")
  public void theBalanceIs(int expectedBalance) {
    assertThat(ledger.getBalance()).isEqualTo(expectedBalance);
  }
  
  @Then("^a payment of (\\d+)p is made to (\\w+)") 
  public void aPaymentIsMade(int amount, String payee) {
	  ledger.payment(new Payment(amount, payee));
  }

  @Then("^the statement contains$")
  public void theStatementContains(String expectedStatement) {
    assertThat(ledger.statement()).isEqualTo(expectedStatement);
  }
}
