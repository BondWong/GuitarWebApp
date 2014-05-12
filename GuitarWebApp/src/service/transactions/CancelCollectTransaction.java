package service.transactions;

import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Post;
import model.User;

public class CancelCollectTransaction extends DAOTransaction{

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
		u.cancelCollect(p);
		pdao.update(p);
		return null;
	}

}
