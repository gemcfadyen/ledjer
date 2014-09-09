package ledjer;

public class Ledger
{

  private int balance;
  private Deposit[] deposits = new Deposit[10];
  private int depositIndex = 0;

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

  public String statement()
  {
    String result = "";
    for(int i = 0; i < depositIndex; i++)
    {
      Deposit deposit = deposits[i];
      result += String.format("Deposit: $%.2f\n", deposit.getAmount() / 100.0);
    }
    result += String.format("Total: $%.2f", balance / 100.0);
    return result;
  }
}