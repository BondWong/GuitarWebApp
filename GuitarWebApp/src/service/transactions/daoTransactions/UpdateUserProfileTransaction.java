package service.transactions.daoTransactions;

import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.User;

public class UpdateUserProfileTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		
		String nickName = (String) params.get("nickName");
		String lookingFor = (String) params.get("lookingFor");
		String relationship = (String) params.get("relationship");
		String telnum = (String) params.get("telnum");
		String email = (String) params.get("email");
		String dorm = (String) params.get("dorm");
		
		DAO<User> udao = new DAO<User>(User.class, em);
		User user = udao.singleResultRead(userID, User.class);
		user.setLookingFor(lookingFor);
		user.setNickName(nickName);
		user.setRelationShip(relationship);
		user.setTelnum(telnum);
		user.setEmail(email);
		user.setDorm(dorm);
		udao.update(user);
		
		return null;
	}

}
