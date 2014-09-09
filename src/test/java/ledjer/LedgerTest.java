package ledjer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    ledger.deposit(new Deposit(123));
    ledger.deposit(new Deposit(456));

    assertEquals("Deposit: $1.23\nDeposit: $4.56\nTotal: $5.79", ledger.statement());
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
    ledger.deposit(new Deposit(1234));
    ledger.pay(new Payment(234, "Paul"));

    assertEquals("Deposit: $12.34\n" +
      "Payment to Paul: ($2.34)\n" +
      "Total: $10.00", ledger.statement());
  }
}