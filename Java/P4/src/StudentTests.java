import static org.junit.Assert.*;
import org.junit.Test;

public class StudentTests {

	@Test
	public void testSomething() {
		
		// add statements for testing
		
	}
	@Test
	public void longestConsecutiveCharacters() {
		assertEquals(2,
				CheckPasswords.longestConsecutiveIdenticalCharacters("AABBCCDDe"));
		assertEquals(2,
				CheckPasswords.longestConsecutiveIdenticalCharacters("abcde112"));
		assertEquals(0, CheckPasswords.longestConsecutiveIdenticalCharacters(""));
		assertEquals(9, CheckPasswords.longestConsecutiveIdenticalCharacters("qqqqqqqqq"));
		assertEquals(3, CheckPasswords.longestConsecutiveIdenticalCharacters("aabbcccd"));

		

		
	}
	@Test
	public void similarToWordExactMatch() {
		assertEquals(true, CheckPasswords.similarToWord("Bananas", "banana$"));
		assertEquals(false, CheckPasswords.similarToWord("Banana", "Banana12345"));
		assertEquals(true, CheckPasswords.similarToWord("apple", "aappleex"));

	}

	

}
