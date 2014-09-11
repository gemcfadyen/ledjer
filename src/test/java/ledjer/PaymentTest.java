package ledjer;

import org.junit.Before;
import org.junit.Test;

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
    Payment payment = new Payment(123, "Uncle Sam");

    assertEquals(123, payment.getAmount());
    assertEquals("Uncle Sam", payment.getPayee());
  }

  @Test
  public void asStatement() throws Exception
  {
    assertEquals("1. Payment to Sam: ($1.23)\n", new Payment(123, "Sam").asStatement());
  }

  @Test
  public void equality() throws Exception
  {
    final Payment payment = new Payment(123, "Sam");
    assertNotEquals(payment, null);
    assertEquals(payment, new Payment(123, "Sam"));
    assertNotEquals(payment, new Payment(321, "Sam"));
    assertNotEquals(payment, new Payment(123, "Matt"));
  }

  @Test
  public void cloning() throws Exception
  {
    Payment payment = new Payment(123, "Sam");
    Payment clone = payment.clone();
    assertEquals(payment, clone);
    assertNotSame(payment, clone);
  }
}
