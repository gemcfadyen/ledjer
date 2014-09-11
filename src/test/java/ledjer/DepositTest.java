package ledjer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;


public class DepositTest
{

  @Before
  public void setUp()
  {
    Transaction.resetSequenceNumber();
  }

  @Test
  public void getAmount() throws Exception
  {
    Deposit deposit = new Deposit(42);
    assertEquals(42, deposit.getAmount());
  }

  @Test
  public void asStatement() throws Exception
  {
    assertEquals("1. Deposit: $1.23\n", new Deposit(123).asStatement());
  }

  @Test
  public void equality() throws Exception
  {
    final Deposit deposit = new Deposit(123);
    assertNotEquals(deposit, null);
    assertEquals(deposit, new Deposit(123));
    assertNotEquals(deposit, new Deposit(321));
  }

  @Test
  public void cloning() throws Exception
  {
    Deposit deposit = new Deposit(123);
    Deposit clone = deposit.clone();
    assertEquals(deposit, clone);
    assertNotSame(deposit, clone);
  }
}