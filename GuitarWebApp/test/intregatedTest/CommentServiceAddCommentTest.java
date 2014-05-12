package intregatedTest;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Comment;
import model.Post;
import model.User;

import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import service.CommentService;
import service.factory.PostFactory;
import utils.CommentType;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;

public class CommentServiceAddCommentTest {
	
	@Before
	public void setUp() throws Exception{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<User> udao = new DAO<User>(User.class, em);
		
		User u1 = new User("2011052407");
		User u2 = new User("2011052406");
		Post p = new PostFactory().create(ParamGenerator.generatePostParam(PostType.QUESTION));
		
		em.getTransaction().begin();
		udao.create(u1);
		udao.create(u2);
		u2.addPost(p);
		udao.update(u2);
		em.getTransaction().commit();
	}
	
	@Test
	public void testAddComment() throws Exception{
		CommentService cs = new CommentService();
		cs.addComment("2011052407", new Long(1), ParamGenerator.generateCommentParam(CommentType.ANSWER));
		
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<User> udao = new DAO<User>(User.class, em);
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		em.getTransaction().begin();
		User u = udao.singleResultRead("2011052407", User.class);
		Post p = pdao.singleResultRead(new Long(1), Post.class);
		Comment c = p.getComments().iterator().next();
		em.getTransaction().commit();
		
		assertEquals(u, c.getOwner());
		assertEquals(1, p.getComments().size());
	}
}
