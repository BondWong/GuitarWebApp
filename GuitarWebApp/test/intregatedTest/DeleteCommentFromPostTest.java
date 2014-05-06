package intregatedTest;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Comment;
import model.Post;
import model.User;

import org.junit.BeforeClass;
import org.junit.Test;

import persistence.DAO;
import services.CommentFactory;
import services.PostFactory;
import utils.CommentType;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;

public class DeleteCommentFromPostTest {
	
	@BeforeClass
	public static void setUp() throws Exception{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<User> dao = new DAO<User>(User.class, em);
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		
		User u =new User("2011052407");
		User u1 = new User("2011052406");
		Post p = new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		
		em.getTransaction().begin();
		dao.create(u);
		dao.create(u1);
		u.addPost(p);
		dao.update(u);
		u1.addComment(p, new CommentFactory().create(ParamGenerator.generateCommentParam(CommentType.COMMENT)));
		pdao.update(p);
		em.getTransaction().commit();
	}
	
	@Test
	public void testAddComment() throws Exception{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		DAO<Post> dao = new DAO<Post>(Post.class, em);
		DAO<User> udao = new DAO<User>(User.class, em);
		DAO<Comment> cdao = new DAO<Comment>(Comment.class, em);
		em.getTransaction().begin();
		Post p = dao.singleResultRead(new Long(1), Post.class);
		User u = udao.singleResultRead("2011052406",User.class);
		Comment c = cdao.singleResultRead(new Long(1), Comment.class);
		u.deleteComment(p, c);
		
		dao.update(p);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		p = dao.singleResultRead(new Long(1), Post.class);
		assertEquals(0, p.getComments().size());
		
	}
}
