
/*
 * Julie Depenyou
 * CMSC131 Yoon
 * UID:115911437
 * Honor Pledge:I pledge on my honor that I have not given or received any 
 * unauthorized assistance on this assignment/examination 
 */
import java.util.ArrayList;

/**
 * EXTRA CREDIT CLASS - ONLY SECRET TESTS The Twitter class contains a list of
 * users. It implements a "singleton" design pattern, which means that there can
 * only be one Twitter object ever (since there is only one Twitter in real
 * life). The singleton pattern is a bit advanced, so that has already been
 * implemented for you. See Hints/Tips section of the PDF for more info. You
 * only need to implement some methods.
 * 
 * @author Julie Depenyou
 *
 */
public class Twitter {

	/**
	 * The only instance of Twitter to exist.
	 */
	public static final Twitter ONLY_INSTANCE = new Twitter();

	private ArrayList<TwitterUser> users;

	/**
	 * Private constructor so that no one can create Twitter objects.
	 */
	private Twitter() {
		users = new ArrayList<TwitterUser>();
	}

	/**
	 * Creates a new user with the given username and adds to the list of users.
	 * Return false if the username is null, too long, does not start with '@', or a
	 * user with this username already exists.
	 * 
	 * @param userID the username of the new user
	 * @return true if the user could be created and added, false otherwise
	 */
	public boolean signUp(String userID) {
		// throw new UnsupportedOperationException();
		TwitterUser num1 = new TwitterUser(userID);
		if (userID == null || userID.charAt(0) != '@' || userID.length() > 32 || users.contains(num1)) {
			return false;
		}

		users.add(num1);
		return true;
	}

	/**
	 * Removes the user from the list if such a user exists. Return false if u is
	 * null. Removing a user means that all the users that the removed user followed
	 * will lose a follower. However, the tweets that this user liked or retweeted
	 * will NOT lose their count of likes/retweets.
	 * 
	 * @param userID the username of the user to be deleted
	 * @return true if the user could be deleted, false otherwise
	 */
	public boolean deactivate(TwitterUser u) {
		// throw new UnsupportedOperationException();
		if (u == null) {
			return false;
		}
		users.remove(u);
		for (int i = 0; i < u.getFollowing().size(); i++) {
			if (u.getFollowing().contains(users.get(i))) {
				users.get(i).getNumFollowers();

			}
		}
		return true;
	}

	/**
	 * Resets Twitter to having 0 users.
	 */
	public void reset() {
		users.clear();
	}
}
