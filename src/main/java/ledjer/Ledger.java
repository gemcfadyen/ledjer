package ledjer;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Ledger implements Cloneable
{

  private int balance;
  private LinkedList<Transaction> transactions = new LinkedList<Transaction>();
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
    transactions.add(deposit);
    balance += deposit.getAmount();
  }

  public void pay(Transaction payment) throws NegativeBalanceException
  {
    if(payment.getAmount() > balance)
      throw new NegativeBalanceException((Payment)payment);
    transactions.add(payment);
    balance -= payment.getAmount();
  }

  public String statement()
  {
    String result = "";

    for(Transaction transaction : transactions)
      result += transaction.asStatement();

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
    if(!transactions.equals(that.transactions)) return false;

    return true;
  }

  @Override
  public int hashCode()
  {
    return balance + transactions.hashCode();
  }

  @Override
  public Ledger clone()
  {
    try
    {
      Ledger clone = (Ledger)super.clone();
      clone.transactions = new LinkedList<Transaction>();
      for(Transaction transaction : transactions)
        clone.transactions.add(transaction);
      return clone;
    }
    catch(CloneNotSupportedException e)
    {
      throw new RuntimeException("Can't clone Ledger", e);
    }
  }
}