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
import factory.PostFactory;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;

public class FetchPostsByUserIDTest {
	@Before
	public void setUp() throws Exception{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		User u1 = new User("2011052407");
		User u2 = new User("2011052406");
		DAO<User> udao = new DAO<User>(User.class, em);
		em.getTransaction().begin();
		udao.create(u1);
		udao.create(u2);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		u1 = udao.singleResultRead("2011052407", User.class);
		u2 = udao.singleResultRead("2011052406", User.class);
		for(int i=0;i<10;i++){
			Post p = new PostFactory().create(ParamGenerator.generatePostParam(PostType.QUESTION));
			u1.addPost(p);
		}
		for(int i=0;i<10;i++){
			Post p = new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
			u2.addPost(p);
		}
		udao.update(u1);
		udao.update(u2);
		em.getTransaction().commit();
	}
	
	@Test
	public void testFetchPostsByUserID() throws Exception{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		
		em.getTransaction().begin();
		List<Post> posts1 = pdao.collectionRead("Post.fetchByUserID", 0, 5, Post.class, "2011052407");
		List<Post> posts2 = pdao.collectionRead("Post.fetchByUserID", 3, 2, Post.class, "2011052407");
		List<Post> posts3 = pdao.collectionRead("Post.fetchByType", 0, 6, Post.class, PostType.ACTIVITY);
		List<Post> posts4 = pdao.collectionRead("Post.fetchByType", 3, 5, Post.class, PostType.QUESTION);
		em.getTransaction().commit();
		
		assertEquals(5, posts1.size());
		assertEquals(2, posts2.size());
		assertEquals(6, posts3.size());
		assertEquals(5, posts4.size());
		
		for(Post p : posts3){
			assertEquals(PostType.ACTIVITY, p.getType());
		}
		for(Post p : posts4){
			assertEquals(PostType.QUESTION, p.getType());
		}
	}
	
}
