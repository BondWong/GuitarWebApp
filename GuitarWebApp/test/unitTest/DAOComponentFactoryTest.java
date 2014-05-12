package unitTest;

import static org.junit.Assert.assertEquals;
import model.Comment;
import model.Post;
import model.User;

import org.junit.Test;

import persistence.DAOComponentFactory;
import persistence.components.CollectionReadableImp;
import persistence.components.CreatableImp;
import persistence.components.NonCollectionReadable;
import persistence.components.NonCreatable;
import persistence.components.NonDeletable;
import persistence.components.SingleResultReadableImp;

public class DAOComponentFactoryTest {
	private DAOComponentFactory daocf;
	
	@Test
	public void testUserDAOComponentFactory() throws Exception{
		daocf = DAOComponentFactory.createInstance(null, User.class);
		assertEquals(CreatableImp.class, daocf.createCreatable().getClass());
		assertEquals(SingleResultReadableImp.class, daocf.createSingleResultReadable().getClass());
		assertEquals(NonCollectionReadable.class, daocf.createCollectionReadable().getClass());
		assertEquals(NonDeletable.class, daocf.createDeletable().getClass());
	}
	
	@Test
	public void testPostDAOComponentFactory() throws Exception{
		daocf = DAOComponentFactory.createInstance(null, Post.class);
		assertEquals(NonCreatable.class, daocf.createCreatable().getClass());
		assertEquals(SingleResultReadableImp.class, daocf.createSingleResultReadable().getClass());
		assertEquals(CollectionReadableImp.class, daocf.createCollectionReadable().getClass());
		assertEquals(NonDeletable.class, daocf.createDeletable().getClass());
	}
	
	@Test
	public void testCommentDAOComponentFactory() throws Exception{
		daocf = DAOComponentFactory.createInstance(null, Comment.class);
		assertEquals(NonCreatable.class, daocf.createCreatable().getClass());
		assertEquals(SingleResultReadableImp.class, daocf.createSingleResultReadable().getClass());
		assertEquals(NonCollectionReadable.class, daocf.createCollectionReadable().getClass());
		assertEquals(NonDeletable.class, daocf.createDeletable().getClass());
	}
}
