package persistence;

import javax.persistence.EntityManager;

import model.Post;
import model.User;
import model.Comment;

public abstract class DAOComponentFactory {
	public static <T> DAOComponentFactory createInstance(EntityManager em, Class<T> t) throws Exception{
		if(t.equals(Post.class)){
			return new PostDAOComponentFactory(em);
		} else if(t.equals(User.class)){
			return new UserDAOComponentFactory(em);
		} else if(t.equals(Comment.class)){
			return new CommentDAOComponentFactory(em);
		} else{
			throw new Exception();
		}
		
	}
	
	public abstract Creatable createCreatable(EntityManager em);
	
	public abstract SingleResultReadable createSingleResultReadable(EntityManager em);
	
	public abstract CollectionReadable createCollectionReadable(EntityManager em);
	
	public abstract Deletable createDeletable(EntityManager em);
	
}
