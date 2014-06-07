package service.transactions.daoTransactions;

import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.User;

public class RegisterTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		String password = (String) params.get("password");
		
		User user = new User(userID);
		user.setPassword(password);
		DAO<User> udao = new DAO<User>(User.class, em);
		udao.create(user);
		
		return null;
	}

}
