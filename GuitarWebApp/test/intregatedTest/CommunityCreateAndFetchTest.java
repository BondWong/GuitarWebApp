package intregatedTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Community;

import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import utils.EntityManagerFactoryCreator;
import utils.PostType;

public class CommunityCreateAndFetchTest {
	@Before
	public void setUp() throws Exception{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<Community> cdao = new DAO<Community>(Community.class, em);
		
		Community c1 = new Community();
		c1.setType(PostType.ACTIVITY);
		c1.setName(PostType.ACTIVITY.name());
		Community c2 = new Community();
		c2.setType(PostType.DISSCUSSION);
		c2.setName(PostType.DISSCUSSION.name());
		Community c3 = new Community();
		c3.setType(PostType.QUESTION);
		c3.setName(PostType.QUESTION.name());
		
		em.getTransaction().begin();
		cdao.create(c1);
		cdao.create(c2);
		cdao.create(c3);
		em.getTransaction().commit();
		
	}
	
	@Test
	public void testFetchCommunities() throws Exception{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<Community> cdao = new DAO<Community>(Community.class, em);
		
		List<Community> communities = new ArrayList<Community>();
		
		em.getTransaction().begin();
		communities = cdao.collectionRead("Community.fetchAll", Community.class);
		em.getTransaction().commit();
		
		assertEquals(3, communities.size());
	}
}
