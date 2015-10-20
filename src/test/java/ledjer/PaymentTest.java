package ledjer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PaymentTest {

	@Test
	public void paymentAmount() {
		Payment payment = new Payment(10, "payee");

		assertThat(payment.getAmount()).isEqualTo(10);
	}

	@Test
	public void payeeName() {
		Payment payment = new Payment(100, "Amazon");
		assertThat(payment.getPayee()).isEqualTo("Amazon");
	}

	@Test
	public void payeeStatementDetails() {
		Payment payment = new Payment(250, "Ikea");
		int idNumber = payment.getNumber();
		
		assertThat(payment.asStatement()).isEqualTo(idNumber + ". Payment to Ikea: (£2.50)\n");
	}
	
	@Test
	public void paymentsAtTheSameAddressAreEqual() {
		Payment payment = new Payment(123, "Amazon");
		Payment anotherPayment = payment;
		
		assertThat(payment).isEqualTo(anotherPayment);
	}
	
	@Test
	public void paymentsWithTheSameAmountAndPayeeAndTransactionNumberAreEqual() {
		Transaction.resetNumber();
		Payment payment = new Payment(34423, "Apple");
		Transaction.resetNumber();
		Payment anotherPayment = new Payment(34423, "Apple");
		
		assertThat(payment).isEqualTo(anotherPayment);
	}
	
	@Test
	public void paymentsWithDifferentTransactionNumbersAreNotEqual() {
		Payment payment = new Payment(34423, "Apple");
		Payment anotherPayment = new Payment(34423, "Apple");
		
		assertThat(payment).isNotEqualTo(anotherPayment);
	}
	
	@Test
	public void paymentIsNotEqualToAnotherObject() {
		Payment payment = new Payment(23, "Ikea");
		Deposit deposit = new Deposit(23);
		assertThat(payment).isNotEqualTo(deposit);
	}
}
