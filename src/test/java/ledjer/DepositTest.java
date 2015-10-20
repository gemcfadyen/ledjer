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
		int idNumber = deposit.getNumber();
		assertThat(deposit.asStatement()).isEqualTo(idNumber + ". Deposit: £1.20\n");
	}

	@Test
	public void depositsAtTheSameAddressAreEqual() {
		Deposit deposit = new Deposit(123);
		Deposit theSameDeposit = deposit;

		assertThat(deposit).isEqualTo(theSameDeposit);
	}

	@Test
	public void depositsThatHaveTheSameAmountAndTransactionNumbersAreEqual() {
		Transaction.resetNumber();
		Deposit deposit = new Deposit(213);
		Transaction.resetNumber();
		Deposit anotherDeposit = new Deposit(213);

		assertThat(deposit).isEqualTo(anotherDeposit);
	}
	
	@Test
	public void depositsThatHaveDifferentTransactionNumbersAreNotEqual() {
		Deposit deposit = new Deposit(199);
		Deposit anotherDeposit = new Deposit(199);
		
		assertThat(deposit).isNotEqualTo(anotherDeposit);
	}

	@Test
	public void depositsThatHaveDifferentAmountsAreNotEqual() {
		Deposit deposit = new Deposit(234);
		Deposit anotherDeposit = new Deposit(4533);

		assertThat(deposit).isNotEqualTo(anotherDeposit);
	}
	
	@Test
	public void depositIsNotEqualToAnotherObject() {
		Deposit deposit = new Deposit(2134);
		Payment payment = new Payment(2134, "company-A");
		
		assertThat(deposit).isNotEqualTo(payment);
	}
}