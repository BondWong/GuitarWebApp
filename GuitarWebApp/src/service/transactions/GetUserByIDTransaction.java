package service.transactions;

import java.util.Map;

import javax.persistence.EntityManager;

import model.User;
import persistence.DAO;

public class GetUserByIDTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		DAO<User> udao = new DAO<User>(User.class, em);
		
		User user = null;
		user = udao.singleResultRead(userID, User.class);
		
		return user;
	}

}
