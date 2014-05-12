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

public class DeleteUserTest {
	private User u1;
	private Post p1;
	
	@Before
	public void setUp(){
		u1 = new User("2011052407");
		
		Factory f = new PostFactory();
		
		p1 = (Post) f.create(ParamGenerator.generatePostParam(PostType.QUESTION));
	}
	
	@Test
	public void testDeleteUser(){
		u1.addPost(p1);
		
		assertEquals(true, u1.isActive());
		assertEquals(true, p1.isActive());
		
		u1.forbid();
		
		assertEquals(false, u1.isActive());
		assertEquals(false, p1.isActive());
	}
}
