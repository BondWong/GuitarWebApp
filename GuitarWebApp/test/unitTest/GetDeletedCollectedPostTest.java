package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import service.factory.Factory;
import service.factory.PostFactory;
import utils.ParamGenerator;
import utils.PostType;
import model.Post;
import model.User;

public class GetDeletedCollectedPostTest {
	private User u1;
	private User u2;
	private Post p1;
	private Post p2;
	
	@Before
	public void setUp(){
		u1 = new User("2011052407");
		u2 = new User("2011052406");
		Factory f = new PostFactory();
		p1 = (Post) f.create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		p2 = (Post) f.create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
	}
	
	@Test
	public void testGetDeletedCollectedPost(){
		assertEquals(true, p1.isActive());
		assertEquals(true, p2.isActive());
		
		u2.addPost(p1);
		u2.addPost(p2);
		
		u1.collectPost(p1);
		u1.collectPost(p2);
		
		assertEquals(2,u1.getCollectedPosts().size());
		
		u2.deletePost(p1);
		
		assertEquals(2,u1.getCollectedPosts().size());
		assertEquals(false,u1.getCollectedPosts().iterator().next().isActive());
	}
}
