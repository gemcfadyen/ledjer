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

		assertThat(payment.asStatement()).isEqualTo("Payment to Ikea: (£2.50)\n");
	}
}
