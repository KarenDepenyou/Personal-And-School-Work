import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the PokerAnalyzer interface. It will analyze the List
 * of Cards it is given at construction. Most of the tests will only give 5
 * cards in the List, like real Poker, but some tests will contain more than 5
 * cards. In that case, you have a hand (say, a flush) if ANY 5 of those cards
 * have that hand (flush in this case).
 *
 */
public class PokerAnalysis implements PokerAnalyzer {

	private List<Card> cards;
	private int[] rankCounts;
	private int[] suitCounts;

	/**
	 * The constructor has been partially implemented for you. cards is the
	 * ArrayList where you'll be adding all the cards you're given. In addition,
	 * there are two arrays. You don't necessarily need to use them, but using them
	 * will be extremely helpful.
	 * 
	 * The rankCounts array is of the same length as the number of Ranks. At
	 * position i of the array, keep a count of the number of cards whose
	 * rank.ordinal() equals i. Repeat the same with Suits for suitCounts. For
	 * example, if your Cards are (Clubs 4, Clubs 10, Spades 2), your suitCounts
	 * array would be {2, 0, 0, 1}.
	 * 
	 * @param cards
	 *            the list of cards to be added
	 */
	public PokerAnalysis(List<Card> cards) {
		this.cards = new ArrayList<Card>();
		this.rankCounts = new int[Rank.values().length];
		this.suitCounts = new int[Suit.values().length];

//		throw new UnsupportedOperationException();
		this.cards.addAll(cards);
		for(Card c: this.cards) {
			this.rankCounts[c.getRank().ordinal()]++;
			this.suitCounts[c.getSuit().ordinal()]++;
		}
	}

	@Override
	public boolean hasPair() {
//		throw new UnsupportedOperationException();
		for(int i=0; i<rankCounts.length; i++) {
			if(rankCounts[i]==2) {
				return true;
			}
		}return false;
	}

	@Override
	public boolean hasThreeOfAKind() {
//		throw new UnsupportedOperationException();
		for(int i=0; i<rankCounts.length; i++) {
			if(rankCounts[i]==3) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasTwoPair() {
		int count=0;
//		throw new UnsupportedOperationException();
		for(int i=0; i<rankCounts.length; i++) {
			if(rankCounts[i]==2) {
				count++;
				
			}
			
		}
		if(count>=2) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasFourOfAKind() {
//		throw new UnsupportedOperationException();
		for(int i=0; i<rankCounts.length; i++) {
			if(rankCounts[i]==4) {
				return true;
			}
		}
		return false;
	
	}

	@Override
	public boolean hasFullHouse() {
//		throw new UnsupportedOperationException();
		return hasThreeOfAKind()&& hasPair();
	}

	/**
	 * You don't need to implement this, but it will be helpful. This method returns
	 * true if there is a straight hand starting with the Rank r and false
	 * otherwise. As the Wikipedia page says, no straight hand can start with a
	 * Jack, Queen or King. Also look into the nextRank() method of the Rank enum.
	 */
	private boolean hasStraight(Rank r) {
//		throw new UnsupportedOperationException();
		for(int i=0; i<5; i++) {
			if(rankCounts[r.ordinal()]==0) {
				return false;
			}r=r.nextRank();
		}return true;
	}

	@Override
	public boolean hasStraight() {
		//throw new UnsupportedOperationException();
		for(Rank rank: Rank.values()) {
			if(hasStraight(rank)) {
				return true;
			}
		}return false;

	}

	@Override
	public boolean hasFlush() {
		//throw new UnsupportedOperationException();
		for(int i=0; i<suitCounts.length; i++) {
			if(suitCounts[i]==5) {
				return true;
			}
		}return false;
	}

	/**
	 * Private helper similar to hasStraight(Rank r), but this time you consider
	 * suit also. Optional, but very helpful to write.
	 */
	private boolean hasStraightFlush(Rank r, Suit s) {
		throw new UnsupportedOperationException();
	
	}

	@Override
	public boolean hasStraightFlush() {
		//throw new UnsupportedOperationException();
		return hasStraight()&&hasFlush();
	}

}
