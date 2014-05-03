package intregatedTest;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.User;

import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import utils.EntityManagerFactoryCreator;

public class DAOCreateUserTest {
	private DAO<User> dao;
	private User user;
	private EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
	private EntityManager em;
	
	@Before
	public void setUp() throws Exception{
		em = emf.createEntityManager();
		dao = new DAO<User>(User.class, em);
	}
	
	@Test
	public void testCreateUser(){
		user = new User("2011052407");
		em.getTransaction().begin();
		dao.create(user);
		User u = dao.singleResultRead("2011052407", User.class);
		em.getTransaction().commit();
		assertEquals(user, u);
	}
	
}
