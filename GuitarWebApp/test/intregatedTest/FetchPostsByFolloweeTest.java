package intregatedTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Post;
import model.User;

import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import service.factory.PostFactory;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;

public class FetchPostsByFolloweeTest {
	@Before
	public void setUp() throws Exception{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		User u1 = new User("2011052407");
		User u2 = new User("2011052406");
		User u3 = new User("2011052405");
		DAO<User> udao = new DAO<User>(User.class, em);
		em.getTransaction().begin();
		udao.create(u1);
		udao.create(u2);
		udao.create(u3);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		u1 = udao.singleResultRead("2011052407", User.class);
		u2 = udao.singleResultRead("2011052406", User.class);
		for(int i=0;i<5;i++){
			Post p = new PostFactory().create(ParamGenerator.generatePostParam(PostType.QUESTION));
			u1.addPost(p);
		}
		for(int i=0;i<3;i++){
			Post p = new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
			u2.addPost(p);
		}
		for(int i=0;i<4;i++){
			Post p = new PostFactory().create(ParamGenerator.generatePostParam(PostType.DISCUSSION));
			u1.addPost(p);
		}
		udao.update(u1);
		udao.update(u2);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		u3.follow(u1);
		u3.follow(u2);
		em.getTransaction().commit();
	}
	
	@Test
	public void testFetchPostsByUserID() throws Exception{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		
		em.getTransaction().begin();
		List<Post> posts = pdao.collectionRead("Post.fetchByFollowee", Post.class, "2011052405");
		em.getTransaction().commit();
		
		assertEquals(10, posts.size());
	}
	
}
