package persistence;

import java.util.List;

import javax.persistence.EntityManager;

import persistence.components.CollectionReadable;
import persistence.components.Creatable;
import persistence.components.Deletable;
import persistence.components.SingleResultReadable;

public class DAO<E> {
	private Creatable creatable;
	private SingleResultReadable srReadable;
	private CollectionReadable cReadable;
	private Deletable deletable;
	private EntityManager em;
	
	public DAO(Class<E> type, EntityManager em) throws Exception{
		this.em = em;
		DAOComponentFactory daocf = DAOComponentFactory.createInstance(em,type);
		
		creatable = daocf.createCreatable();
		srReadable = daocf.createSingleResultReadable();
		cReadable = daocf.createCollectionReadable();
		deletable = daocf.createDeletable();
		
	}
	
	public void create(E e){
		creatable.create(e);
	}
	
	public E singleResultRead(String ID, Class<E> type){
		return srReadable.read(ID, type);
	}
	
	public E singleResultRead(Long ID, Class<E> type){
		return srReadable.read(ID, type);
	}
	
	public List<E> collectionRead(String queryName, Class<E> type){
		return cReadable.read(queryName, 0, 10, type);
	}
	
	public List<E> collectionRead(String queryName, int startIndex, int pageSize, Class<E> type, Object...params){
		return cReadable.read(queryName, startIndex, pageSize, type, params);
	}
	
	public List<E> collectionRead(String queryName, Class<E> type, Object...params){
		return collectionRead(queryName, 0, 10, type, params);
	}
	
	public void update(E e){
		em.merge(e);
	}
	
	public void delete(String ID, Class<E> type){
		deletable.delete(ID, type);
	}
	
}
