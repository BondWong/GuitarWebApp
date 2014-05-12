package service.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Community;

public class GetCommunitiesTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		DAO<Community> cdao = new DAO<Community>(Community.class, em);
		List<Community> communities = new ArrayList<Community>();
		
		communities = cdao.collectionRead("Community.fetchAll", Community.class);
		
		return communities;
	}

}
