package ledjer;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

public class PaymentTest
{
  @Before
  public void setUp()
  {
      Transaction.resetSequenceNumber();
  }

  @Test
  public void construction() throws Exception
  {
    Payment payment = new Payment(123, "Uncle Sam", new Date());

    assertEquals(123, payment.getAmount());
    assertEquals("Uncle Sam", payment.getPayee());
  }

  @Test
  public void date() throws Exception
  {
    Date now = new Date();

    Payment payment = new Payment(123, "Sam", now);

    assertEquals(now, payment.getDate());
  }

  @Test
  public void asStatement() throws Exception
  {
    final Payment payment = new Payment(123, "Sam", Transaction.dateFormat.parse("Feb 2, 2003"));
    assertEquals("Feb 2, 2003 1. Payment to Sam: ($1.23)\n", payment.asStatement());
  }

  @Test
  public void equality() throws Exception
  {
    final Payment payment = new Payment(123, "Sam", new Date());
    assertNotEquals(payment, null);
    assertEquals(payment, new Payment(123, "Sam", new Date()));
    assertNotEquals(payment, new Payment(321, "Sam", new Date()));
    assertNotEquals(payment, new Payment(123, "Matt", new Date()));
  }

  @Test
  public void cloning() throws Exception
  {
    Payment payment = new Payment(123, "Sam", new Date());
    Payment clone = payment.clone();
    assertEquals(payment, clone);
    assertNotSame(payment, clone);
  }
}
