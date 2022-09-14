/*
 * Julie Depenyou
 * CMSC131 Yoon
 * UID:115911437
 * Honor Pledge:I pledge on my honor that I have not given or received any 
 * unauthorized assistance on this assignment/examination 
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class PublicTests {
	
	@Test
    public void testTweetConstruction() {
    	Tweet t1 = new Tweet("Guac is extra");
    	assertEquals("Guac is extra", t1.getText());
    	assertEquals(0L, t1.getNumLikes());
    	assertEquals(0L, t1.getNumRetweets());
    }
	
	@Test
    public void testRetweet() {
    	Tweet t1 = new Tweet("Can a man be brave if he is afraid?");
    	Tweet t2 = new Tweet("That is the only time a man can be brave");
    	
    	t1.retweet();
    	for (int i = 0; i < 100; i++)
    		t2.retweet();
    	
    	assertEquals(1L, t1.getNumRetweets());
    	assertEquals(100L, t2.getNumRetweets());
    }
	
	@Test
    public void testLike() {
    	Tweet t1 = new Tweet("If you sleep with more than 2 pillows, you're a psychopath");
    	Tweet t2 = new Tweet("A comfortable psychopath");
    	
    	t2.like();
    	for (int i = 0; i < 100; i++)
    		t1.like();
    	
    	assertEquals(1L, t2.getNumLikes());
    	assertEquals(100L, t1.getNumLikes());
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
    	assertEquals("I like balls", u1.getTweet(0).getText());
    	assertEquals("15/10 I love Buster", u2.getTweet(3).getText());
    }
    
    @Test
    public void testUserRetweetAll() {
    	String[] tweets = {"hello", "world", "I", "love", "CMCS131", "so much", "uwu"};
    	
    	TwitterUser u1 = new TwitterUser("@student1");
    	TwitterUser u2 = new TwitterUser("@student2");
    	
    	for (String t : tweets)
    		u1.tweet(t);
    	
    	
    	assertTrue(u2.retweetAll(u1));
    	//System.out.Println(u1.tweet.size());
    	assertEquals(7, u2.getNumTweets());
    	
    	for (int i = 0; i < 19; i++)
    		assertTrue(u2.retweetAll(u1));
    	
    	assertEquals(140, u2.getNumTweets());
    }
    
    @Test
    public void testUserLikeAll() {
    	String[] tweets = {"Shriraj", "is", "the", "best", "TA", "he is checking", 
    			"if anyone will notice this", "the SECOND TIME he's trying to pull this off"};
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
    	assertEquals(1L, u2.getNumFollowers());
    	assertTrue(u2.follow(u1));
    	assertEquals(1, u2.getNumFollowing());
    	assertEquals(1L, u1.getNumFollowers());
    	

    }
    @Test
    public void testGetFollowers() {
    	TwitterUser u1 = new TwitterUser("@eve");
    	TwitterUser u2 = new TwitterUser("@villanelle1");
    	TwitterUser u3 = new TwitterUser("@villanelle2");
    	TwitterUser u4 = new TwitterUser("@villanelle3");
    	TwitterUser u5 = new TwitterUser("@villanelle4");


    	
    	assertTrue(u1.follow(u2));
    	assertTrue(u1.follow(u3));
    	assertTrue(u1.follow(u4));
    	assertTrue(u1.follow(u5));
    	
    	System.out.println(u1.getNumFollowing());
    	System.out.println(u1.getFollowing());


    	
    	

    }

    
    @Test
    public void testEquals() {
    	TwitterUser u = new TwitterUser("@bojack");
    	assertTrue(u.equals(u));
    }

}
