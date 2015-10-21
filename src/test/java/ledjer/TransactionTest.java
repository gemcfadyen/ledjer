package ledjer;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;

public class TransactionTest {
	
	@Before
	public void resetTransactionNumber() {
		Transaction.resetNumber();
	}

	@Test
	public void eachTransactionHasADifferentIdNumber() {
		Transaction firstDeposit = new Deposit(500, new Date());
		Transaction secondDeposit = new Deposit(800, new Date());

		assertThat(firstDeposit.getNumber()).isEqualTo(1);
		assertThat(firstDeposit.getNumber()).isNotEqualTo(secondDeposit.getNumber());
	}

	@Test
	public void resetsTransactionNumber() {
		Transaction firstDeposit = new Deposit(303, new Date());
		Transaction.resetNumber();
		Transaction secondDeposit = new Deposit(394, new Date());

		assertThat(firstDeposit.getNumber()).isEqualTo(1);
		assertThat(secondDeposit.getNumber()).isEqualTo(1);

	}
	
	@Test
	public void aTransactionHasADate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, 4, 23);
		Transaction deposit = new Deposit(3443, calendar.getTime());
		
		assertThat(deposit.getDate()).isEqualTo(calendar.getTime());
	}

}
