package intregatedTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.User;

import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import utils.EntityManagerFactoryCreator;

public class FetchUserByIDandPasswordTest {
	@Before
	public void setUp() throws Exception{
		EntityManagerFactory emf =EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<User> udao = new DAO<User>(User.class, em);
		
		User u = new User("2011052407");
		u.setPassword("1901103390");
		
		em.getTransaction().begin();
		udao.create(u);
		em.getTransaction().commit();
	}
	
	@Test
	public void testFetchUserByIDandPassword() throws Exception{
		EntityManagerFactory emf =EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<User> udao = new DAO<User>(User.class, em);
		
		em.getTransaction().begin();
		List<User> users = (List<User>) udao.collectionRead("User.getByIDandPassword", 0, 1, User.class, "2011052407", "1901103390");
		em.getTransaction().commit();
		
		assertEquals(1, users.size());
	}
}
