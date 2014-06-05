package service.transactions.daoTransactions;

import java.util.Date;
import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.User;

public class UpdateUserProfileTransaction extends DAOTransaction{

	@SuppressWarnings("deprecation")
	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		String nickName = (String) params.get("nickName");
		String gender = (String) params.get("gender");
		String lookingFor = (String) params.get("lookingFor");
		String relationship = (String) params.get("relationship");
		String year = (String) params.get("year");
		String month = (String) params.get("month");
		String date = (String) params.get("date");
		
		DAO<User> udao = new DAO<User>(User.class, em);
		User user = udao.singleResultRead(userID, User.class);
		user.setBirthday(new Date(Integer.parseInt(year)-1900, Integer.parseInt(month)-1, Integer.parseInt(date)));
		user.setGender(gender);
		user.setLookingFor(lookingFor);
		user.setNickName(nickName);
		user.setRelationShip(relationship);
		udao.update(user);
		return null;
	}

}
