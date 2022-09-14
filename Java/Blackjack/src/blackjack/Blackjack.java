package blackjack;

import java.util.*;

//import enumeratedTypes.Card;

public class Blackjack implements BlackjackEngine {

	/**
	 * Constructor you must provide. Initializes the player's account to 200 and the
	 * initial bet to 5. Feel free to initialize any other fields. Keep in mind that
	 * the constructor does not define the deck(s) of cards.
	 * 
	 * @param randomGenerator
	 * @param numberOfDecks
	 */
	private int numberOfDecks; //number of decks 
	private int pAccount; // how much money player has in account 
	private int inBet; //how player is betting 
	Random randomGenerator; //randomly generates a deck
	private ArrayList<Card> deckOfCards; //an array of cards to be dealt
	public static int gameStatus; //status of game
	private ArrayList<Card> playerDeck; //cards dealt to player
	private ArrayList<Card> dealerDeck; //cards dealt to dealer

	
	//constructor. sets up the game
	public Blackjack(Random randomGenerator, int numberOfDecks) {
		this.numberOfDecks = numberOfDecks;
		pAccount = 200;
		inBet = 5;
		this.randomGenerator = randomGenerator;
	}

	//method returns number of decks
	public int getNumberOfDecks() {
		return numberOfDecks;
	}

	//method creates a deck of cards for game and shuffles them
	public void createAndShuffleGameDeck() {
		deckOfCards = new ArrayList<Card>();
		for (int i = 0; i < numberOfDecks; i++) { //iterates through the deck of cards
			for (CardSuit cards : CardSuit.values()) { // iterates through the cards suit values
				for (CardValue c : CardValue.values()) { //  iterates through the CardValue values
					deckOfCards.add(new Card(c, cards)); // creates new cards of all suit and value combinations and adds them to game deck
				}
			}

		}
		Collections.shuffle(deckOfCards, this.randomGenerator);

	}

	//method returns an array of cards from game deck of cards
	public Card[] getGameDeck() {
		Card[] getGameD = new Card[this.deckOfCards.size()];
		return this.deckOfCards.toArray(getGameD);
	}

	/**
	 * Creates a new deck of cards, and assigns cards to the dealer and player. A
	 * total of four cards are dealt in the following order: Player (face up),
	 * Dealer (face down), Player (face up), Dealer (face up). Once the cards have
	 * been dealt, the game's status will be GAME_IN_PROGRESS. Delete the bet amount
	 * from the account.
	 */
	//method deals out the cards to player and dealer. 
	//calls the createAndShuffleGameDeck(). every card added to player
	//or dealer deck is removed from game deck
	public void deal() {
		createAndShuffleGameDeck();
		playerDeck = new ArrayList<Card>();
		dealerDeck = new ArrayList<Card>();

		playerDeck.add(deckOfCards.get(0));
		deckOfCards.remove(0);

		Card down = deckOfCards.get(0);
		deckOfCards.remove(0);
		down.setFaceDown();
		dealerDeck.add(down);

		playerDeck.add(deckOfCards.get(0));
		deckOfCards.remove(0);

		dealerDeck.add(deckOfCards.get(0));
		deckOfCards.remove(0);

		gameStatus = BlackjackEngine.GAME_IN_PROGRESS;

		setAccountAmount(pAccount - inBet);

	}

	/**
	 * Returns dealer's cards.
	 * @return Card array representing the dealer's cards.
	 */
	
	//method returns an array of the dealer's cards.
	public Card[] getDealerCards() {

		Card[] getDdeck = new Card[this.dealerDeck.size()];
		return this.dealerDeck.toArray(getDdeck);
	}



	/*
	 * This method calculates the sum total of the dealer's cards and returns 
	 * that sum in an array. If the dealer has an ace in its deck then the sum
	 * could be two different amounts and in those cases the method returns an 
	 * array of size 2 with the two possible totals
	 * */
	public int[] getDealerCardsTotal() {

		// System.out.println(dealerDeck);
		boolean checkAce = false;
		for (int i = 0; i < dealerDeck.size(); i++) { //iterate through dealer deck
			if (this.dealerDeck.get(i).getValue() == CardValue.Ace) { //important to check if there is an ace in deck
				// System.out.println("found ace");
				checkAce = true;
			}
		}
		int aceIsOne = 0, aceIsEleven = 0, noAce = 0; //vars for different sum situations
		if (checkAce) { //If there is an ace value then 2 diff sums will be calculated
			for (int i = 0; i < dealerDeck.size(); i++) { //iterate through game deck.
				aceIsOne = aceIsOne + this.dealerDeck.get(i).getValue().getIntValue(); //add values of game deck to aceIsOne sum
			}
			aceIsEleven = aceIsOne + 10;
			if (aceIsOne > 21) {

				return null;
			} else if (aceIsOne <= 21 && aceIsEleven > 21) {

				int[] value = { aceIsOne };
				return value;
			} else {

				int[] value = { aceIsOne, aceIsEleven };
				return value;
			}
		} else { //for when there is no ace
			for (int i = 0; i < dealerDeck.size(); i++) {
				noAce = noAce + this.dealerDeck.get(i).getValue().getIntValue();
			}
			if (noAce > 21) {
				return null;
			} else {
				int[] value = { noAce };
				return value;
			}
		}

	}

	

