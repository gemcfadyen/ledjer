package ledjer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NegativeBalanceExceptionTest
{
  @Test
  public void message() throws Exception
  {
    Payment payment = new Payment(123, "Sam");
    NegativeBalanceException exception = new NegativeBalanceException(payment);

    assertEquals(payment, exception.getPayment());
    assertEquals("Payment to Sam of $1.23, would make you go bankrupt!",
      exception.getMessage());
  }
}
