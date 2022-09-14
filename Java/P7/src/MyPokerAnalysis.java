import java.util.List;

/**
 * OPTIONAL extra credit class.
 * For up to 10 points extra credit, you can write your own poker analysis.
 * See the Extra Credit section of the P7 PDF for more details.
 */
public class MyPokerAnalysis implements PokerAnalyzer {

	private List<Card> cards;
	
	public MyPokerAnalysis(List<Card> cards) {
		
	}
	
	@Override
	public boolean hasPair() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasThreeOfAKind() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasTwoPair() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasFourOfAKind() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasFullHouse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasStraight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasFlush() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasStraightFlush() {
		// TODO Auto-generated method stub
		return false;
	}

}
