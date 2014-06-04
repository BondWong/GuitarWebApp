package service.transactions.daoTransactions;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.User;

public class AddImagesTransaction extends DAOTransaction{

	@SuppressWarnings("unchecked")
	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		List<String> imageLinks = (List<String>) params.get("imageLinks");
		
		User user = null;
		DAO<User> udao = new DAO<User>(User.class, em);
		user = udao.singleResultRead(userID, User.class);
		for(String imageLink : imageLinks){
			user.addImageLink(imageLink);
		}
		udao.update(user);
		return null;
	}

}
