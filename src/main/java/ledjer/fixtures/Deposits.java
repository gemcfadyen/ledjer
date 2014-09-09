package ledjer.fixtures;

import ledjer.Deposit;

public class Deposits
{

  public int amount;

  public void setAmount(int amount)
  {
    this.amount = amount;
  }

  public int balance()
  {
    return Context.ledger.getBalance();
  }

  public void execute()
  {
    Context.ledger.deposit(new Deposit(amount));
  }
}