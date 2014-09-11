package ledjer;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

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
    ledger.deposit(new Deposit(123, new Date()));

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
    final Deposit deposit1 = new Deposit(123, new Date());
    final Deposit deposit2 = new Deposit(456, new Date());
    ledger.deposit(deposit1);
    ledger.deposit(deposit2);

    assertEquals(deposit1.asStatement() +
      deposit2.asStatement() +
      "Total: $5.79", ledger.statement());
  }

  @Test
  public void paymentSubtractsFromBalance() throws Exception
  {
    ledger.deposit(new Deposit(1234, new Date()));
    ledger.pay(new Payment(234, "Paul", new Date()));

    assertEquals(1000, ledger.getBalance());
  }

  @Test
  public void paymentsAppearInStatement() throws Exception
  {
    final Deposit deposit = new Deposit(1234, new Date());
    final Payment payment = new Payment(234, "Paul", new Date());
    ledger.deposit(deposit);
    ledger.pay(payment);

    assertEquals(deposit.asStatement() +
      payment.asStatement() +
      "Total: $10.00", ledger.statement());
  }

  @Test
  public void statementWithMixedDepositAndPayment() throws Exception
  {
    final Deposit deposit1 = new Deposit(1234, new Date());
    final Payment payment = new Payment(234, "Paul", new Date());
    final Deposit deposit2 = new Deposit(2222, new Date());
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
      ledger.pay(new Payment(123, "Sam", new Date()));
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

    ledger.deposit(new Deposit(123, new Date()));
    assertNotEquals(ledger, other);

    other.deposit(new Deposit(123, new Date()));
    assertEquals(ledger, other);
  }

  @Test
  public void cloning() throws Exception
  {
    final Deposit deposit = new Deposit(1234, new Date());
    final Payment payment = new Payment(321, "Sam", new Date());
    ledger.deposit(deposit);
    ledger.pay(payment);

    Ledger clone = ledger.clone();

    assertEquals(ledger, clone);
    assertNotSame(ledger, clone);
  }

  @Test
  public void addManyTransactions() throws Exception
  {
    for(int i = 0; i < 20; i++)
      ledger.deposit(new Deposit(1, new Date()));

    assertEquals(20, ledger.getBalance());
  }

  @Test
  public void statementOrderedByDate() throws Exception
  {
    final Deposit deposit1 = new Deposit(1234, Transaction.dateFormat.parse("June 3, 2010"));
    final Payment payment = new Payment(234, "Paul", Transaction.dateFormat.parse("June 2, 2009"));
    final Deposit deposit2 = new Deposit(2222, Transaction.dateFormat.parse("June 1, 2008"));
    ledger.deposit(deposit1);
    ledger.pay(payment);
    ledger.deposit(deposit2);

    assertEquals(deposit2.asStatement() +
      payment.asStatement() +
      deposit1.asStatement() +
      "Total: $32.22", ledger.statement());

  }

}