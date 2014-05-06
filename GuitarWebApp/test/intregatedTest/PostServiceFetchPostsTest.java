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
import services.PostFactory;
import services.PostService;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;

public class PostServiceFetchPostsTest {
	
	@Before
	public void setUp() throws Exception{
		User u1 = new User("2011052407");
		User u2 = new User("2011052406");
		User u3 = new User("2011052405");
		
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		DAO<User> udao = new DAO<User>(User.class, em);
		
		em.getTransaction().begin();
		udao.create(u1);
		udao.create(u2);
		udao.create(u3);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		u1.follow(u2);
		for(int i=0;i<20;i++){
			Post p = new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
			if(i<=8)
				u1.addPost(p);
			else if(i>8&&i<=15){
				u2.addPost(p);
			} else
				u3.addPost(p);
		}
		udao.update(u1);
		udao.update(u2);
		udao.update(u3);
		em.getTransaction().commit();
	}
	
	@Test
	public void testPostServiceFetchPosts(){
		PostService ps = new PostService();
		List<Post> posts1 = ps.fetchPostsByUserID("2011052407");
		assertEquals(9, posts1.size());
		
		List<Post> posts2 = ps.fetchPostsByType(PostType.ACTIVITY);
		assertEquals(10, posts2.size());
		
		List<Post> posts3 = ps.fetchPostsByFollowee("2011052407");
		assertEquals(7, posts3.size());
		
	}
}
