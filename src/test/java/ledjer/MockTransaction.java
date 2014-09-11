package ledjer;

import java.util.Date;

public class MockTransaction extends Transaction
{
  public MockTransaction()
  {
    super(0, new Date());
  }

  @Override
  public String asStatement()
  {
    return "Mock!";
  }
}
