package intregatedTest;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Post;
import model.User;

import org.junit.Before;
import org.junit.Test;

import factory.PostFactory;
import persistence.DAO;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;

public class UserAddPostIntegratedTest {
	private EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
	private EntityManager em;
	
	@Before
	public void classSetUp() throws Exception{
		em = emf.createEntityManager();
		em.getTransaction().begin();
		User u =new User("2011052407");
		DAO<User> dao = new DAO<User>(User.class, em);
		dao.create(u);
		em.getTransaction().commit();
	}
	
	@Test
	public void testUserAddPost() throws Exception{
		Post p = (Post) new PostFactory().create(ParamGenerator.generatePostParam(PostType.DISSCUSSION));
		
		DAO<User> dao = new DAO<User>(User.class, em);
		em.getTransaction().begin();
		User u = dao.singleResultRead("2011052407", User.class);
		assertEquals(0, u.getPosts().size());
		
		u.addPost(p);
		dao.update(u);
		
		User u1 = dao.singleResultRead("2011052407", User.class);
		assertEquals(u, u1);
		assertEquals(1, u1.getPosts().size());
		em.getTransaction().commit();
		
	}
}
