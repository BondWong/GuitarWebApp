package intregatedTest;

import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import services.CommentRep;
import utils.CommentType;

public class CommentServiceMockTest extends JerseyTest{
	private Entity<String> nullEntity;
	
	@Before
	public void setUp(){
		nullEntity = Entity.text("");
	}
	
	@Override
	public Application configure(){
		return new ResourceConfig(CommentServiceMock.class).register(JacksonFeature.class);
	}
	
	@Ignore
	@Test
	public void testCommentServiceAddComment(){
		CommentRep commentRep = new CommentRep();
		commentRep.setContent("gehe");
		
		Entity<CommentRep> entity = Entity.json(commentRep);
		commentRep.setType(CommentType.COMMENT);
		Response response = target("/comment/add/2011052407/1")
				.register(JacksonFeature.class).request(MediaType.APPLICATION_JSON)
				.post(entity);
		
		assertEquals(200, response.getStatus());
	}
	
	@Ignore
	@Test
	public void testCommentServiceDeleteComment(){
		Response response = target("/comment/delete/2011052407/1/1")
				.request()
				.delete();
		
		assertEquals(200, response.getStatus());
	}
	
	@Ignore
	@Test
	public void testCommentServiceSupportAnswer(){
		Response response = target("/comment/support/2011052407/1")
				.request().put(nullEntity);
		
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testCommentServiceCancelSupport(){
		Response response = target("/comment/cancelSupport/2011052407/1")
				.request().put(nullEntity);
		
		assertEquals(200, response.getStatus());
	}
}
