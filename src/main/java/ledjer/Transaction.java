package ledjer;

public abstract class Transaction
{
  public static int currentSequenceNumber = 1;
  protected int amount;
  private int number;

  private static int nextNumber()
  {
    return currentSequenceNumber++;
  }

  public static void resetSequenceNumber()
  {
    currentSequenceNumber = 1;
  }

  public Transaction(int amount)
  {
    number = nextNumber();
    this.amount = amount;
  }

  public int getAmount()
  {
    return amount;
  }

  public abstract String asStatement();

  public int getNumber()
  {
    return number;
  }
}
