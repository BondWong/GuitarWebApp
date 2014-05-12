package intregatedTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Post;
import model.Post.ShortCut;
import model.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import factory.Factory;
import factory.PostFactory;
import service.transactions.DAOTransaction;
import service.transactions.GetPostsByIDsTransaction;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;

public class GetPostByIDsTransactionTest {
	@Before
	public void setUp() throws Exception{
		User user = new User("2011052407");
		
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<User> udao = new DAO<User>(User.class, em);
		Factory f = new PostFactory();
		
		em.getTransaction().begin();
		udao.create(user);
		Post p1 = (Post) f.create(ParamGenerator.generatePostParam(PostType.DISSCUSSION));
		Post p2 = (Post) f.create(ParamGenerator.generatePostParam(PostType.DISSCUSSION));
		Post p3 = (Post) f.create(ParamGenerator.generatePostParam(PostType.DISSCUSSION));
		Post p4 = (Post) f.create(ParamGenerator.generatePostParam(PostType.DISSCUSSION));
		user.addPost(p1);
		user.addPost(p2);
		user.addPost(p3);
		user.addPost(p4);
		udao.update(user);
		em.getTransaction().commit();
	}
	
	@Test
	public void testGetPostsByIDs() throws Exception{
		DAOTransaction transaction = new GetPostsByIDsTransaction();
		
		Map<String, Object> params = new HashMap<String, Object>();
		List<Long> postIDs = new ArrayList<Long>();
		postIDs.add(new Long(1));
		postIDs.add(new Long(2));
		postIDs.add(new Long(3));
		params.put("postIDs", postIDs);
		@SuppressWarnings("unchecked")
		List<Post.ShortCut> shortCuts = (List<ShortCut>) transaction.execute(params);
		
		Assert.assertNotNull(shortCuts);
		assertEquals(3, shortCuts.size());
	}
}
