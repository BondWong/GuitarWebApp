package service.transactions.daoTransactions;

import java.util.Date;
import java.util.Map;

import javax.persistence.EntityManager;

import model.User;
import persistence.DAO;

public class UpdateUserBasicInfoTransaction extends DAOTransaction{

	@SuppressWarnings("deprecation")
	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		
		String campus = (String) params.get("campus");
		String institution = (String) params.get("institution");
		String name = (String) params.get("name");
		String major = (String) params.get("major");
		String gender = (String) params.get("gender");
		String year = (String) params.get("year");
		String month = (String) params.get("month");
		String date = (String) params.get("date");
		String telnum = (String) params.get("telnum");
		String email = (String) params.get("email");
		
		DAO<User> udao = new DAO<User>(User.class, em);
		User user = udao.singleResultRead(userID, User.class);
		user.setCampus(campus);
		user.setInstitution(institution);
		user.setNickName(name);
		user.setMajor(major);
		user.setGender(gender);
		user.setTelnum(telnum);
		user.setEmail(email);
		Date birthday = new Date(Integer.parseInt(year)-1900, Integer.parseInt(month)-1, Integer.parseInt(date));
		user.setBirthday(birthday);
		udao.update(user);
		return null;
	}

}
