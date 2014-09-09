package ledjer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaymentTest
{
  @Test
  public void construction() throws Exception
  {
    Payment payment = new Payment(123, "Uncle Sam");

    assertEquals(123, payment.getAmount());
    assertEquals("Uncle Sam", payment.getPayee());
  }
}
