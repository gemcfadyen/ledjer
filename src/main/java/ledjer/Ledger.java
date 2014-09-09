package ledjer;

public class Ledger
{

  private int balance;
  private Deposit[] deposits = new Deposit[10];
  private int depositIndex = 0;
  private Payment[] payments = new Payment[10];
  private int paymentIndex = 0;

  public Ledger()
  {
    balance = 0;
  }

  public int getBalance()
  {
    return balance;
  }

  public void deposit(Deposit deposit)
  {
    deposits[depositIndex++] = deposit;
    balance += deposit.getAmount();
  }

  public void pay(Payment payment)
  {
    payments[paymentIndex++] = payment;
    balance -= payment.getAmount();
  }

  public String statement()
  {
    String result = "";
    for(int i = 0; i < depositIndex; i++)
    {
      Deposit deposit = deposits[i];
      result += String.format("Deposit: $%.2f\n", deposit.getAmount() / 100.0);
    }
    for(int i = 0; i < paymentIndex; i++)
    {
      Payment payment = payments[i];
      result += String.format("Payment to %s: ($%.2f)\n", payment.getPayee(), payment.getAmount() / 100.0);
    }
    result += String.format("Total: $%.2f", balance / 100.0);
    return result;
  }
}