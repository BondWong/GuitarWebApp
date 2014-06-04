package service.transactions.daoTransactions;

import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.User;

public class ChangeAvatarTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		String avatarLink = (String) params.get("avatarLink");
		
		DAO<User> udao = new DAO<User>(User.class, em);
		User user = null;
		user = udao.singleResultRead(userID, User.class);
		user.setAvatarLink(avatarLink);
		udao.update(user);
		return null;
	}

}
