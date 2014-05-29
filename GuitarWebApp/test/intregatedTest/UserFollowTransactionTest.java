package intregatedTest;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.User;

import org.junit.BeforeClass;
import org.junit.Test;

import persistence.DAO;
import service.transactions.Transaction;
import service.transactions.daoTransactions.CancelFollowTransaction;
import service.transactions.daoTransactions.FollowTransaction;
import service.transactions.daoTransactions.GetUserByIDTransaction;
import utils.EntityManagerFactoryCreator;

public class UserFollowTransactionTest {
	@BeforeClass
	public static void setUp() throws Exception{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<User> dao = new DAO<User>(User.class, em);

		User u =new User("2011052407");
		User u1 = new User("2011052406");
		
		em.getTransaction().begin();
		dao.create(u);
		dao.create(u1);
		em.getTransaction().commit();
	}
	
	@Test
	public void testUserFollowTransaction() throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", "2011052407");
		params.put("otherUserID", "2011052406");
		Transaction transaction = new FollowTransaction();
		transaction.execute(params);
		
		transaction = new GetUserByIDTransaction();
		User u = (User) transaction.execute(params);
		
		assertEquals("2011052406", u.getFollowees().iterator().next().getID());
		
		transaction = new CancelFollowTransaction();
		transaction.execute(params);
		
		transaction = new GetUserByIDTransaction();
		u = (User) transaction.execute(params);
		
		assertEquals(0, u.getFollowees().size());
	}
}
