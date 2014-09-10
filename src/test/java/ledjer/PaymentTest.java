package ledjer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}
