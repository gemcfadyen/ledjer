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
    return String.format("%d. Payment to %s: ($%.2f)\n", getNumber(), payee, getAmount() / 100.0);
  }

  @Override
  public boolean equals(Object o)
  {
    if(this == o) return true;
    if(!(o instanceof Payment)) return false;

    Payment payment = (Payment) o;

    if(!super.equals(o)) return false;
    if(payee != null ? !payee.equals(payment.payee) : payment.payee != null) return false;

    return true;
  }

  @Override
  public int hashCode()
  {
    return super.hashCode() + (payee != null ? payee.hashCode() : 0);
  }
}
