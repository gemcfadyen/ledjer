package ledjer;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PoundConverterTest {

	@Test
	public void converts100pToOnePound() {
		String pounds = PoundConverter.convertForDisplay(100);
		assertThat(pounds).isEqualTo("£1.00");
	}
	
	@Test
	public void converts120ToOnePoundTwentyPence() {
		String pounds = PoundConverter.convertForDisplay(120);
		assertThat(pounds).isEqualTo("£1.20");
	}
}
