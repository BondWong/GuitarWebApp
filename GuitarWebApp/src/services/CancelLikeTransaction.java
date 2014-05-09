package services;

import java.util.Map;

import javax.persistence.EntityManager;

import model.Post;
import model.User;
import persistence.DAO;

public class CancelLikeTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		Long postID = (Long) params.get("postID");
		
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		DAO<User> udao = new DAO<User>(User.class, em);
		User u = udao.singleResultRead(userID, User.class);
		Post p = pdao.singleResultRead(postID, Post.class);
		u.cancelLike(p);
		pdao.update(p);
		return null;
	}

}
