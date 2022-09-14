import java.util.Comparator;

/**
 * A comparator that compares cards.
 * 
 * If suitMatters is false, then suite is ignored when comparing cards. For
 * example compare(FIVE_OF_HEARTS,FIVE_OF_CLUBS) should return 0.
 * 
 * If suitMatters is true, then ONLY when comparing two cards with the same rank, 
 * the cards are ordered by their suit (according to the natural order of Suit,
 * which means the order which they are in in the Suit Enum). For example,
 * compare(FIVE_OF_HEARTS, FIVE_OF_CLUBS) should return a positive number.
 * 
 * In other words, compare by rank first. Then, IF the ranks are same AND
 * suitMatters is true, compare by suit also.
 */
public class CardComparator implements Comparator<Card> {

	final boolean suitMatters;
	
	/**
	 * Constructor that takes the flag for whether the
	 * suit matters or not.
	 */
	public CardComparator(boolean suitMatters) {
//		throw new UnsupportedOperationException();
		this.suitMatters=suitMatters;
	}

	/**
	 * Simple getter
	 */
	public boolean doesSuitMatter() {
		return this.suitMatters;
	}

	@Override
	public int compare(Card o1, Card o2) {
		int comparator= o1.getRank().compareTo(o2.getRank());
		if(!suitMatters) {
			return comparator;	
		}
		if(suitMatters) {
			if(o1.getRank()==o2.getRank()) {
			comparator= o1.getSuit().compareTo(o2.getSuit());
		}
			
		} return comparator;
	
	}

}
