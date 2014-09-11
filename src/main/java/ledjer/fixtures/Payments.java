package ledjer.fixtures;

import ledjer.Payment;
import ledjer.Transaction;

import java.util.Date;

public class Payments
{
  private int amount;
  private String payee;
  private Date date;
  private Payment payment;

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

  public void setDate(String date) throws Exception
  {
    this.date = Transaction.dateFormat.parse(date);
  }

  public int number()
  {
    return payment.getNumber();
  }

  public void execute() throws Exception
  {
    payment = new Payment(amount, payee, date);
    Context.ledger.pay(payment);
  }
}
