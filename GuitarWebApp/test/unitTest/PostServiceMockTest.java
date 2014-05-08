package unitTest;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.LinkedHashSet;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import services.PostRep;
import utils.PostType;

public class PostServiceMockTest extends JerseyTest{
	private static Entity<String> nullEntity;
	
	@BeforeClass
	public static void classSetUp(){
		nullEntity = Entity.text("");
	}
	
	@Override
	protected Application configure(){
		return new ResourceConfig(PostServiceMock.class).register(JacksonFeature.class);
	}
	
	@Ignore
	@Test
	public void testPostServiceAddPost(){
		PostRep postRep = new PostRep();
		postRep.setContent("I love you");
		postRep.setMediaLocation(new LinkedHashSet<String>());
		postRep.setPublishDate(new Date());
		postRep.setTopic("hehe");
		postRep.setType(PostType.DISSCUSSION);
		
		Entity<PostRep> entity = Entity.entity(postRep, MediaType.APPLICATION_JSON);
		Response response = target("/post/add/2011052407")
				.register(JacksonFeature.class)
				.request(MediaType.APPLICATION_JSON)
				.post(entity);
		assertEquals(200, response.getStatus());
		
	}
	
	@Ignore
	@Test
	public void testPostServiceDeletePost(){
		Response response =target("/post/delete/2011052407/1")
				.request(MediaType.TEXT_PLAIN)
				.put(nullEntity);
		
		assertEquals(200, response.getStatus());
	}
	
	@Ignore
	@Test
	public void testPostServiceFetchByUserID(){
		Response response = target("/post/fetchByUserID/2011052407")
				.request()
				.get();
		
		assertEquals(204, response.getStatus());
	}
	
	@Ignore
	@Test
	public void testPostServiceFetchByType(){
		Response response = target("/post/fetchByType/DISSCUSSION")
				.request()
				.get();
		
		assertEquals(204, response.getStatus());
	}
	
	@Ignore
	@Test
	public void testPostServiceFetchByFollowee(){
		Response response = target("/post/fetchByFollowee/2011052407")
				.request()
				.get();
		
		assertEquals(204, response.getStatus());
	}
	
	@Ignore
	@Test
	public void testPostServiceGetByID(){
		Response response = target("/post/getByID/1")
				.request()
				.get();
		
		assertEquals(204, response.getStatus());
	}
	
	@Ignore
	@Test
	public void testPostServiceLikePost(){
		Response response = target("/post/like/2011052407/1")
				.request()
				.put(nullEntity);
		
		assertEquals(200, response.getStatus());
	}
	
	@Ignore
	@Test
	public void testPostServiceCancelLike(){
		Response response = target("/post/cancelLike/2011052407/1")
				.request()
				.put(nullEntity);
		
		assertEquals(200, response.getStatus());
	}
	
	@Ignore
	@Test
	public void testPostServiceCollectPost(){
		Response response = target("/post/collect/2011052407/1")
				.request()
				.put(nullEntity);
		
		assertEquals(200, response.getStatus());
	}
}
