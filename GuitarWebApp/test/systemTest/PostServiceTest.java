package systemTest;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.LinkedHashSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.User;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import persistence.DAO;
import service.PostService;
import service.factory.PostRep;
import utils.EntityManagerFactoryCreator;
import utils.PostType;

public class PostServiceTest extends JerseyTest{
	private static Entity<String> nullEntity;
	@BeforeClass
	public static void classSetUp() throws Exception{
		nullEntity = Entity.text("");
		User u = new User("2011052407");
		
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<User> udao = new DAO<User>(User.class, em);
		
		em.getTransaction().begin();
		udao.create(u);
		em.getTransaction().commit();
	}
	@Override
	protected Application configure(){
		return new ResourceConfig(PostService.class).register(JacksonFeature.class);
	}
	
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
		
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testPostServiceLikePost(){
		Response response = target("/post/like/2011052407/1")
				.request()
				.put(nullEntity);
		
		assertEquals(200, response.getStatus());
	}
}
