
/*
 * Julie Depenyou
 * CMSC131 Yoon
 * UID:115911437
 * Honor Pledge:I pledge on my honor that I have not given or received any 
 * unauthorized assistance on this assignment/examination 
 */
import java.util.ArrayList;

/**
 * This class describes a user of Twitter. A user has a user ID (e.g. @testudo),
 * a list of tweets, a count of the number of followers, and a list of users
 * that this user follows. Unlike P5, there is no upper limit on the number of
 * tweets or users to follow.
 * 
 * You may NOT import any library class other than java.util.ArrayList. You may
 * NOT add any instance variables to keep a count of the number of tweets or
 * users being followed. You may add instance variables for other purposes as
 * long as they are private.
 * 
 * @author <Julie Depenyou>
 *
 */
public class TwitterUser {
	private String username;
	private long numFollowers;
	private ArrayList<Tweet> tweets;
	private ArrayList<TwitterUser> following;

	/**
	 * A constructor that takes the user's ID. If the ID is null, or longer than 32
	 * characters, or it does not start with "@", throw an IllegalArgumentException.
	 * 
	 * All other instance variables should be initialized appropriately.
	 * 
	 * @param userID the ID of the new user
	 */
	public TwitterUser(String userID) {
		// throw new UnsupportedOperationException();
		if (userID == null || userID.charAt(0) != '@' || userID.length() > 32) {
			throw new IllegalArgumentException("This is not a valid username");
		}
		this.username = userID;
		this.numFollowers = 0;
		this.tweets = new ArrayList<Tweet>();
		this.following = new ArrayList<TwitterUser>();

	}

	/**
	 * A pseudo copy-constructor that takes an existing user, a new userID, and a
	 * boolean flag. It should create a new user with the newID given (which has the
	 * same restrictions as above), and the same tweets as the old user that is
	 * being passed in. Information about the number of followers or list of users
	 * the old user is following should NOT be copied. If the flag is false, make a
	 * shallow copy of the old user's tweet ArrayList, otherwise make a deep copy.
	 * Never make a reference copy. All other instance variables should be
	 * initialized appropriately. It may help you to call the earlier constructor
	 * here.
	 * 
	 * @param old
	 * @param newID
	 */
	public TwitterUser(TwitterUser old, String newID, boolean flag) {
		// throw new UnsupportedOperationException();
		// this(newID);
		if (newID == null || newID.charAt(0) != '@' || newID.length() > 32) {
			throw new IllegalArgumentException("This is not a valid username");
		}
		this.username = newID;
		this.numFollowers = 0;
		this.following = new ArrayList<TwitterUser>();
		if (!flag) {
			this.tweets = (ArrayList<Tweet>) old.tweets.clone();
		}

		else if (flag) {
			this.tweets = new ArrayList<Tweet>();

			for (int i = 0; i < old.tweets.size(); i++) {
				Tweet newTweets = old.tweets.get(i);
				this.tweets.add(newTweets);
			}
		}

	}

	/**
	 * Creates a new Tweet object using the given message and adds it to this user's
	 * list of tweets.
	 * 
	 * Note: If the String passed in is null OR if the tweet is longer than
	 * Tweet.MAX_LENGTH, then this method will not add anything to the list and
	 * simply return false. It will return true if it was able to add to the list.
	 * 
	 * @param message the message of the tweet
	 * @return true if the tweet was added to the list, false otherwise
	 */
	public boolean tweet(String message) {
		// throw new UnsupportedOperationException();
		if (message == null || message.length() > Tweet.MAX_LENGTH) {
			return false;
		}
		// tweets.add(message);
		Tweet newTweet = new Tweet(message);
		tweets.add(newTweet);
		return true;
	}

	/**
	 * Adds all the tweets in the given list to this user's list. A 1-line solution
	 * exists (see ArrayList API).
	 * 
	 * @param tweets the list of tweets to be tweeted
	 * @return true if atleast one tweet was added, false otherwise
	 */
	public boolean tweetAll(ArrayList<Tweet> tweets) {
		// throw new UnsupportedOperationException();
		return this.tweets.addAll(tweets);
	}

	/**
	 * This method allows the user to retweet an already existing tweet. You may
	 * have to make appropriate changes in the Tweet object. Do not make a deep copy
	 * of the Tweet object, just point to it directly.
	 * 
	 * @param t the Tweet Object to be retweeted
	 * @return true
	 */
	public boolean retweet(Tweet t) {
		// throw new UnsupportedOperationException();
		// boolean canRetweets=f
		t.retweet();
		tweets.add(t);
		return true;
	}

	/**
	 * This method retweets all the tweets of the TwitterUser object that is passed
	 * in. Do not make a deep copy of the Tweets.
	 * 
	 * @param u the TwitterUser whose tweets are to be retweeted
	 * @return true
	 */
	public boolean retweetAll(TwitterUser u) {
		// throw new UnsupportedOperationException();
		for (int i = 0; i < u.tweets.size(); i++) {
			Tweet newTweets = u.tweets.get(i);
			newTweets.retweet();
			tweets.add(newTweets);

		}
		return true;

	}

	/**
	 * Like all the tweets of the TwitterUser that is passed in. It would be a good
	 * idea to look at the like() method of the Tweet class. There is no true/false
	 * return because a like is always successful.
	 * 
	 * @param u the TwitterUser whose tweets are to be liked
	 */
	public void likeAll(TwitterUser u) {
		// throw new UnsupportedOperationException();
		for (int i = 0; i < u.tweets.size(); i++) {
			Tweet newTweets = u.tweets.get(i);
			newTweets.like();

		}
	}

