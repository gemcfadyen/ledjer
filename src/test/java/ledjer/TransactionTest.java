package ledjer;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTest {
	
	@Before
	public void resetTransactionNumber() {
		Transaction.resetNumber();
	}

	@Test
	public void eachTransactionHasADifferentIdNumber() {
		Transaction firstDeposit = new Deposit(500);
		Transaction secondDeposit = new Deposit(800);

		assertThat(firstDeposit.getNumber()).isEqualTo(1);
		assertThat(firstDeposit.getNumber()).isNotEqualTo(secondDeposit.getNumber());
	}

	@Test
	public void resetsTransactionNumber() {
		Transaction firstDeposit = new Deposit(303);
		Transaction.resetNumber();
		Transaction secondDeposit = new Deposit(394);

		assertThat(firstDeposit.getNumber()).isEqualTo(1);
		assertThat(secondDeposit.getNumber()).isEqualTo(1);

	}

}
