package service.transactions;

import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import security.validation.CommentRep;
import service.factory.CommentFactory;
import model.Comment;
import model.Post;
import model.User;

public class AddCommentTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		Long postID = (Long) params.get("postID");
		CommentRep commentRep = (CommentRep) params.get("commentRep");
		
		DAO<User> udao = new DAO<User>(User.class, em);
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		
		Comment c = new CommentFactory().create(commentRep);
		User u = udao.singleResultRead(userID, User.class);
		Post p = pdao.singleResultRead(postID, Post.class);
		u.addComment(p, c);
		pdao.update(p);
		return null;
	}

}
