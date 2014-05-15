package service.transactions.daoTransactions;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.User;

public class GetUsersByIDsTransaction extends DAOTransaction{

	@SuppressWarnings("unchecked")
	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> IDs = (List<String>) params.get("userIDs");
		
		String query = "SELECT u FROM User u WHERE u.ID IN ";
		query += "(";
		Iterator<String> iter = IDs.iterator();
		while(iter.hasNext()){
			query += iter.next();
			if(iter.hasNext())
				query += ",";
		}
		query += ")";
		
		DAO<User> udao = new DAO<User>(User.class, em);
		return udao.collectionDynamicRead(query, 0, IDs.size(), User.class);
	}

}
