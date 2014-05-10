package intregatedTest;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Post;
import model.User;

import org.junit.Before;
import org.junit.Test;

import factory.CommentFactory;
import factory.PostFactory;
import persistence.DAO;
import services.CommentService;
import utils.CommentType;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;

public class CommentServiceDeleteCommentTest {
	
	@Before
	public void setUp() throws Exception{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<User> udao = new DAO<User>(User.class, em);
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		
		User u1 = new User("2011052407");
		User u2 = new User("2011052406");
		Post p = new PostFactory().create(ParamGenerator.generatePostParam(PostType.QUESTION));
		
		em.getTransaction().begin();
		udao.create(u1);
		udao.create(u2);
		u2.addPost(p);
		udao.update(u2);
		u1.addComment(p, new CommentFactory().create(ParamGenerator.generateCommentParam(CommentType.ANSWER)));
		pdao.update(p);
		em.getTransaction().commit();
	}
	
	@Test
	public void testAddComment() throws Exception{
		CommentService cs = new CommentService();
		cs.deleteComment("2011052407", new Long(1), new Long(1));
		
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		em.getTransaction().begin();
		Post p = pdao.singleResultRead(new Long(1), Post.class);
		em.getTransaction().commit();
		
		assertEquals(0, p.getComments().size());
	}
}
