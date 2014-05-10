package components;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CollectionReadableImp implements CollectionReadable{
	private EntityManager em;
	
	public CollectionReadableImp(EntityManager em){
		this.em = em;
	}
	
	@Override
	public <T> List<T> read(String criteria, int startIndex, int pageSize, Class<T> type, Object...params) {
		// TODO Auto-generated method stub
		TypedQuery<T> query = em.createNamedQuery(criteria,  type);
		query.setParameter(1, params[0]);
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

}
