/*
 * Julie Depenyou
 * CMSC131 Yoon
 * UID:115911437
 * Honor Pledge:I pledge on my honor that I have not given or received any 
 * unauthorized assistance on this assignment/examination 
 */
/**
 * This class describes a tweet. A tweet has a message in it, a count of the
 * number of likes, and a count of the number of times it has been retweeted.
 * Unlike P5, tweets now have an upper limit on size. There is no ID this time.
 * 
 * You may NOT import any Java standard library class in this file, even
 * java.util.ArrayList.
 * 
 * @author <Julie Depenyou>
 *
 */
public class Tweet {

	public static final int MAX_LENGTH = 140;
	private long likes, retweets;
	private String tweet;
//	private static long addTweets=0;
//	private long id;

	/**
	 * This constructor creates a tweet with the given message. If the message is
	 * null or its length is greater than 140, throw an IllegalArgumentException You
	 * will have to initialize other instance variables appropriately.
	 * 
	 * @param message the text of the tweet
	 */
	public Tweet(String message) {
		// throw new UnsupportedOperationException();
		if (message == null || message.length() > MAX_LENGTH) {
			throw new IllegalArgumentException();
		}
		this.tweet = message;
		this.likes = 0;
		this.retweets = 0;
	}

	/**
	 * Copy constructor for the Tweet class. Notice that since the parameter is
	 * already a Tweet, it was created by the Tweet() constructor, so it already
	 * contains valid text (i.e. old.text can never be invalid). This constructor is
	 * not tested directly, but it will help you write the deep copy constructor in
	 * TwitterUser.
	 * 
	 * @param old the Tweet object to be copied
	 */
	public Tweet(Tweet old) {
		// throw new UnsupportedOperationException();
		// this(old);
		tweet = old.tweet;
		likes = old.likes;
		retweets = old.retweets;
	}

	/**
	 * Makes appropriate changes in the instance variables to reflect that this
	 * tweet has been retweeted.
	 */
	public void retweet() {
		// throw new UnsupportedOperationException();
		retweets += 1;
	}

	/**
	 * Makes appropriate changes in the instance variables to reflect that this
	 * tweet has been liked.
	 */
	public void like() {
		// throw new UnsupportedOperationException();
		likes += 1;
	}

	/**
	 * 
	 * @return the text of this tweet
	 */
	public String getText() {
		// throw new UnsupportedOperationException();
		return tweet;
	}

	/**
	 * 
	 * @return the number of likes this tweet has received
	 */
	public long getNumLikes() {
		// throw new UnsupportedOperationException();
		return likes;
	}

	/**
	 * 
	 * @return the number of times this tweet has been retweeted
	 */
	public long getNumRetweets() {
		// throw new UnsupportedOperationException();
		return retweets;
	}

	/**
	 * Write the standard equals() method for the Tweet class. Two Tweets are equal
	 * if and only if their messages are equal. You should not check other instance
	 * variables.
	 */
	public boolean equals(Object other) {
		// throw new UnsupportedOperationException();
		if (other == null) {
			return false;
		}
		if (!(this.getClass().equals(other.getClass()))) {
			return false;
		}
		if (this == other) {
			return true;
		}

		Tweet check = (Tweet) other;
		return this.tweet.equals(check.tweet);

	}
}
