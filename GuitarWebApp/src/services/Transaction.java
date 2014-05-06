package services;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import utils.EntityManagerFactoryCreator;

public abstract class Transaction {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public final Object execute(Map<String, Object> params) throws Exception{
		initEntityManagerFactory();
		initEntityManager();
		
		beginTransaction();
		Object result = process(em, params);
		commitTransaction();
		
		return result;
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
	
}
