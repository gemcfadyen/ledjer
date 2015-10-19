package ledjer;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DepositTest {

	@Test
	public void getsAmountOfDeposit() {
		Deposit deposit = new Deposit(8);

		assertThat(deposit.getAmount()).isEqualTo(8);
	}
	
	@Test
	public void depositStatementDetails() {
		Deposit deposit = new Deposit(120);
		assertThat(deposit.asStatement()).isEqualTo("Deposit: £1.20\n");
	}
}