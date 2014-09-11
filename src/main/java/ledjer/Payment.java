package ledjer;

import java.util.Date;

public class Payment extends Transaction
{
  private String payee;

  public Payment(int amount, String payee, Date date)
  {
    super(amount, date);
    this.payee = payee;
  }

  public String getPayee()
  {
    return payee;
  }

  @Override
  public String asStatement()
  {
    return super.asStatement() + "Payment to " + payee  + ": (" + toDollars(getAmount()) + ")\n";
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

  @Override
  public Payment clone()
  {
    return (Payment)super.clone();
  }
}
