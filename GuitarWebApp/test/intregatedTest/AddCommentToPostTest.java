package intregatedTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Comment;
import model.Post;
import model.User;

import org.junit.BeforeClass;
import org.junit.Test;

import factory.CommentFactory;
import factory.PostFactory;
import persistence.DAO;
import utils.CommentType;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;

public class AddCommentToPostTest {
	
	@BeforeClass
	public static void setUp() throws Exception{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<User> dao = new DAO<User>(User.class, em);

		User u =new User("2011052407");
		Post p = new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		
		em.getTransaction().begin();
		dao.create(u);
		
		u = dao.singleResultRead("2011052407", User.class);
		u.addPost(p);
		dao.update(u);
		em.getTransaction().commit();
	}
	
	@Test
	public void testAddComment() throws Exception{
		Comment c = new CommentFactory().create(ParamGenerator.generateCommentParam(CommentType.COMMENT));
		
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		DAO<Post> dao = new DAO<Post>(Post.class, em);
		DAO<User> udao = new DAO<User>(User.class, em);
		DAO<Comment> cdao = new DAO<Comment>(Comment.class, em);
		em.getTransaction().begin();
		Post p = dao.singleResultRead(new Long(1), Post.class);
		User u = udao.singleResultRead("2011052407",User.class);
		u.addComment(p, c);
				
		dao.update(p);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		p = dao.singleResultRead(new Long(1), Post.class);
		Comment comment = p.getComments().iterator().next();
		assertNotNull(comment);
		assertEquals(new Long(1), comment.getID());
		
		Comment c1 = cdao.singleResultRead(new Long(1), Comment.class);
		assertEquals(comment, c1);
		em.getTransaction().commit();
		
	}
}
