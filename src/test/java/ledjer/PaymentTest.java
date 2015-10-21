package ledjer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class PaymentTest {

	@Test
	public void paymentAmount() {
		Payment payment = new Payment(10, "payee", new Date());

		assertThat(payment.getAmount()).isEqualTo(10);
	}

	@Test
	public void payeeName() {
		Payment payment = new Payment(100, "Amazon", new Date());
		assertThat(payment.getPayee()).isEqualTo("Amazon");
	}

	@Test
	public void payeeStatementDetails() {
		Calendar paymentDate = Calendar.getInstance();
		paymentDate.set(1998, 11, 13);
		Payment payment = new Payment(250, "Ikea", paymentDate.getTime());
		int idNumber = payment.getNumber();
		
		assertThat(payment.asStatement()).isEqualTo("Dec 13, 1998 " + idNumber + ". Payment to Ikea: (£2.50)\n");
	}
	
	@Test
	public void paymentsAtTheSameAddressAreEqual() {
		Payment payment = new Payment(123, "Amazon", new Date());
		Payment anotherPayment = payment;
		
		assertThat(payment).isEqualTo(anotherPayment);
	}
	
	@Test
	public void paymentsWithTheSameAttributesAreEqual() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2008, 5, 17);
		
		Transaction.resetNumber();
		Payment payment = new Payment(34423, "Apple", calendar.getTime());
		Transaction.resetNumber();
		Payment anotherPayment = new Payment(34423, "Apple", calendar.getTime());
		
		assertThat(payment).isEqualTo(anotherPayment);
	}
	
	@Test
	public void paymentsWithDifferentDatesAreNotEqual() {
		Calendar calendarOne = Calendar.getInstance();
		calendarOne.set(2008, 5, 17);
		
		Calendar calendarTwo = Calendar.getInstance();
		calendarTwo.set(2009, 3, 11);
		
		Transaction.resetNumber();
		Payment payment = new Payment(34423, "Apple", calendarOne.getTime());
		Transaction.resetNumber();
		Payment anotherPayment = new Payment(34423, "Apple", calendarTwo.getTime());
		
		assertThat(payment).isNotEqualTo(anotherPayment);
	}
	
	@Test
	public void paymentsWithDifferentTransactionNumbersAreNotEqual() {
		Payment payment = new Payment(34423, "Apple", new Date());
		Payment anotherPayment = new Payment(34423, "Apple", new Date());
		
		assertThat(payment).isNotEqualTo(anotherPayment);
	}
	
	@Test
	public void paymentIsNotEqualToAnotherObject() {
		Payment payment = new Payment(23, "Ikea", new Date());
		Deposit deposit = new Deposit(23, new Date());
		assertThat(payment).isNotEqualTo(deposit);
	}
}
