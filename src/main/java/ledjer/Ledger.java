package ledjer;

public class Ledger
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
}