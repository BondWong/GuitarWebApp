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

public class AddPostTest extends TestCase{
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
		
		p1 = (Post) f1.create(ParamGenerator.generatePostParam(PostType.QUESTION));
		p2 = (Post) f1.create(ParamGenerator.generatePostParam(PostType.QUESTION));
	}
	
	@Test
	public void testAddPost(){
		u1.addPost(p1);
		u2.addPost(p2);
		
		assertEquals(true,p1.isActive());
		assertEquals(u1,p1.getOwner());
		assertEquals("2011052407",p1.getOwner().getID());
		assertEquals(p1,u1.getPosts().iterator().next());
		assertEquals(PostType.QUESTION, p1.getType());
		assertEquals(false, p1.isJoinable());
	}
	
	@Test
	public void testDeletePost(){
		assertEquals(true,p1.isActive());
		u1.deletePost(p1);
		
		assertEquals(null,p1.getOwner());
		assertEquals(0,u1.getPosts().size());
		assertEquals(false,p1.isActive());
	}
}
