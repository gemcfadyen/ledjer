package ledjer.fixtures;

import ledjer.Deposit;
import ledjer.Transaction;

import java.util.Date;

public class Deposits
{
  private int amount;
  private Deposit deposit;
  private Date date;

  public void setAmount(int amount)
  {
    this.amount = amount;
  }

  public void setDate(String date) throws Exception
  {
    this.date = Transaction.dateFormat.parse(date);
  }

  public int balance()
  {
    return Context.ledger.getBalance();
  }

  public int number()
  {
    return deposit.getNumber();
  }

  public void execute()
  {
    deposit = new Deposit(amount, date);
    Context.ledger.deposit(deposit);
  }
}