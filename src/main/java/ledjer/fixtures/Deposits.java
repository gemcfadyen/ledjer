package ledjer.fixtures;

import ledjer.Deposit;

public class Deposits
{

  public int amount;
  public Deposit deposit;

  public void setAmount(int amount)
  {
    this.amount = amount;
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
    deposit = new Deposit(amount);
    Context.ledger.deposit(deposit);
  }
}