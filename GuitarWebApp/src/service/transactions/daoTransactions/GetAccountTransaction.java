package service.transactions.daoTransactions;

import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Account;

public class GetAccountTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		DAO<Account> adao = new DAO<Account>(Account.class, em);
		return adao.singleResultRead(userID, Account.class);
	}

}
