package persistence;

import javax.persistence.EntityManager;

import components.CollectionReadable;
import components.Creatable;
import components.Deletable;
import components.SingleResultReadable;

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
	
	public abstract Creatable createCreatable();
	
	public abstract SingleResultReadable createSingleResultReadable();
	
	public abstract CollectionReadable createCollectionReadable();
	
	public abstract Deletable createDeletable();
	
}
