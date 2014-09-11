package ledjer;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LedgerTest
{

  public Ledger ledger;

  @Before
  public void setup()
  {
    ledger = new Ledger();
  }

  @Test
  public void initialBalance() throws Exception
  {
    assertEquals(0, ledger.getBalance());
  }

  @Test
  public void deposit()
  {
    ledger.deposit(new Deposit(123));

    assertEquals(123, ledger.getBalance());
  }

  @Test
  public void emptyStatement() throws Exception
  {
    assertEquals("Total: $0.00", ledger.statement());
  }

  @Test
  public void statementWithDeposits() throws Exception
  {
    final Deposit deposit1 = new Deposit(123);
    final Deposit deposit2 = new Deposit(456);
    ledger.deposit(deposit1);
    ledger.deposit(deposit2);

    assertEquals(deposit1.asStatement() +
      deposit2.asStatement() +
      "Total: $5.79", ledger.statement());
  }

  @Test
  public void paymentSubtractsFromBalance() throws Exception
  {
    ledger.deposit(new Deposit(1234));
    ledger.pay(new Payment(234, "Paul"));

    assertEquals(1000, ledger.getBalance());
  }

  @Test
  public void paymentsAppearInStatement() throws Exception
  {
    final Deposit deposit = new Deposit(1234);
    final Payment payment = new Payment(234, "Paul");
    ledger.deposit(deposit);
    ledger.pay(payment);

    assertEquals(deposit.asStatement() +
      payment.asStatement() +
      "Total: $10.00", ledger.statement());
  }

  @Test
  public void statementWithMixedDepositAndPayment() throws Exception
  {
    final Deposit deposit1 = new Deposit(1234);
    final Payment payment = new Payment(234, "Paul");
    final Deposit deposit2 = new Deposit(2222);
    ledger.deposit(deposit1);
    ledger.pay(payment);
    ledger.deposit(deposit2);

    assertEquals(deposit1.asStatement() +
      payment.asStatement() +
      deposit2.asStatement() +
      "Total: $32.22", ledger.statement());
  }

  @Test
  public void cantGoBankrupt() throws Exception
  {
    try
    {
      ledger.pay(new Payment(123, "Sam"));
      fail("Should not allow bankrupcy!");
    }
    catch(NegativeBalanceException nbe)
    {
      assertEquals(0, ledger.getBalance());
    }
  }

  @Test
  public void equality() throws Exception
  {
    assertNotEquals(ledger, null);

    Ledger other = new Ledger();
    assertEquals(ledger, other);

    ledger.deposit(new Deposit(123));
    assertNotEquals(ledger, other);

    other.deposit(new Deposit(123));
    assertEquals(ledger, other);
  }
}