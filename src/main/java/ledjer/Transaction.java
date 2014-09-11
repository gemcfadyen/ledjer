package ledjer;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Transaction implements Cloneable, Comparable<Transaction>
{
  public static int currentSequenceNumber = 1;
  public static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
  public static DecimalFormat dollarFormat = new DecimalFormat("$0.00");

  private final Date date;
  private int amount;
  private int number;

  private static int nextNumber()
  {
    return currentSequenceNumber++;
  }

  public static void resetSequenceNumber()
  {
    currentSequenceNumber = 1;
  }

  public static  String toDollars(int pennies)
  {
    return dollarFormat.format(pennies / 100.0);
  }

  public Transaction(int amount, Date date)
  {
    number = nextNumber();
    this.amount = amount;
    this.date = date;
  }

  public int getAmount()
  {
    return amount;
  }

  public Date getDate()
  {
    return date;
  }

  public String asStatement()
  {
    return dateFormat.format(date) + " " + number + ". ";
  }

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

  @Override
  public int compareTo(Transaction that)
  {
    return this.date.compareTo(that.date);
  }
}