	/*
	 * This methods call the getDealerCardsTotal() to evaluate the meaning of
	 * the dealer's total. this method return an int based what the total was
	 * the assigned int values have already been defined in the BlackjackEngine
	 * interface
	 * */
	public int getDealerCardsEvaluation() {
		int cardEval = 0;
		
		//check to make sure method doesn't return null. If not proceed with code
		if (getDealerCardsTotal() != null) {
			if (getDealerCardsTotal()[0] < 21) {
				cardEval = BlackjackEngine.LESS_THAN_21;
			} else if (getDealerCardsTotal()[0] > 21) {
				cardEval = BlackjackEngine.BUST;
			} else if (getDealerCardsTotal().length == 2) {
				for (int i = 0; i < dealerDeck.size(); i++) {
					if (this.dealerDeck.get(i).getValue() == CardValue.Ten
							|| this.dealerDeck.get(i).getValue() == CardValue.Jack
							|| this.dealerDeck.get(i).getValue() == CardValue.Queen
							|| this.dealerDeck.get(i).getValue() == CardValue.King) {
						cardEval = BlackjackEngine.BLACKJACK;
					}
				}
			}

			else if (getDealerCardsTotal()[0] == 21) {
				cardEval = BlackjackEngine.HAS_21;
			}
		} else {//if getDealerCardsTotal does return null assume dealer BUST
			cardEval = BlackjackEngine.BUST;
		}

		return cardEval;

	}
	/*Method return an array of player's cards*/
	public Card[] getPlayerCards() {
		Card[] getPdeck = new Card[this.playerDeck.size()];
		return this.playerDeck.toArray(getPdeck);
	}

	/*
	 * This method calculates the sum total of the player's cards and returns 
	 * that sum in an array. If the player has an ace in its deck then the sum
	 * could be two different amounts and in those cases the method returns an 
	 * array of size 2 with the two possible totals
	 * */
	public int[] getPlayerCardsTotal() {
		boolean checkAce = false;
		for (int i = 0; i < playerDeck.size(); i++) { //iterate through player deck
			if (this.playerDeck.get(i).getValue() == CardValue.Ace) { //important to check if there is an ace in deck
				checkAce = true;
			}
		}
		int aceIsOne = 0, aceIsEleven = 0, noAce = 0; //vars for different sum situations
		if (checkAce) { //If there is an ace value then 2 diff sums will be calculated
			for (int i = 0; i < playerDeck.size(); i++) { //iterate through game deck.
				aceIsOne = aceIsOne + this.playerDeck.get(i).getValue().getIntValue(); //add values of game deck to aceIsOne sum
			}
			aceIsEleven = aceIsOne + 10;
			if (aceIsOne > 21) {
				return null;
			} else if (aceIsOne <= 21 && aceIsEleven > 21) {
				int[] value = { aceIsOne };
				return value;
			} else {
				int[] value = { aceIsOne, aceIsEleven };
				return value;
			}
		} else {  //for when there is no ace
			for (int i = 0; i < playerDeck.size(); i++) {
				noAce = noAce + this.playerDeck.get(i).getValue().getIntValue();
			}
			if (noAce > 21) {
				return null;
			} else {
				int[] value = { noAce };
				return value;
			}
		}
	}

	/**
	 * Returns an integer value that can assume the values LESS_THAN_21 if the
	 * player's cards have a value less than 21, BUST if the players's cards have a
	 * value greater than 21, and BLACKJACK if the player has an Ace along with a
	 * "10", Jack, Queen, or King. If the players' cards have a value equivalent to
	 * 21 and the hand does not correspond to a blackjack, HAS_21 will be returned.
	 * 
	 * @return Integer value that corresponds to one of the following: LESS_THAN_21,
	 *         BUST, BLACKJACK, HAS_21
	 */
	public int getPlayerCardsEvaluation() {
		int cardEval = 0;
		
		//check to make sure method doesn't return null. If not proceed with code
		if (getPlayerCardsTotal() != null) {
			int[] arr = new int[getPlayerCardsTotal().length];
			for (int i = 0; i < getPlayerCardsTotal().length; i++) {
				arr[i] = getPlayerCardsTotal()[i];
			}

			if (arr[0] < 21) {
				cardEval = BlackjackEngine.LESS_THAN_21;
			} else if (arr[0] > 21) {
				cardEval = BlackjackEngine.BUST;
			} else if (arr.length == 2) {
				for (int i = 0; i < playerDeck.size(); i++) {
					if (this.playerDeck.get(i).getValue() == CardValue.Ten
							|| this.playerDeck.get(i).getValue() == CardValue.Jack
							|| this.playerDeck.get(i).getValue() == CardValue.Queen
							|| this.playerDeck.get(i).getValue() == CardValue.King) {
						cardEval = BlackjackEngine.BLACKJACK;
					}
				}
			}

			else if (arr[0] == 21) {
				cardEval = BlackjackEngine.HAS_21;
			}

		} else { //if getPlayerCardsTotal does return null assume dealer BUST
			cardEval = BlackjackEngine.BUST;
		}

		return cardEval;
	}

