package intregatedTest;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.User;
import persistence.DAO;
import utils.EntityManagerFactoryCreator;

public class UserFollowOtherUserTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private DAO<User> udao;
	
	@BeforeClass
	public static void classSetUp(){
		emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
	}
	
	@Before
	public void setUp() throws Exception{
		udao = new DAO<User>(User.class, em);
		User u1 = new User("2011052407");
		User u2 = new User("2011052406");
		User u3 = new User("2011052405");
		User u4 = new User("2011052404");
		
		em.getTransaction().begin();
		udao.create(u1);
		udao.create(u2);
		udao.create(u3);
		udao.create(u4);
		em.getTransaction().commit();
	}
	
	@Test
	public void testUserFollowOtherUser(){
		User ua;
		User ub;
		User uc;
		User ud;
		
		em.getTransaction().begin();
		ua = udao.singleResultRead("2011052407", User.class);
		ub = udao.singleResultRead("2011052406", User.class);
		uc = udao.singleResultRead("2011052405", User.class);
		ud = udao.singleResultRead("2011052404", User.class);
		
		ua.follow(ub);
		ua.follow(uc);
		ub.follow(ua);
		uc.follow(ud);
		
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		ua = udao.singleResultRead("2011052407", User.class);
		ub = udao.singleResultRead("2011052406", User.class);
		uc = udao.singleResultRead("2011052405", User.class);
		ud = udao.singleResultRead("2011052404", User.class);
		em.getTransaction().commit();
		
		assertEquals(2, ua.getFollowees().size());
		assertEquals(1, ub.getFollowees().size());
		assertEquals(1, uc.getFollowees().size());
		assertEquals(0, ud.getFollowees().size());
		
		assertEquals(1, ua.getFollowers().size());
		assertEquals(1, ub.getFollowers().size());
		assertEquals(1, uc.getFollowers().size());
		assertEquals(1, ud.getFollowers().size());
	}
}
