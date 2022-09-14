/*
 * Julie Depenyou
 * UID: 115911437
 * CMSC131 0102
 * APR 7
 */
public class CheckPasswords {

	// method counts the numbers of uppercase letters in password
	// and return the number
	static int countUppercaseLetters(String password) {
		int count = 0;
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
				count++;
			}
		}
		return count;

	}

	// method counts the numbers of lowercase numbers in password
	// and return the number
	static int countLowercaseLetters(String password) {
		int count = 0;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {
				count++;
			}
		}
		return count;

	}

	// method counts the number of times the same char appears in
	// password and returns the number of the most repreated char
	static int longestConsecutiveIdenticalCharacters(String password) {
		int count = 0;
		int maxCount = 1;
		if (password.length() == 0) {
			return 0;
		}
		for (int i = 0; i < password.length() - 1; i++) {
			if (password.charAt(i) == password.charAt(i + 1)) {
				count++;
			}
			if (password.charAt(i) != password.charAt(i + 1)) {
				// break;
				if (count > maxCount) {
					maxCount = count;
				}
				count = 1;
			}
			if(i==password.length()-2) {
				if (count > maxCount) {
					maxCount = count+1;
				}
			}


		}
		return maxCount;
	}

	// method tests the similarity of password to dictionary word and
	// and return whether or not the two are similar
	static boolean similarToWord(String word, String password) {
		boolean similar = false;
		if (password.length() >= word.length() + 5) {
			similar = false;
		} else {
			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) == password.charAt(i)) {
					similar = true;
				} else if (word.charAt(i) == 'l' && password.charAt(i) == '1'
						|| word.charAt(i) == 's' && password.charAt(i) == '$'
						|| word.charAt(i) == 'o' && password.charAt(i) == '0'
						|| word.charAt(i) == 'a' && password.charAt(i) == '@') {
					similar = true;
				}
			}
		}
		return similar;

	}
	static boolean passwordLength(String password) {
		boolean length=false;
		if(password.length() >= 8 && password.length() <= 32) {
			length=true;
		}
		return length;
	}
	static boolean specialCharacters(String password) {
		boolean special=false;
		for(int i=0; i<password.length();i++) {
			if(password.charAt(i) >= '!' && password.charAt(i) <= '/'
							|| password.charAt(i) >= ':' && password.charAt(i) <= '@'
							|| password.charAt(i) >= ']' && password.charAt(i) <= '_'
							|| password.charAt(i) >= '{' && password.charAt(i) <= '}'
							|| password.charAt(i) >= '0' && password.charAt(i) <= '9') {
				special=true;
			}
		}return special;
	}
	static boolean spaceInWord(String password) {
		boolean space=true;;
		if(password.charAt(0) != ' ' && password.charAt(password.length() - 1) != ' ') {
			space=false;
		}return space;
		
	}

	// method checks if a given password meets all password requirements
	static boolean checkPassword(String password, String[] dictionary) {
		boolean passwordOK=true;
		if(countUppercaseLetters(password)<1) {
			passwordOK=false;
		}
		if(countLowercaseLetters(password)<1) {
			passwordOK=false;
		}
		if(passwordLength(password)==false) {
			passwordOK=false;
		}
		if(specialCharacters(password)==false) {
			passwordOK=false;
		}
		for (int i = 0; i < dictionary.length; i++) {
			if(similarToWord(dictionary[i],password)==true) {
				passwordOK=false;
			}
		}
		if(spaceInWord(password)==true) {
			passwordOK=false;
		}
		for(int i=0; i<password.length(); i++) {
			if(password.charAt(i)==13||password.charAt(i)==10||
		       password.charAt(i)=='/'||password.charAt(i)=='\\'||password.charAt(i)=='*') {
				passwordOK=false;
			}
		}
		return passwordOK;
			

	}

}
