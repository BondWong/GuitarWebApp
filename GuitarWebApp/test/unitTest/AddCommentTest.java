package unitTest;

import junit.framework.TestCase;
import model.Comment;
import model.Post;
import model.User;

import org.junit.Before;
import org.junit.Test;

import service.factory.CommentFactory;
import service.factory.Factory;
import service.factory.PostFactory;
import utils.CommentType;
import utils.ParamGenerator;
import utils.PostType;

public class AddCommentTest extends TestCase{
	private User u1;
	private User u2;
	private Post p1;
	private Post p2;
	private Comment c1;
	private Comment c2;
	private Factory f1;
	private Factory f2;
	
	@Before
	public void setUp(){
		f1 = new PostFactory();
		f2 = new CommentFactory();
		
		u1 = new User("2011052407");
		u2 = new User("2011052406");
		
		p1 = (Post) f1.create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		p2 = (Post) f1.create(ParamGenerator.generatePostParam(PostType.DISCUSSION));
		
		c1 = (Comment) f2.create(ParamGenerator.generateCommentParam(CommentType.COMMENT));
		c2 = (Comment) f2.create(ParamGenerator.generateCommentParam(CommentType.COMMENT));
		
		u1.addPost(p1);
		u2.addPost(p2);
	}
	
	@Test
	public void testAddComment(){
		u1.addComment(p1,c1);
		u1.addComment(p2,c2);
		
		assertEquals(1,p1.getComments().size());
		assertEquals(1,p2.getComments().size());
		assertEquals(p1.getOwner(),c1.getOwner());
		assertEquals(u1,c2.getOwner());
	}
	
	@Test
	public void testDeleteComment(){
		u1.deleteComment(p1,c1);
		
		assertEquals(0,p1.getComments().size());
		assertEquals(null,c1.getOwner());
	}
}
