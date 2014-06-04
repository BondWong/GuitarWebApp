package intregatedTest;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.User;

import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import utils.EntityManagerFactoryCreator;

public class UpdateUserProfileTest {
	@Before
	public void setUp() throws Exception {
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		DAO<User> udao = new DAO<User>(User.class, em);
		
		User user = new User("2011052410");
		user.setPassword("123456");
		
		em.getTransaction().begin();
		udao.create(user);
		em.getTransaction().commit();
	}
	
	@Test
	public void testUpdateProfile() throws Exception {
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		DAO<User> udao = new DAO<User>(User.class, em);
		em.getTransaction().begin();
		User user = udao.singleResultRead("2011052410", User.class);
		assertNull(user.getRelationShip());
		assertNull(user.getBirthday());
		assertEquals(user.getID(),user.getNickName());
		assertNull(user.getGender());
		assertNull(user.getLookingFor());
		
		user.setBirthday(new Date());
		user.setGender("Male");
		user.setLookingFor("girls");
		user.setRelationShip("single");
		user.setNickName("GG");
		
		udao.update(user);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		user = udao.singleResultRead("2011052410", User.class);
		em.getTransaction().commit();
		
		assertEquals("Male", user.getGender());
		assertEquals("girls", user.getLookingFor());
		assertEquals("single", user.getRelationShip());
		assertEquals("GG", user.getNickName());
	}
}
