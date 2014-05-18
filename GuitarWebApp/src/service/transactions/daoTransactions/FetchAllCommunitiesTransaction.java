package service.transactions.daoTransactions;

import java.util.Map;

import javax.persistence.EntityManager;

import model.Community;
import persistence.DAO;
import service.transactions.Transaction;

public class FetchAllCommunitiesTransaction extends DAOTransaction implements
		Transaction {

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		DAO<Community> cDAO = new DAO<Community>(Community.class, em);
		
		return cDAO.collectionRead("Community.fetchAll", Community.class);
	}

}
