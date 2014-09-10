package ledjer;

public class Payment extends Transaction
{
  private String payee;

  public Payment(int amount, String payee)
  {
    super(amount);
    this.payee = payee;
  }

  public String getPayee()
  {
    return payee;
  }

  @Override
  public String asStatement()
  {
    return String.format("Payment to %s: ($%.2f)\n", payee, getAmount() / 100.0);
  }
}
