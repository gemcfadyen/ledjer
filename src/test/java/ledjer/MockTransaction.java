package ledjer;

public class MockTransaction extends Transaction
{
  public MockTransaction()
  {
    super(0);
  }

  @Override
  public String asStatement()
  {
    return "Mock!";
  }
}
