package ledjer;

import java.util.Arrays;

public class Ledger implements Cloneable
{

  private int balance;
  private Transaction[] transactions = new Transaction[10];
  private int transactionIndex = 0;

  public Ledger()
  {
    balance = 0;
  }

  public int getBalance()
  {
    return balance;
  }

  public void deposit(Transaction deposit)
  {
    transactions[transactionIndex++] = deposit;
    balance += deposit.getAmount();
  }

  public void pay(Transaction payment) throws NegativeBalanceException
  {
    if(payment.getAmount() > balance)
      throw new NegativeBalanceException((Payment)payment);
    transactions[transactionIndex++] = payment;
    balance -= payment.getAmount();
  }

  public String statement()
  {
    String result = "";

    for(int i = 0; i < transactionIndex; i++)
      result += transactions[i].asStatement();

    result += String.format("Total: $%.2f", balance / 100.0);
    return result;
  }

  @Override
  public boolean equals(Object o)
  {
    if(this == o) return true;
    if(! (o instanceof Ledger)) return false;

    Ledger that = (Ledger) o;

    if(balance != that.balance) return false;
    if(!Arrays.equals(transactions, that.transactions)) return false;

    return true;
  }

  @Override
  public int hashCode()
  {
    return balance + Arrays.hashCode(transactions);
  }

  @Override
  public Ledger clone()
  {
    try
    {
      Ledger clone = (Ledger)super.clone();
      clone.transactions = transactions.clone();
      for(int i = 0; i < transactionIndex; i++)
        clone.transactions[i] = transactions[i].clone();
      return clone;
    }
    catch(CloneNotSupportedException e)
    {
      throw new RuntimeException("Can't clone Ledger", e);
    }
  }
}