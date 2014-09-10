package ledjer;

public abstract class Transaction
{
  protected int amount;

  public Transaction(int amount)
  {
    this.amount = amount;
  }

  public int getAmount()
  {
    return amount;
  }

  public abstract String asStatement();
}