	/**
	 * Retrieves a card from the deck and assigns the card to the player. The new
	 * sets of cards will be evaluated. If the player busts, the game is over and
	 * the games's status will be updated to DEALER_WON. Otherwise the game's status
	 * is GAME_IN_PROGRESS.
	 */

	public void playerHit() {

		playerDeck.add(deckOfCards.get(0));
		deckOfCards.remove(0);
		if (getPlayerCardsEvaluation() == BlackjackEngine.BUST) {
			gameStatus = BlackjackEngine.DEALER_WON;
		} else {
			gameStatus = BlackjackEngine.GAME_IN_PROGRESS;
		}
	}

	/**
	 * Flips the dealer's card that is currently face down and assigns cards to the
	 * dealer as long as the dealer doesn't bust and the cards have a value less
	 * than 16. Once the dealer has a hand with a value greater than or equal to 16,
	 * and less than or equal to 21, the hand will be compared against the player's
	 * hand and whoever has the hand with a highest value will win the game. If both
	 * have the same value we have a draw. The game's status will be updated to one
	 * of the following values: DEALER_WON, PLAYER_WON, or DRAW. The player's
	 * account will be updated with a value corresponding to twice the bet amount if
	 * the player wins. If there is a draw the player's account will be updated with
	 * the only the bet amount.
	 *
	 */
	public void playerStand() {

		Card assign = dealerDeck.get(0);
		assign.setFaceUp();
		deckOfCards.remove(assign);
		
		
		//this loop makes sure that the current dealer total fits the set parameters
		while (getDealerCardsEvaluation() != BlackjackEngine.BUST
				&& getDealerCardsTotal()[getDealerCardsTotal().length - 1] < 16) {
			Card assign2 = deckOfCards.get(0);
			dealerDeck.add(assign2);
			deckOfCards.remove(assign2);	
		}
		

		if (getDealerCardsTotal() != null) { //important to check that the array isn't null
			if (getDealerCardsTotal()[0] >= 16
					&& getDealerCardsTotal()[0] <= 21) {
				
				//series of if statements that determine who has won
				//important to check for both length 1 and length 2
				if (getDealerCardsTotal()[0] > getPlayerCardsTotal()[0]) {
					gameStatus = BlackjackEngine.DEALER_WON;
				} else if (getDealerCardsTotal()[0] < getPlayerCardsTotal()[0]) {
					gameStatus = BlackjackEngine.PLAYER_WON;
					setAccountAmount((inBet * 2) + pAccount);

				} else if (getDealerCardsTotal()[0] == getPlayerCardsTotal()[0]) {
					gameStatus = BlackjackEngine.DRAW;
					setAccountAmount(inBet + pAccount);
				} else if (getDealerCardsTotal()[1] == getPlayerCardsTotal()[1]) {
					gameStatus = BlackjackEngine.DRAW;
					setAccountAmount(inBet + pAccount);
				} else if (getDealerCardsTotal()[1] > getPlayerCardsTotal()[1]) {
					gameStatus = BlackjackEngine.DEALER_WON;
				} else if (getDealerCardsTotal()[1] < getPlayerCardsTotal()[1]) {
					gameStatus = BlackjackEngine.PLAYER_WON;
					setAccountAmount((inBet * 2) + pAccount);

				}
				
			}

		} else { //if the array is null assume dealer busted.
			gameStatus = BlackjackEngine.PLAYER_WON;
			setAccountAmount((inBet * 2) + pAccount);


		}
		
	}

	public int getGameStatus() {
		return gameStatus;
	}

	public void setBetAmount(int amount) {
		this.inBet = amount;
	}

	public int getBetAmount() {
		return inBet;
	}

	public void setAccountAmount(int amount) {
		this.pAccount = amount;
	}

	public int getAccountAmount() {
		return pAccount;
	}

	/* Feel Free to add any private methods you might need */
}