package ledjer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class TransactionDateOrderingTest {

	private Calendar firstDate = Calendar.getInstance();
	private Calendar secondDate = Calendar.getInstance();
	private Deposit earliestDeposit;
	private Deposit latestDeposit;
	private TransactionDateOrdering ordering = new TransactionDateOrdering();
	
	@Before
	public void setup() {
		firstDate.set(2000, 2, 3);
		secondDate.set(2000, 2, 4);
		
		earliestDeposit = new Deposit(1000, firstDate.getTime());
		latestDeposit = new Deposit(2000, secondDate.getTime());
	}
	
	@Test
	public void comparingEarlierTransactionToLater() {
		assertThat(ordering.compare(earliestDeposit, latestDeposit)).isEqualTo(-1);
	}
	
	@Test
	public void comparingLaterTransactionToEarlier() {
		assertThat(ordering.compare(latestDeposit, earliestDeposit)).isEqualTo(1);
	}
	
	@Test
	public void comparingTransactionsWithTheSameDate() {
		assertThat(ordering.compare(latestDeposit, latestDeposit)).isEqualTo(0);
	}
}
