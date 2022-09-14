import static org.junit.Assert.*;

import org.junit.Test;

public class PublicTests {

	@Test
	public void testTweetConstruction() {
		Tweet t1 = new Tweet("Guac is extra");
		//assertEquals("Guac is extra", t1.getText());
		assertEquals(0, t1.getNumLikes());
		assertEquals(0, t1.getNumRetweets());
	}

	@Test
	public void testRetweet() {
		Tweet t1 = new Tweet("Can a man be brave if he is afraid?");
		Tweet t2 = new Tweet("That is the only time a man can be brave");

		t1.retweet();
		for (int i = 0; i < 100; i++)
			t2.retweet();

		assertEquals(1, t1.getNumRetweets());
		assertEquals(100, t2.getNumRetweets());
	}

	@Test
	public void testLike() {
		Tweet t1 = new Tweet("If you sleep with more than 2 pillows, you're a psychopath");
		Tweet t2 = new Tweet("A comfortable psychopath");

		t2.like();
		for (int i = 0; i < 100; i++)
			t1.like();

		assertEquals(1, t2.getNumLikes());
		assertEquals(100, t1.getNumLikes());
	}

	@SuppressWarnings("unused")
	@Test
	public void testNumTweetsBasic() {
		Tweet t1 = new Tweet("Mornings are for coffee and contemplation");
		long x = Tweet.getNumTweets();
		Tweet t2 = new Tweet("Friends don't lie");
		long y = Tweet.getNumTweets();

		assertTrue(Tweet.getNumTweets() >= 2);
		assertTrue(y == x + 1);
	}

	@Test
	public void testTweetIDBasic() {
		Tweet t1 = new Tweet("Again, the sea of flags");
		Tweet t2 = new Tweet("Pretenders to his crown");

		assertTrue(t1.getID() >= 0);
		assertTrue(t2.getID() >= 1);
		assertTrue(t2.getID() == t1.getID() + 1);
	} 

	@Test
	public void testUserConstruction1() {
		TwitterUser u1 = new TwitterUser("@doge");
		assertEquals("@doge", u1.getUsername());
		assertEquals(0, u1.getNumFollowers());
		assertEquals(0, u1.getNumFollowing());
		assertEquals(0, u1.getNumTweets());
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testUserConstruction2() {
		TwitterUser u1 = new TwitterUser(null);
	}

	@Test
	public void testTweeting() {
		TwitterUser u1 = new TwitterUser("@ThoughtsOfDog");
		TwitterUser u2 = new TwitterUser("@WeRateDogs");

		assertTrue(u1.tweet("I like balls"));
		assertTrue(u1.tweet("Food is good"));
		assertTrue(u2.tweet("12/10 h*ckin good boy"));
		assertTrue(u2.tweet("13/10 Lucy is the best dog ever"));
		assertTrue(u2.tweet("14/10 Chloe is a good girl"));
		assertTrue(u2.tweet("15/10 I love Buster"));
		assertFalse(u1.tweet(null));

		assertEquals(2, u1.getNumTweets());
		assertEquals(4, u2.getNumTweets());
		assertEquals("I like balls", u1.getTweet(0));
		assertEquals("15/10 I love Buster", u2.getTweet(3));
	}

	@Test
	public void testUserConstruction4() {
		TwitterUser u1 = new TwitterUser("@umdcs");
		u1.tweet("Boy am I excited for my new IRB building");

		TwitterUser u2 = new TwitterUser(u1, "@cmns");
		assertEquals("@cmns", u2.getUsername());
		assertEquals(0, u2.getNumFollowers());
		assertEquals(0, u2.getNumFollowing());
		assertEquals(1, u2.getNumTweets());
		assertEquals("Boy am I excited for my new IRB building", u2.getTweet(0));
	}

	@Test
	public void testUserRetweetAll() {
		String[] tweets = {"hello", "world", "I", "love", "CMCS131", "so much", "uwu"};

		TwitterUser u1 = new TwitterUser("@student1");
		TwitterUser u2 = new TwitterUser("@student2");

		for (String t : tweets)
			u1.tweet(t);

		assertTrue(u2.retweetAll(u1));
		assertEquals(7, u2.getNumTweets());

		for (int i = 0; i < 19; i++)
			assertTrue(u2.retweetAll(u1));

		assertEquals(140, u2.getNumTweets());
		assertFalse(u2.retweetAll(u1));
	}

	@Test
	public void testUserLikeAll() {
		String[] tweets = {"Shriraj", "is", "the", "best", "TA", "he is checking", 
				"if anyone will notice this", "before the project is released"};
		Tweet[] ts = new Tweet[tweets.length];

		TwitterUser u1 = new TwitterUser("@TA1");
		TwitterUser u2 = new TwitterUser("@TA2");

		for (int i = 0; i < tweets.length; i++)
			ts[i] = new Tweet(tweets[i]);

		for (Tweet t : ts)
			u1.retweet(t);

		u2.likeAll(u1);

		for (Tweet t : ts)
			assertEquals(1, t.getNumLikes());    	

		for (int i = 0; i < 10; i++) 
			u2.likeAll(u1);

		for (Tweet t : ts)
			assertEquals(11, t.getNumLikes());
	}

	@Test
	public void testFollow() {
		TwitterUser u1 = new TwitterUser("@eve");
		TwitterUser u2 = new TwitterUser("@villanelle");

		assertTrue(u1.follow(u2));
		assertEquals(1, u1.getNumFollowing());
		assertEquals(1, u2.getNumFollowers());

		assertTrue(u2.follow(u1));
		assertEquals(1, u2.getNumFollowing());
		assertEquals(1, u1.getNumFollowers());
	}

	@Test
	public void testEquals() {
		TwitterUser u1 = new TwitterUser("@qtips");
		TwitterUser u2 = new TwitterUser("@qtips");
		TwitterUser u3 = new TwitterUser("@mug");

		assertTrue(u1.equals(u2));
		assertTrue(u2.equals(u1));
		assertTrue(u1.equals(u1));
		assertTrue(u2.equals(u2));
		assertTrue(u3.equals(u3));
		assertFalse(u1.equals(u3));
		assertFalse(u3.equals(u2));

		u1.follow(u2);
		u3.follow(u2);
		assertTrue(u1.equals(u2));
		assertTrue(u2.equals(u1));

		u2.follow(u1);
		u1.tweet("wiggle wiggle");
		assertTrue(u1.equals(u2));
		assertTrue(u2.equals(u1));
	}

}
