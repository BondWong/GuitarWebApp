package unitTest;

import junit.framework.TestCase;
import model.Post;
import model.User;

import org.junit.Before;
import org.junit.Test;

import services.Factory;
import services.PostFactory;
import utils.ParamGenerator;
import utils.PostType;

public class CollectPostTest extends TestCase{
	private User u1;
	private User u2;
	private Post p1;
	private Post p2;
	private Factory f1;
	
	@Before
	public void setUp(){
		f1 = new PostFactory();
		u1 = new User("2011052407");
		u2 = new User("2011052406");
		
		p1 = (Post) f1.create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		p2 = (Post) f1.create(ParamGenerator.generatePostParam(PostType.QUESTION));
	}
	
	@Test
	public void testCollectPost(){
		assertEquals(0,u1.getCollectedPosts().size());
		assertEquals(0,u2.getCollectedPosts().size());
		
		u1.collectPost(p1);
		u1.collectPost(p2);
		u2.collectPost(p2);
		
		assertEquals(2,u1.getCollectedPosts().size());
		assertEquals(1,u2.getCollectedPosts().size());
		
		u1.cancelCollect(p1);
		assertEquals(1,u1.getCollectedPosts().size());
	}
}
