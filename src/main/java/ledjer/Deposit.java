package ledjer;

public class Deposit extends Transaction
{

  public Deposit(int amount)
  {
    super(amount);
  }

  @Override
  public String asStatement()
  {
    return String.format("%d. Deposit: $%.2f\n", getNumber(), getAmount() / 100.0);
  }

  @Override
  public Deposit clone()
  {
    return (Deposit)super.clone();
  }
}
