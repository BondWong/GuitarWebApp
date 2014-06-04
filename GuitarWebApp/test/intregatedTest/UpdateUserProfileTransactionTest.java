package intregatedTest;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.User;

import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import service.transactions.Transaction;
import service.transactions.daoTransactions.UpdateUserProfileTransaction;
import utils.EntityManagerFactoryCreator;

public class UpdateUserProfileTransactionTest {
	@Before
	public void setUp() throws Exception {
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		DAO<User> udao = new DAO<User>(User.class, em);
		
		User user = new User("2011052407");
		user.setPassword("1901103390");
		em.getTransaction().begin();
		udao.create(user);
		em.getTransaction().commit();
	}
	
	@Test
	public void testUpdateUserProfileTransaction() throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", "2011052407");
		params.put("birthday", new Date());
		params.put("nickName", "Bond");
		params.put("gender", "Male");
		params.put("lookingFor", "girls");
		params.put("relationship", "single");
		Transaction transaction = new UpdateUserProfileTransaction();
		transaction.execute(params);
		
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		DAO<User> udao = new DAO<User>(User.class, em);
		User u = udao.singleResultRead("2011052407", User.class);
		assertEquals("Bond", u.getNickName());
		assertEquals("Male", u.getGender());
		assertEquals("girls", u.getLookingFor());
		assertEquals("single", u.getRelationShip());
		
	}
}
