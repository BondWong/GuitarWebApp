package systemTest;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Comment;
import model.Post;
import model.User;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.DAO;
import service.CommentService;
import service.factory.CommentFactory;
import service.factory.CommentRep;
import service.factory.PostFactory;
import utils.CommentType;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;

public class CommentServiceTest extends JerseyTest{
private static Entity<String> nullEntity;
	
	@BeforeClass
	public static void classSsetUp() throws Exception{
		nullEntity = Entity.text("");
		
		EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		DAO<User> dao = new DAO<User>(User.class, em);
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		
		User u =new User("2011052407");
		Post p = new PostFactory().create(ParamGenerator.generatePostParam(PostType.QUESTION));
		
		em.getTransaction().begin();
		dao.create(u);
		
		u = dao.singleResultRead("2011052407", User.class);
		u.addPost(p);
		dao.update(u);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		u = dao.singleResultRead("2011052407", User.class);
		Comment c = new CommentFactory().create(ParamGenerator.generateCommentParam(CommentType.ANSWER));
		p = pdao.singleResultRead(new Long(1), Post.class);
		u.addComment(p, c);
		pdao.update(p);
		em.getTransaction().commit();
	}
	
	@Override
	public Application configure(){
		return new ResourceConfig(CommentService.class).register(JacksonFeature.class);
	}
	
	@Test
	public void testCommentServiceAddComment(){
		CommentRep commentRep = new CommentRep();
		commentRep.setContent("gehe");
		
		Entity<CommentRep> entity = Entity.json(commentRep);
		commentRep.setType(CommentType.COMMENT);
		Response response = target("/comment/add/2011052407/1")
				.register(JacksonFeature.class).request(MediaType.APPLICATION_JSON)
				.post(entity);
		
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testCommentServiceDeleteComment(){
		Response response = target("/comment/delete/2011052407/1/1")
				.request()
				.delete();
		
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testCommentServiceSupportAnswer(){
		Response response = target("/comment/support/2011052407/1")
				.request().put(nullEntity);
		
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testCommentServiceCancelSupport(){
		Response response = target("/comment/cancelSupport/2011052407/1")
				.request().put(nullEntity);
		
		assertEquals(200, response.getStatus());
	}
}
