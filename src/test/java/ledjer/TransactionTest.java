package ledjer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionTest
{
  @Test
  public void resettingSequenceNumber() throws Exception
  {
    Transaction.resetSequenceNumber();
    assertEquals(1, Transaction.currentSequenceNumber);

    Transaction.resetSequenceNumber();
    assertEquals(1, Transaction.currentSequenceNumber);
  }

  @Test
  public void sequenceNumberAssignedOnConstruction() throws Exception
  {
    Transaction.resetSequenceNumber();
    assertEquals(1, new MockTransaction().getNumber());
    assertEquals(2, new MockTransaction().getNumber());
    assertEquals(3, new MockTransaction().getNumber());

  }
}
