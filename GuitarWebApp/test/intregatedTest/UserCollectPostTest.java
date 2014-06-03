package intregatedTest;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Post;
import model.User;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.DAO;
import service.factory.PostFactory;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;

public class UserCollectPostTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private DAO<User> udao;
	private DAO<Post> pdao;
	
	@BeforeClass
	public static void classSetUp(){
		emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
	}
	
	@Before
	public void setUp() throws Exception{
		udao = new DAO<User>(User.class, em);
		pdao = new DAO<Post>(Post.class, em);
	}
	
	@Test
	public void testUserLikePost() throws Exception{
		User u1 = new User("2011052407");
		User u2 = new User("2011052406");
		
		em.getTransaction().begin();
		udao.create(u1);
		udao.create(u2);
		em.getTransaction().commit();
		
		Post p1 = new PostFactory().create(ParamGenerator.generatePostParam(PostType.DISCUSSION));
		em.getTransaction().begin();
		u1 = udao.singleResultRead("2011052407", User.class);
		u1.addPost(p1);
		udao.update(u1);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		u2 = udao.singleResultRead("2011052406", User.class);
		p1 = pdao.singleResultRead(new Long(1), Post.class);
		u2.collectPost(p1);
		pdao.update(p1);
		em.getTransaction().commit();
		
		User u = udao.singleResultRead("2011052406", User.class);
		assertEquals(1, u.getCollectedPosts().size());
		assertEquals(p1, u.getCollectedPosts().iterator().next());
		
		em.getTransaction().begin();
		u2 = udao.singleResultRead("2011052406", User.class);
		p1 = pdao.singleResultRead(new Long(1), Post.class);
		u2.cancelCollect(p1);
		pdao.update(p1);
		em.getTransaction().commit();
		
		u = udao.singleResultRead("2011052406", User.class);
		assertEquals(0, u.getCollectedPosts().size());
		
	}
}
