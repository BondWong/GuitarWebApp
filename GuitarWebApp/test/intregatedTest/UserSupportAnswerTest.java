package intregatedTest;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Comment;
import model.Post;
import model.User;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.DAO;
import service.factory.CommentFactory;
import service.factory.PostFactory;
import utils.CommentType;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;

public class UserSupportAnswerTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private DAO<User> udao;
	private DAO<Post> pdao;
	private DAO<Comment> cdao;
	
	@BeforeClass
	public static void classSetUp(){
		emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
	}
	
	@Before
	public void setUp() throws Exception{
		udao = new DAO<User>(User.class, em);
		pdao = new DAO<Post>(Post.class, em);
		cdao = new DAO<Comment>(Comment.class, em);
		
		User u1 = new User("2011052407");
		User u2 = new User("2011052406");
		User u3 = new User("2011052405");
		
		em.getTransaction().begin();
		udao.create(u1);
		udao.create(u2);
		udao.create(u3);
		em.getTransaction().commit();
		
		Post p1 = new PostFactory().create(ParamGenerator.generatePostParam(PostType.QUESTION));
		em.getTransaction().begin();
		u1 = udao.singleResultRead("2011052407", User.class);
		u1.addPost(p1);
		udao.update(u1);
		em.getTransaction().commit();
	}
	
	@Test
	public void testUserLikePost() throws Exception{
		User u2;
		User u3;	
		
		em.getTransaction().begin();
		u2 = udao.singleResultRead("2011052406", User.class);
		Comment c = new CommentFactory().create(ParamGenerator.generateCommentParam(CommentType.ANSWER));
		Post p = pdao.singleResultRead(new Long(1), Post.class);
		u2.addComment(p, c);
		pdao.update(p);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		u3 = udao.singleResultRead("2011052405", User.class);
		c = cdao.singleResultRead(new Long(1), Comment.class);
		u3.supportComment(c);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		p = pdao.singleResultRead(new Long(1), Post.class);
		c = p.getComments().iterator().next();
		em.getTransaction().commit();
		
		assertEquals(1, c.getSupportNum());
		assertEquals(u3,c.getSupportors().iterator().next());
		
		em.getTransaction().begin();
		c = cdao.singleResultRead(new Long(1), Comment.class);
		u3 = udao.singleResultRead("2011052405", User.class);
		u3.cancelSupport(c);
		em.getTransaction().commit();
		
		c = cdao.singleResultRead(new Long(1), Comment.class);
		assertEquals(0, c.getSupportNum());
		
	}
}
