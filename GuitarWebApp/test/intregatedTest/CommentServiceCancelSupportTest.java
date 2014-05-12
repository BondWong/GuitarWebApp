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
import factory.CommentFactory;
import factory.PostFactory;
import service.CommentService;
import utils.CommentType;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;

public class CommentServiceCancelSupportTest {
	
	@Before
	public void setUp() throws Exception{
		User u1 = new User("2011052407");
		User u2 = new User("2011052406");
		
		Post p = new PostFactory().create(ParamGenerator.generatePostParam(PostType.QUESTION));
		
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<User> udao = new DAO<User>(User.class, em);
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		DAO<Comment> cdao = new DAO<Comment>(Comment.class, em);
		
		em.getTransaction().begin();
		udao.create(u1);
		udao.create(u2);
		u1.addPost(p);
		udao.update(u1);
		Comment c = new CommentFactory().create(ParamGenerator.generateCommentParam(CommentType.ANSWER));
		u2.addComment(p, c);
		pdao.update(p);
		u1.supportComment(c);
		cdao.update(c);
		em.getTransaction().commit();
	}
	
	@Test
	public void testSupportAnswer() throws Exception{
		CommentService cs = new CommentService();
		cs.supportAnswer("2011052407", new Long(1));
		
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<Comment> cdao = new DAO<Comment>(Comment.class, em);
		
		Comment c = cdao.singleResultRead(new Long(1), Comment.class);
		assertEquals(1, c.getSupportNum());
	}
	
}
