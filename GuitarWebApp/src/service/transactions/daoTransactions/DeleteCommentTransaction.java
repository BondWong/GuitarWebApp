package service.transactions.daoTransactions;

import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Comment;
import model.Post;
import model.User;

public class DeleteCommentTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		Long postID = (Long) params.get("postID");
		Long commentID = (Long) params.get("commentID");
		
		DAO<User> udao = new DAO<User>(User.class, em);
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		DAO<Comment> cdao = new DAO<Comment>(Comment.class, em);
		
		Comment c = cdao.singleResultRead(commentID, Comment.class);
		User u = udao.singleResultRead(userID, User.class);
		Post p = pdao.singleResultRead(postID, Post.class);
		u.deleteComment(p, c);
		pdao.update(p);
		return null;
	}

}
