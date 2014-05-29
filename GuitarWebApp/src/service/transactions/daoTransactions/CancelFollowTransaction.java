package service.transactions.daoTransactions;

import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.User;

public class CancelFollowTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		String otherUserID = (String) params.get("otherUserID");
		DAO<User> udao = new DAO<User>(User.class, em);
		User u = udao.singleResultRead(userID, User.class);
		User u1 = udao.singleResultRead(otherUserID, User.class);
		u.cancelFollow(u1);
		udao.update(u);
		return null;
	}

}
