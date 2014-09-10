package ledjer;

public class NegativeBalanceException extends Exception
{
  private Payment payment;

  public NegativeBalanceException(Payment payment)
  {
    super(generateMessage(payment));
    this.payment = payment;
  }

  private static String generateMessage(Payment payment)
  {
    return String.format("Payment to %s of $%.2f, would make you go bankrupt!",
      payment.getPayee(),
      payment.getAmount() / 100.0);
  }

  public Payment getPayment()
  {
    return payment;
  }
}
