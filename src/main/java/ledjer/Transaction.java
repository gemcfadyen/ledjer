package ledjer;

public abstract class Transaction implements Cloneable
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

  @Override
  public boolean equals(Object o)
  {
    if(this == o) return true;
    if(!(o instanceof Transaction)) return false;

    Transaction that = (Transaction) o;

    if(amount != that.amount) return false;
//    Maybe we should check number... but that makes things less fun.
//    if(number != that.number) return false;

    return true;
  }

  @Override
  public int hashCode()
  {
    return amount;
  }

  @Override
  public Transaction clone()
  {
    try
    {
      return (Transaction)super.clone();
    }
    catch(CloneNotSupportedException e)
    {
      throw new RuntimeException("Can't clone Transaction", e);
    }
  }
}
