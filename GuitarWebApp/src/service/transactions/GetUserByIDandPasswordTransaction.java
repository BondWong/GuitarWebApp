package service.transactions;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.User;

public class GetUserByIDandPasswordTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		String password = (String) params.get("password");
		
		DAO<User> udao = new DAO<User>(User.class, em);
		List<User> users = (List<User>) udao.collectionRead("User.getByIDandPassword",
				0, 1, User.class, userID, password);
		
		User user = null;
		if(users.iterator().hasNext())
			user = users.iterator().next();
		return user;
	}

}
