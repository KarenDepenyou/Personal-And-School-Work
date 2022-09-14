/*
 * Julie Depenyou
 * CMSC131 Yoon
 * UID:115911437
 * Honor Pledge:I pledge on my honor that I have not given or received any 
 * unauthorized assistance on this assignment/examination 
 */
/**
 * This is a class which contains some static methods to find some
 * statistics about users and tweets. Importing any library class 
 * (e.g. java.util.Scanner or java.util.Arrays) is NOT allowed.
 * 
 * @author <Julie Depenyou>
 *
 */
public class TwitterStats {
	
	/**
	 * Given an array of tweets, find the ID of the tweet that
	 * has the most "likes". Return -1 if the array is of length 0.
	 * Do not worry about the case where the array is null or there are
	 * multiple tweets with the same number of likes.
	 * 
	 * 
	 * @param tweets the array of tweets
	 * @return ID of the tweet with most likes if array is non-empty, 0 otherwise
	 */
	public static long mostLikedTweet(Tweet[] tweets) {
		//throw new UnsupportedOperationException("Implement this");	
		long mostLiked=0;
		long ID=0;
		if(tweets.length==0) {
			return -1;
		}
		for(int i=0; i<tweets.length; i++) {
			if(tweets[i].getNumLikes()>=mostLiked) {
				mostLiked=tweets[i].getNumLikes();
			}ID=tweets[i].getID();
		}return ID;


	}
	
	
	/**
	 * Given an array of Twitter users, find the user ID of the one
	 * with the most followers. Return null if array is empty.
	 * Do not worry about the case where the array is null or there are
	 * multiple users with the same number of followers.
	 * 
	 * @param users the array of TwitterUsers
	 * @return userID of the user with most followers if array is non-empty, 0 otherwise
	 */
	public static String mostFollowers(TwitterUser[] users) {
		long mostFollowers=0;
		String ID=null;
		if(users.length==0) {
			return null;
		}
		for(int i=0; i<users.length; i++) {
			if(users[i].getNumFollowers()>=mostFollowers) {
				mostFollowers=users[i].getNumFollowers();
			}
			ID=users[i].getUsername();
		}return ID;
	}

}