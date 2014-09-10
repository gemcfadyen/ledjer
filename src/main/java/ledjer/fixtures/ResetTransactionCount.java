package ledjer.fixtures;

import ledjer.Transaction;

public class ResetTransactionCount
{
  public ResetTransactionCount()
  {
    Transaction.resetSequenceNumber();
  }
}
