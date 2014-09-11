package ledjer;

import java.util.Date;

public class Deposit extends Transaction
{

  public Deposit(int amount, Date date)
  {
    super(amount, date);
  }

  @Override
  public String asStatement()
  {
    return super.asStatement() + "Deposit: " + toDollars(getAmount()) + "\n";
  }

  @Override
  public Deposit clone()
  {
    return (Deposit)super.clone();
  }
}
