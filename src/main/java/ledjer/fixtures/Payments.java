package ledjer.fixtures;

import ledjer.Payment;

public class Payments
{
  public int amount;
  public String payee;

  public void setAmount(int amount)
  {
    this.amount = amount;
  }

  public void setPayee(String payee)
  {
    this.payee = payee;
  }

  public int balance()
  {
    return Context.ledger.getBalance();
  }

  public void execute()
  {
    Context.ledger.pay(new Payment(amount, payee));
  }
}
