package unitTest;

import junit.framework.TestCase;
import model.Post;
import model.User;

import org.junit.Before;
import org.junit.Test;

import service.factory.Factory;
import service.factory.PostFactory;
import utils.ParamGenerator;
import utils.PostType;

public class ClickLikeTest extends TestCase{
	private User u1;
	private User u2;
	private Post p1;
	private Factory f1;
	
	@Before
	public void setUp(){
		u1 = new User("2011052407");
		u2 = new User("2011052408");
		f1 = new PostFactory();
		p1 = (Post) f1.create(ParamGenerator.generatePostParam(PostType.QUESTION));
	}
	
	@Test
	public void testClickLike(){
		assertEquals(0,p1.getLikeNum());
		
		u1.likePost(p1);
		
		assertEquals(1,p1.getLikeNum());
		
		u2.likePost(p1);
		
		assertEquals(2,p1.getLikeNum());
		
		u2.cancelLike(p1);
		
		assertEquals(1,p1.getLikeNum());
	}
}
