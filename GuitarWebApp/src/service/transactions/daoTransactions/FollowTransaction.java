package service.transactions.daoTransactions;

import java.util.Map;

import javax.persistence.EntityManager;

import model.User;
import persistence.DAO;

public class FollowTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		String otherUserID = (String) params.get("otherUserID");
		
		User user =null;
		User followee = null;
		
		DAO<User> udao = new DAO<User>(User.class, em);
		user = udao.singleResultRead(userID, User.class);
		followee = udao.singleResultRead(otherUserID, User.class);
		user.follow(followee);
		udao.update(user);
		return null;
	}

}
