package systemTest;

import static org.junit.Assert.assertEquals;

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
import org.junit.Test;

import persistence.DAO;
import service.UserService;
import utils.EntityManagerFactoryCreator;

public class UserServiceTest extends JerseyTest{
	private static Entity<String> nullEntity;
	@BeforeClass
	public static void classSetUp() throws Exception{
		nullEntity = Entity.text("");
		
		User u = new User("2011052407");
		User u1 = new User("2011052406");
		
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<User> udao = new DAO<User>(User.class, em);
		
		em.getTransaction().begin();
		udao.create(u);
		udao.create(u1);
		em.getTransaction().commit();
	}
	@Override
	protected Application configure(){
		return new ResourceConfig(UserService.class).register(JacksonFeature.class);
	}
	
	@Test
	public void testPostServiceAddPost(){
		Response response = target("/user/follow/2011052407/2011052406")
				.register(JacksonFeature.class)
				.request(MediaType.APPLICATION_JSON)
				.put(nullEntity);
		assertEquals(200, response.getStatus());
		
	}
}
