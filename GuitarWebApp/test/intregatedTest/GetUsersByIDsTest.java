package intregatedTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.User;
import model.User.ShortCut;

import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import service.transactions.Transaction;
import service.transactions.daoTransactions.GetUsersByIDsTransaction;
import utils.EntityManagerFactoryCreator;

public class GetUsersByIDsTest {
	@Before
	public void setUp() throws Exception{
		User u1 = new User("2011052407");
		User u2 = new User("2011052406");
		User u3 = new User("2011052405");
		
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		DAO<User> udao = new DAO<User>(User.class, em);
		udao.create(u1);
		udao.create(u2);
		udao.create(u3);
		em.getTransaction().commit();
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testGetUsersByIDs() throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> userIDs = new ArrayList<String>();
		userIDs.add("2011052407");
		userIDs.add("2011052406");
		userIDs.add("2011052405");
		params.put("userIDs", userIDs);
		
		Transaction transaction = new GetUsersByIDsTransaction();
		List<User.ShortCut> shortCuts = (List<ShortCut>) transaction.execute(params);
	
		assertEquals(3, shortCuts.size());
	}
}
