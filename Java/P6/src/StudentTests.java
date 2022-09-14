import static org.junit.Assert.*;
import org.junit.Test;

public class StudentTests {

	@Test
    public void testTwitterDemo() {
		assertTrue(Twitter.ONLY_INSTANCE.signUp("@facebook"));
	}
}
