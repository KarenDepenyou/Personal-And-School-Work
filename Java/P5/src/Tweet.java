/*
 * Julie Depenyou
 * CMSC131 Yoon
 * UID:115911437
 * Honor Pledge:I pledge on my honor that I have not given or received any 
 * unauthorized assistance on this assignment/examination 
 */

/**
 * This class describes a tweet. A tweet has a message in it, a unique ID,
 * a count of the number of likes, and a count of the number of times it
 * has been retweeted. In addition, the Tweet class will have a static 
 * variable to count the total number of tweets to ever be created 
 * (retweets don't count as a new tweet).
 * 
 * You may NOT import any library class.
 * 
 * @author <Julie Depenyou>
 *
 */
public class Tweet {

	// ADD YOUR INSTANCE/STATIC VARIABLES HERE
	private long likes, retweets;
	private String tweet;
	private static long addTweets=0;
	private long id;
	//private final int MAX=140;

	/**
	 * This constructor creates a tweet with the given message.
	 * Assume that the message is not null because only the tweet() method
	 * from the TwitterUser class will call this constructor. There are
	 * no length requirements on Tweets.
	 * 
	 * The very first tweet to ever be created will have an ID of 0, the 
	 * next one will have 1, and so on and so forth. It may help you to use
	 * the static count variable to set the ID. 
	 *
	 * You will have to initialize other instance variables appropriately.
	 * @param message the text of the tweet
	 */
	public Tweet(String message) {
		//throw new UnsupportedOperationException("Implement this");
		this.tweet=message;
		this.id=addTweets;
		//addTweets+=1;
		this.likes=0;
		this.retweets=0;
		addTweets+=1;
		
	}

	/**
	 * Makes appropriate changes in the instance variables to reflect that
	 * this tweet has been retweeted.
	 */
	public void retweet() {
		//throw new UnsupportedOperationException("Implement this");
		retweets+=1;
	}

	/**
	 * Makes appropriate changes in the instance variables to reflect that
	 * this tweet has been liked. 
	 */
	public void like() {
		//throw new UnsupportedOperationException("Implement this");
		likes++;
	}


	/**
	 * 
	 * @return the ID of this tweet
	 */
	public long getID() {
		//throw new UnsupportedOperationException("Implement this");
		return id;
	}

	/**
	 * 
	 * @return the text of this tweet
	 */
	public String getText() {
		//throw new UnsupportedOperationException("Implement this");
		return tweet;
	}

	/**
	 * 
	 * @return the number of likes this tweet has received
	 */
	public long getNumLikes() {
		//throw new UnsupportedOperationException("Implement this");
		return likes;
	}

	/**
	 * 
	 * @return the number of times this tweet has been retweeted
	 */
	public long getNumRetweets() {
		//throw new UnsupportedOperationException("Implement this");
		return retweets;
	}

	/**
	 * 
	 * @return the total number of tweets to ever be created
	 */
	public static long getNumTweets() {
		//throw new UnsupportedOperationException("Implement this");
		return addTweets;
	}
}
