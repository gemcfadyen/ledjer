package ledjer;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class TransactionTest {
	private class TestTransaction extends Transaction {
		public TestTransaction(int amount) {
			super(amount);
		}
		@Override
		public String asStatement() {
			return null;
		}
	}
	
	@Test
	public void constructsTransaction() {
		Transaction transaction = new TestTransaction(1000);
		assertEquals(1000, transaction.getAmount());
		assertEquals(1, transaction.getNumber());
	}
	
	@Test
	public void assignsNumbersSequentially() {
		Transaction.resetNextNumber();
		Transaction t1 = new TestTransaction(10);
		Transaction t2 = new TestTransaction(10);
		Transaction t3 = new TestTransaction(10);
		assertEquals(1, t1.getNumber());
		assertEquals(2, t2.getNumber());
		assertEquals(3, t3.getNumber());
	}
	
	@Test
	public void resetNextNumber() {
		new TestTransaction(10);
		new TestTransaction(10);
		new TestTransaction(10);
		Transaction.resetNextNumber();
		assertEquals(1, new TestTransaction(10).getNumber());
	}
	
	@Test
	public void assignsTodaysDate() {
		Transaction transaction = new TestTransaction(1000);
		Calendar cal = new GregorianCalendar();
		Date today = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		assertEquals(format.format(today), format.format(transaction.getDate()));
	}
	
	
}