package test;

import org.junit.Before;
import org.junit.Test;

import model.User;
import junit.framework.TestCase;

public class FollowUserTest extends TestCase{
	private User u1;
	private User u2;
	private User u3;
	private User u4;
	
	@Before
	public void setUp(){
		u1 = new User("2011052407");
		u2 = new User("2011052406");
		u3 = new User("2011052408");
		u4 = new User("2011052409");
	}
	
	@Test
	public void testFollowUser(){
		assertEquals(0,u1.getFollowees().size());
		assertEquals(0,u2.getFollowees().size());
		assertEquals(0,u3.getFollowees().size());
		assertEquals(0,u4.getFollowees().size());
		
		assertEquals(0,u1.getFollowers().size());
		assertEquals(0,u2.getFollowers().size());
		assertEquals(0,u3.getFollowers().size());
		assertEquals(0,u4.getFollowers().size());
		
		u1.follow(u2);
		u1.follow(u3);
		u2.follow(u4);
		u4.follow(u1);
		
		assertEquals(2,u1.getFollowees().size());
		assertEquals(1,u2.getFollowees().size());
		assertEquals(0,u3.getFollowees().size());
		assertEquals(1,u4.getFollowees().size());
		
		assertEquals(1,u1.getFollowers().size());
		assertEquals(1,u2.getFollowers().size());
		assertEquals(1,u3.getFollowers().size());
		assertEquals(1,u4.getFollowers().size());
		
		u2.follow(u1);
		
		u1.cancelFollow(u2);
		u1.cancelFollow(u3);
		
		assertEquals(0,u1.getFollowees().size());
		assertEquals(2,u2.getFollowees().size());
		
		assertEquals(2,u1.getFollowers().size());
		assertEquals(0,u2.getFollowers().size());
		assertEquals(0,u3.getFollowers().size());
	}

}
