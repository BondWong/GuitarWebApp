package intregatedTest;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Comment;
import model.Post;
import model.User;

import org.junit.Before;
import org.junit.Test;

import factory.CommentFactory;
import factory.PostFactory;
import persistence.DAO;
import utils.CommentType;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;

public class FetchPostCommentsTest {
	@Before
	public void setUp() throws Exception{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		User u1 = new User("2011052407");
		User u2 = new User("2011052406");
		DAO<User> udao = new DAO<User>(User.class, em);
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		
		em.getTransaction().begin();
		udao.create(u1);
		udao.create(u2);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		u1 = udao.singleResultRead("2011052407", User.class);
		u2 = udao.singleResultRead("2011052406", User.class);
		for(int i=0;i<10;i++){
			Post p = new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
			u2.addPost(p);
		}
		udao.update(u1);
		udao.update(u2);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		u1 = udao.singleResultRead("2011052407", User.class);
		u2 = udao.singleResultRead("2011052406", User.class);
		for(int i=0;i<10;i++){
			Comment c = new CommentFactory().create(ParamGenerator.generateCommentParam(CommentType.ANSWER));
			Post p = pdao.singleResultRead(new Long(i+1), Post.class);
			u1.addComment(p, c);
			pdao.update(p);
		}
		em.getTransaction().commit();
	}
	
	@Test
	public void testFetchPostsByUserID() throws Exception{
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		DAO<User> udao = new DAO<User>(User.class, em);
		DAO<Comment> cdao = new DAO<Comment>(Comment.class, em);
		
		em.getTransaction().begin();
		List<Post> posts1 = pdao.collectionRead("Post.fetchByUserID", Post.class, "2011052406");
		Set<Comment> comments = posts1.get(1).getComments();
		em.getTransaction().commit();
		
		assertEquals(10, posts1.size());
		assertEquals(1, comments.size());
		assertEquals(CommentType.ANSWER, comments.iterator().next().getType());
		
		em.getTransaction().begin();
		User u = udao.singleResultRead("2011052407", User.class);
		Comment c = cdao.singleResultRead(new Long(1), Comment.class);
		u.supportComment(c);
		cdao.update(c);
		em.getTransaction().commit();
		
		c = cdao.singleResultRead(new Long(1), Comment.class);
		assertEquals(u, c.getSupportors().iterator().next());
		assertEquals(1, c.getSupportNum());
		
		em.getTransaction().begin();
		u = udao.singleResultRead("2011052407", User.class);
		Post p = pdao.singleResultRead(new Long(1), Post.class);
		c = p.getComments().iterator().next();
		Long ID = c.getID();
		u.deleteComment(p, c);
		pdao.update(p);
		em.getTransaction().commit();
		
		assertEquals(0, p.getComments().size());
		
		em.getTransaction().begin();
		Comment co = cdao.singleResultRead(ID, Comment.class);
		em.getTransaction().commit();
		
		assertNull(co);
	}

}
