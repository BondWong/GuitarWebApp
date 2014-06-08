package service.transactions.daoTransactions;

import java.util.Map;

import javax.persistence.EntityManager;

import model.Account;
import persistence.DAO;

public class GetAccountBySeriesNumTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String seriesNum = (String) params.get("autoLoginSeriesNum");
		DAO<Account> adao = new DAO<Account>(Account.class, em);
		return adao.collectionRead("Account.getBySeriesNum", 0, 1, Account.class, seriesNum);
	}

}
