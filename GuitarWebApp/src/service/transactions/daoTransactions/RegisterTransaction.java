package service.transactions.daoTransactions;

import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import utils.MD5;
import model.Account;
import model.User;

public class RegisterTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		String password = (String) params.get("password");
		password = MD5.toMD5Code(password);
		
		User user = new User(userID);
		user.setPassword(password);
		Account account = new Account();
		account.setUserID(userID);
		account.setPassword(password);
		
		DAO<User> udao = new DAO<User>(User.class, em);
		udao.create(user);
		
		DAO<Account> adao = new DAO<Account>(Account.class, em);
		adao.create(account);
		
		return null;
	}

}
