package ledjer.fixtures;

import ledjer.Deposit;
import ledjer.Ledger;
import ledjer.NegativeBalanceException;
import ledjer.Payment;

public class EmptyLedger {

  private Class exceptionClass;

  public EmptyLedger() {
		Context.ledger = new Ledger();
	}

  public void makePaymentAndCatchException(int amount)
  {
    try
    {
      Context.ledger.pay(new Payment(amount, "Someone"));
    }
    catch(NegativeBalanceException e)
    {
      exceptionClass = e.getClass();
    }
  }

  public Class exceptionClass()
  {
    return exceptionClass;
  }

  public void depositThisManyTimes(int amount, int times)
  {
    for(int i = 0; i < times; i++)
      Context.ledger.deposit(new Deposit(amount));
  }

  public int balance()
  {
    return Context.ledger.getBalance();
  }
}