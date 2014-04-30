package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DAO<E> {
	private Creatable creatable;
	private SingleResultReadable srReadable;
	private CollectionReadable cReadable;
	private Deletable deletable;
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public DAO(){}
	
	public DAO(Class<E> type) throws Exception{
		//emf
		//em
		
		DAOComponentFactory daocf = DAOComponentFactory.createInstance(em,type);
		
		creatable = daocf.createCreatable(em);
		srReadable = daocf.createSingleResultReadable(em);
		cReadable = daocf.createCollectionReadable(em);
		deletable = daocf.createDeletable(em);
		
	}
	
	public EntityManager getEntityManager(){
		return em;
	}
	
	public void create(E e){
		begin();
		creatable.create(e);
		commit();
	}
	
	public E singleResultRead(String ID, Class<E> type){
		return srReadable.read(ID, type);
	}
	
	public List<E> collectionRead(String criteria, int startIndex, int pageSize, Class<E> type){
		return cReadable.read(criteria, startIndex, pageSize, type);
	}
	
	public void update(E e){
		begin();
		em.merge(e);
		commit();
	}
	
	public void delete(String ID, Class<E> type){
		deletable.delete(ID, type);
	}
	
	private void begin(){
		em.getTransaction().begin();
	}
	
	private void commit(){
		em.getTransaction().commit();
	}
}
