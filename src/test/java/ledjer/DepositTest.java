package ledjer;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;

public class DepositTest {

	@Test
	public void getsAmountOfDeposit() {
		Deposit deposit = new Deposit(8, new Date());

		assertThat(deposit.getAmount()).isEqualTo(8);
	}

	@Test
	public void depositStatementDetails() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2013,  4, 17);
		
		Deposit deposit = new Deposit(120, calendar.getTime());
		int idNumber = deposit.getNumber();
		assertThat(deposit.asStatement()).isEqualTo("May 17, 2013 " + idNumber + ". Deposit: £1.20\n");
	}

	@Test
	public void depositsAtTheSameAddressAreEqual() {
		Deposit deposit = new Deposit(123, new Date());
		Deposit theSameDeposit = deposit;

		assertThat(deposit).isEqualTo(theSameDeposit);
	}

	@Test
	public void depositsThatHaveTheSameAttributesAreEqual() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1997, 6, 22);
		
		Transaction.resetNumber();
		Deposit deposit = new Deposit(213, calendar.getTime());
		Transaction.resetNumber();
		Deposit anotherDeposit = new Deposit(213, calendar.getTime());

		assertThat(deposit).isEqualTo(anotherDeposit);
	}
	
	@Test
	public void depositsThatHaveDifferentDatesAreNotEqual() {
		Calendar calendarForJune = Calendar.getInstance();
		calendarForJune.set(1997, 6, 22);
		
		Calendar calendarForApril = Calendar.getInstance();
		calendarForApril.set(1997, 4, 22);
		
		Transaction.resetNumber();
		Deposit deposit = new Deposit(213, calendarForJune.getTime());
		Transaction.resetNumber();
		Deposit anotherDeposit = new Deposit(213, calendarForApril.getTime());

		assertThat(deposit).isNotEqualTo(anotherDeposit);
	}
	
	@Test
	public void depositsThatHaveDifferentTransactionNumbersAreNotEqual() {
		Deposit deposit = new Deposit(199, new Date());
		Deposit anotherDeposit = new Deposit(199, new Date());
		
		assertThat(deposit).isNotEqualTo(anotherDeposit);
	}

	@Test
	public void depositsThatHaveDifferentAmountsAreNotEqual() {
		Deposit deposit = new Deposit(234, new Date());
		Deposit anotherDeposit = new Deposit(4533, new Date());

		assertThat(deposit).isNotEqualTo(anotherDeposit);
	}
	
	@Test
	public void depositIsNotEqualToAnotherObject() {
		Deposit deposit = new Deposit(2134, new Date());
		Payment payment = new Payment(2134, "company-A", new Date());
		
		assertThat(deposit).isNotEqualTo(payment);
	}
}