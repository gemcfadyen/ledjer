package ledjer;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

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
    Deposit deposit = new Deposit(42, new Date());
    assertEquals(42, deposit.getAmount());
  }

  @Test
  public void hasDate() throws Exception
  {
    Date now = new Date();
    Deposit deposit = new Deposit(42, new Date());

    assertEquals(now, deposit.getDate());
  }

  @Test
  public void asStatement() throws Exception
  {
    final Deposit deposit = new Deposit(123, Transaction.dateFormat.parse("Jan 1, 1997"));
    assertEquals("Jan 1, 1997 1. Deposit: $1.23\n", deposit.asStatement());
  }

  @Test
  public void equality() throws Exception
  {
    final Deposit deposit = new Deposit(123, new Date());
    assertNotEquals(deposit, null);
    assertEquals(deposit, new Deposit(123, new Date()));
    assertNotEquals(deposit, new Deposit(321, new Date()));
  }

  @Test
  public void cloning() throws Exception
  {
    Deposit deposit = new Deposit(123, new Date());
    Deposit clone = deposit.clone();
    assertEquals(deposit, clone);
    assertNotSame(deposit, clone);
  }
}