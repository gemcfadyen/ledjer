package ledjer.fixtures;

import ledjer.Payment;

public class Payments
{
  public int amount;
  public String payee;
  public Payment payment;

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

  public int number()
  {
    return payment.getNumber();
  }

  public void execute() throws Exception
  {
    payment = new Payment(amount, payee);
    Context.ledger.pay(payment);
  }
}
