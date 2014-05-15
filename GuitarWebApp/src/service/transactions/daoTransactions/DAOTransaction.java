package service.transactions.daoTransactions;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OptimisticLockException;

import service.transactions.Transaction;
import utils.EntityManagerFactoryCreator;

public abstract class DAOTransaction implements Transaction{
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public final Object execute(Map<String, Object> params) throws Exception{
		initEntityManagerFactory();
		initEntityManager();
		
		try{
			return execute(em, params);
		} catch(OptimisticLockException ole){
			return execute(em, params);
		} catch(NullPointerException npe){
			throw npe;
		} catch(Exception e){
			throw e;
		}
	}
	
	private void initEntityManagerFactory(){
		emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
	}
	
	private void initEntityManager(){
		em = emf.createEntityManager();
	}
	
	private void beginTransaction(){
		em.getTransaction().begin();
	}
	
	protected abstract Object process(EntityManager em, Map<String,Object> params) throws Exception;
	
	private void commitTransaction(){
		em.getTransaction().commit();
	}
	
	private Object execute(EntityManager em, Map<String, Object> params) throws Exception{
		beginTransaction();
		Object result = process(em, params);
		commitTransaction();
		
		return result;
	}
	
}
