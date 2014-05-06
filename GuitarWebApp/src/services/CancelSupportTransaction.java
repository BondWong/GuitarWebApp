package services;

import java.util.Map;

import javax.persistence.EntityManager;

import model.Comment;
import model.User;
import persistence.DAO;

public class CancelSupportTransaction extends Transaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		Long commentID = (Long) params.get("commentID");
		
		DAO<User> udao = new DAO<User>(User.class, em);
		DAO<Comment> cdao = new DAO<Comment>(Comment.class, em);
		
		Comment c = cdao.singleResultRead(commentID, Comment.class);
		User u = udao.singleResultRead(userID, User.class);
		u.cancelSupport(c);
		cdao.update(c);
		return null;
	}

}
