package unitTest;

import junit.framework.TestCase;
import model.Post;
import model.User;

import org.junit.Before;
import org.junit.Test;

import factory.Factory;
import factory.PostFactory;
import utils.ParamGenerator;
import utils.PostType;

public class JoinActivityTest extends TestCase{
	private User u1;
	private Post p1;
	private Post p2;
	private Factory f1;
	
	@Before
	public void setUp(){
		u1 = new User("2011052407");
		f1 = new PostFactory();
		
		p1 = (Post) f1.create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		p2 = (Post) f1.create(ParamGenerator.generatePostParam(PostType.DISSCUSSION));
	}
	
	@Test
	public void testJoinActivity(){
		assertEquals(false,p2.isJoinable());
		assertEquals(true,p1.isJoinable());
		
		u1.joinActivity(p1);
		
		assertEquals(1,u1.getJoinedActivities().size());
		assertEquals(u1,p1.getParticipants().iterator().next());
		assertEquals(1,p1.getParticipants().size());
		u1.joinActivity(p2);
		assertEquals(0,p2.getParticipants().size());
	}
}