	/**
	 * 
	 * @return the number of followers this user has
	 */
	public long getNumFollowers() {
		// throw new UnsupportedOperationException();
		return numFollowers;
	}

	/**
	 * 
	 * @return the userID of this user
	 */
	public String getUsername() {
		// throw new UnsupportedOperationException();
		return username;
	}

	/**
	 * 
	 * @return the number of tweets this user has tweeted or retweeted
	 */
	public int getNumTweets() {
		// throw new UnsupportedOperationException();
		return this.tweets.size();
	}

	/**
	 * 
	 * @return the number of users this user follows
	 */
	public int getNumFollowing() {
		// throw new UnsupportedOperationException();
		return this.following.size();
	}

	/**
	 * Given an index, return a reference to the Tweet object at that index in the
	 * array. Return null if the index is negative, or greater than or equal to the
	 * current number of tweets.
	 * 
	 * NOTE: It is generally a terrible idea to give away reference copies, but we
	 * use this for testing.(See Lecture 36 privacy leak slides)
	 * 
	 * @param index the index of the desired tweet
	 * @return a reference copy of the tweet at index if index is valid, null
	 *         otherwise
	 */
	public Tweet getTweet(int index) {
		// throw new UnsupportedOperationException();
		if (index < 0 || index >= tweets.size()) {
			return null;
		}
		return tweets.get(index);
	}

	/**
	 * This method helps the current user follow the user that is passed in. If the
	 * TwitterUser passed in is not null and is not already being followed by the
	 * current user, then add the given user to this user's list. You may have to
	 * make appropriate changes to this user's and/or the other user's instance
	 * variables. This method will probably rely on the equals method you write.
	 * 
	 * @param u the user to be followed
	 * @return true if you were able to add u to this user's list, false otherwise
	 */
	public boolean follow(TwitterUser u) {
		// throw new UnsupportedOperationException();
		// this.followers.add(u);
		for (int i = 0; i < this.following.size(); i++) {
			if (this.following.get(i) == null || this.following.get(i) == u) {
				return false;
			}
//			u.followers.add(this);
//			u.numFollowers++;
		}
		this.following.add(u);
		u.numFollowers++;
		return true;

	}

	/**
	 * This method helps the current user unfollow the given user. If the given user
	 * is being followed by the current user, remove them and return true.
	 * Otherwise, return false. You may have to modify instance variables as
	 * necessary.
	 * 
	 * @param u the user to be unfollowed
	 * @return true if you were able to unfollow, false otherwise
	 */
	public boolean unfollow(TwitterUser u) {
		// throw new UnsupportedOperationException();
		for (int i = 0; i < this.following.size(); i++) {
			if (this.following.get(i) != null && this.following.get(i) == u) {
				this.following.remove(u);
				u.numFollowers--;
				return true;
			}

		}

		return false;

	}

	/**
	 * Checks if the user has tweeted or retweeted any tweet with the given message.
	 * Return false if the message is null or too long.
	 * 
	 * @param message the message of the tweet to be searched
	 * @return true if the user has tweeted a tweet with the given message, false
	 *         otherwise
	 */
	public boolean hasTweeted(String message) {
		// throw new UnsupportedOperationException();

		if (message == null || message.length() > Tweet.MAX_LENGTH) {
			return false;
		}
		Tweet newTweet = new Tweet(message);
		if (tweets.contains(newTweet)) {
			return true;
		}
		return false;

	}

	/**
	 * Removes ALL the tweets and retweets with the same message as the given
	 * message. Return false if the message is null or too long.
	 * 
	 * Hint: See removeAll() in the ArrayList API.
	 * 
	 * @param message the message of the tweet(s) to be removed
	 * @return true if atleast one tweet/retweet was removed, false otherwise
	 */
	public boolean delete(String message) {
		// throw new UnsupportedOperationException();

		if (message == null || message.length() > Tweet.MAX_LENGTH) {
			return false;
		}
		Tweet newTweet = new Tweet(message);
		if (tweets.contains(newTweet)) {
			tweets.remove(newTweet);
			return true;
		}
		return false;
	}

	/**
	 * Counts the number of tweets or retweets with the same message as the given
	 * message. Return -1 if the message is null.
	 * 
	 * @param message the message of the tweets/retweets to be counted
	 * @return the count of the number of occurrences of this message
	 */
	public int count(String message) {
		// throw new UnsupportedOperationException();
		int count = 0;
		if (message == null) {
			count = -1;
		}
		for (int i = 0; i < tweets.size(); i++) {
			Tweet newTweet = this.tweets.get(i);
			if (newTweet.getText() == message) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Checks if the Object passed in is logically equal to the current TwitterUser
	 * object. For the purpose of this project, two TwitterUsers are equal if they
	 * both have the same userID. You do not need to check for any other fields.
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

		TwitterUser check = (TwitterUser) other;
		return this.username.equals(check.username);
	}

	/**
	 * Returns a shallow (NOT reference or deep) copy of the list of users that this
	 * user is following. A 1-line solution exists. Hint: One of the ArrayList
	 * constructors in its API.
	 * 
	 * @return a shallow copy of the users the current user is following
	 */
	public ArrayList<TwitterUser> getFollowing() {

		ArrayList<TwitterUser> aClone = (ArrayList<TwitterUser>) following.clone();
		return aClone;

	}
}
