package ledjer;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;

public class SaveLedgerExceptionTest {

	@Test
	public void usefulMessageInException() {
		SaveLedgerException exception = new SaveLedgerException("useful message", new RuntimeException());
		
		assertThat(exception.getMessage()).isEqualTo("useful message");
	}
	
	@Test
	public void throwableInException() {
		Throwable originalException = new IOException();
		SaveLedgerException exception = new SaveLedgerException("a message", originalException);
		
		assertThat(exception.getCause()).isEqualTo(originalException);
	}
}
