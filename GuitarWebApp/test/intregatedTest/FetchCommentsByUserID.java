package intregatedTest;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Comment;
import model.Post;
import model.User;

import org.junit.Before;
import org.junit.Test;

import persistence.DAO;
import service.factory.CommentFactory;
import service.factory.PostFactory;
import service.transactions.daoTransactions.DAOTransaction;
import service.transactions.daoTransactions.FetchCommentsByUserIDTransaction;
import utils.CommentType;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;

public class FetchCommentsByUserID {
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
		
		Comment c1 = new CommentFactory().create(ParamGenerator.generateCommentParam(CommentType.ANSWER));
		Comment c2 = new CommentFactory().create(ParamGenerator.generateCommentParam(CommentType.ANSWER));
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		em.getTransaction().begin();
		p = pdao.singleResultRead(new Long(1), Post.class);
		u1.addComment(p, c1);
		u1.addComment(p, c2);
		pdao.update(p);
		em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testFetchCommentsByUserID() throws Exception{
		DAOTransaction transaction = new FetchCommentsByUserIDTransaction();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", "2011052407");
		List<Comment> comments = (List<Comment>) transaction.execute(params);
		
		assertEquals(1, comments.size());
	}
	
}
