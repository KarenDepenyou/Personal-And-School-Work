/*
 * Julie Depenyou
 * CMSC131 Yoon
 * UID:115911437
 * Honor Pledge:I pledge on my honor that I have not given or received any 
 * unauthorized assistance on this assignment/examination 
 */
 
/**
 * This class describes a user of Twitter. A user has a user ID (e.g. @testudo),
 * a list of tweets, a count of the number of followers, and a list of users
 * that this user follows. The user may contain other static or instance
 * variables. In fact, you will need some to keep track of information such as
 * the number of tweets this user has had, or the maximum number of followers
 * allowed.
 * 
 * You may NOT import any library class.
 * 
 * @author <Julie Depenyou>
 *
 */
public class TwitterUser {

	public static final int MAX_TWEETS = 140, MAX_FOLLOWING = 140;
	private String username;
	private long numFollowers;
	private Tweet[] tweets;
	private TwitterUser[] following;
	private int numTweets;
	private int numFollowing;

	// ADD YOUR INSTANCE/STATIC VARIABLES HERE

	/**
	 * A constructor that takes the user's ID. If the ID is null, or longer than 32
	 * characters, or it does not start with "@", throw an IllegalArgumentException.
	 * The ID may contain any ASCII characters, so you don't have to worry about
	 * checking for special characters. Also do not worry about checking whether any
	 * other user has the same ID.
	 * 
	 * All other instance variables should be initialized appropriately.
	 * 
	 * @param userID the ID of the new user
	 */
	public TwitterUser(String userID) {

		if (userID == null || userID.charAt(0) != '@' || userID.length() > 32) {
			throw new IllegalArgumentException("This is not a valid username");
		}

		else {
			this.username = userID;
			this.numFollowers = 0;
			this.numFollowing = 0;
			this.tweets = new Tweet[MAX_TWEETS];
			this.following = new TwitterUser[MAX_FOLLOWING];
		}


	}

	/**
	 * A constructor that takes an existing user as well as a new userID. It should
	 * create a new user with the newID given (which has the same requirements as
	 * above), and the same tweets as the old user that is being passed in.
	 * Information about the number of followers or list of users the old user is
	 * following should not be copied. All other instance variables should be
	 * initialized appropriately. It may be a good idea to call the other
	 * constructor here.
	 * 
	 * @param old   the existing TwitterUser whose tweets we are copying
	 * @param newID the ID of the new user to be created
	 */
	public TwitterUser(TwitterUser old, String newID) {

		if (newID == null||newID.charAt(0) != '@' || newID.length() > 32) {
			throw new IllegalArgumentException("This is not a valid username");
		} else {
	
			this.username = newID;
			this.numFollowers = 0;
			this.tweets = old.tweets;
			this.following = new TwitterUser[MAX_FOLLOWING];
			this.numFollowing = 0;
			this.numTweets=old.numTweets;

		 }

	}

	/**
	 * Creates a new Tweet object using the given message and adds it to this user's
	 * array of tweets. Our version of Twitter has no requirements on the length of
	 * the message.
	 * 
	 * Note: If the String passed in is null OR if the user has already reached the
	 * maximum number of tweets, then this method will not add anything to the array
	 * and simply return false. It will return true if it was able to add to the
	 * array.
	 * 
	 * You may have to update other instance variables in this process.
	 * 
	 * @param message the message of the tweet
	 * @return true if the tweet was added to the array, false otherwise
	 */
	public boolean tweet(String message) {
		if (message == null || numTweets >= MAX_TWEETS) {
			return false;
		}
		tweets[numTweets] = new Tweet(message);
		numTweets++;
		return true;

	}

	/**
	 * This method allows the user to retweet an already existing tweet. The user
	 * can only do this if they have not reached their maximum allowance of tweets.
	 * You may have to make appropriate changes in the Tweet Object.
	 * 
	 * @param t the Tweet Object
	 * @return true if the tweet was added to the array, false otherwise
	 */
	public boolean retweet(Tweet t) {
		boolean canRetweet = false;
		if (numTweets < MAX_TWEETS) {

			t.retweet();
			tweets[numTweets] = t;
			numTweets++;
			canRetweet = true;
		}
		return canRetweet;
	}

	/**
	 * This method retweets all the tweets of the TwitterUser Object that is passed
	 * in. It will return true if ALL the tweets could be retweeted, and false if at
	 * least one tweet could not be retweeted. In other words, if the current user
	 * reaches the maximum allowance of tweets midway through the array of the other
	 * user's tweets, then the tweets that are allowed to be added will be added,
	 * but the method will return false because not all could be retweeted.
	 * 
	 * @param u the TwitterUser whose tweets are to be retweeted
	 * @return true if all tweets could be retweeted, false otherwise
	 */
	public boolean retweetAll(TwitterUser u) {

		if (numTweets < MAX_TWEETS) {
			for (int i = 0; i < u.numTweets; i++) {
				if(retweet(u.tweets[i])==false) {
					return false;
				}
			

			}

		}else {
			return false;
		}

		return true;
	}

	/**
	 * Like all the tweets of the TwitterUser that is passed in. It would be a good
	 * idea to look at the like() method of the Tweet class.
	 * 
	 * @param u the TwitterUser whose tweets are to be liked
	 */
	public void likeAll(TwitterUser u) {
		for (int i = 0; i < u.getNumTweets(); i++) {
			u.tweets[i].like();
		}
	}

	/**
	 * 
	 * @return the number of followers this user has
	 */
	public long getNumFollowers() {
		return numFollowers;
	}

	/**
	 * 
	 * @return the userID of this user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @return the number of tweets this user has tweeted or retweeted
	 */
	public int getNumTweets() {
		return numTweets;
	}

	/**
	 * 
	 * @return the number of users this user follows
	 */
	public int getNumFollowing() {

		return numFollowing;
	}

	/**
	 * Given an index, return the text of the Tweet at that index in the array.
	 * Return null if the index is negative, or greater than or equal to the current
	 * number of tweets.
	 * 
	 * @param index the index of the desired tweet
	 * @return the text of the tweet at index if index is valid, null otherwise
	 */
	public String getTweet(int index) {

		if (index < 0 || index >= numTweets) {
			return null;
		} else {


			return tweets[index].getText();
		}
	}

	/**
	 * This method helps the current user follow the user that is passed in. If the
	 * current user has not reached the maximum allowance for the number of accounts
	 * to follow, AND the TwitterUser passed in is not already being followed by the
	 * current user, then add the given user to this user's list. You may have to
	 * make appropriate changes to this user's and/or the other user's instance
	 * variables. This method will probably rely on the equals method you write.
	 * 
	 * @param u the user to be followed
	 * @return true if you were able to add u to this user's array, false otherwise
	 */
	public boolean follow(TwitterUser u) {
		if (this.numFollowing >= MAX_FOLLOWING) {
			return false;
		}
		for(int i=0; i<following.length;i++) {
			if(this.following[i]==null||this.following[i]==u) {
				return false;
			}
		}
		this.following[numFollowing] = u;
		this.numFollowing++;
		u.numFollowers++;
		return true;

	}

	/**
	 * Checks if the Object passed in is logically equal to the current TwitterUser
	 * object. For the purpose of this project, two TwitterUsers are equal if they
	 * both have the same userID. You do not need to check for any other fields.
	 */
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (!(this.getClass().equals(other.getClass()))) {
			return false;
		}
		if (this == other) {
			return true;
		}


		TwitterUser check = (TwitterUser) other;
		return this.username.equals(check.username);

	}

}
