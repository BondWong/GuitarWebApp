package service.transactions.daoTransactions;

import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Account;

public class UpdateAccountTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		Account a = (Account) params.get("account");
		DAO<Account> adao = new DAO<Account>(Account.class, em);
		adao.update(a);
		return null;
	}
	
}
