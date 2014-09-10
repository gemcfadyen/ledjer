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
    return String.format("Deposit: $%.2f\n", getAmount() / 100.0);
  }

}