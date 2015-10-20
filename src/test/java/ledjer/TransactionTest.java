package ledjer;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTest {
	
	@Test
	public void eachTransactionHasADifferentIdNumber() {
		Transaction firstDeposit = new Deposit(500);
		Transaction secondDeposit = new Deposit(800);
		
		assertThat(firstDeposit.getNumber()).isNotEqualTo(secondDeposit.getNumber());
	}
	
}
