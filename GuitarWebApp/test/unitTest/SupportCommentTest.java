package unitTest;

import model.Comment;
import model.User;

import org.junit.Before;
import org.junit.Test;

import factory.CommentFactory;
import factory.Factory;
import utils.CommentType;
import utils.ParamGenerator;
import junit.framework.TestCase;

public class SupportCommentTest extends TestCase{
	private User u1;
	private User u2;
	private Comment c1;
	private Comment c2;
	private Factory f;
	@Before
	public void setUp(){
		u1 = new User("2011052407");
		u2 = new User("2011052406");
		f = new CommentFactory();
		c1 = (Comment) f.create(ParamGenerator.generateCommentParam(CommentType.ANSWER));
		c2 = (Comment) f.create(ParamGenerator.generateCommentParam(CommentType.COMMENT));
	}
	
	@Test
	public void testCommentSupport(){
		assertEquals(0,c1.getSupportNum());
		assertEquals(0,c2.getSupportNum());
		
		u1.supportComment(c1);
		u1.supportComment(c2);
		
		assertEquals(1,c1.getSupportNum());
		assertEquals(0,c2.getSupportNum());
		u2.supportComment(c1);
		
		assertEquals(2,c1.getSupportNum());
		assertEquals(2,c1.getSupportors().size());
		assertEquals(0,c2.getSupportors().size());
		
		u2.cancelSupport(c1);
		u2.cancelSupport(c2);
		assertEquals(1,c1.getSupportNum());
		assertEquals(0,c2.getSupportNum());
	}
}
