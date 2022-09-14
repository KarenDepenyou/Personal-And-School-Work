import java.util.Random;

public class PasswordGenerator {

	static String generatePassword(int wordCount, Random r, String[] words) {
		//throw new UnsupportedOperationException("Replace this line with your implementation.");
		String newStr="";
		//words.length=wordCount;
		for(int i=0; i<wordCount; i++) {
			//Random rand= new Random(wordCount);
			int rand = r.nextInt(words.length);
			if(newStr.contains(words[rand])) {
				i--;
			}
			else {
				newStr=newStr+words[rand];
			}
			//rand.nextInt(words.length);
			//newStr=newStr+words[rand];
		}
		return newStr;
	}
}
