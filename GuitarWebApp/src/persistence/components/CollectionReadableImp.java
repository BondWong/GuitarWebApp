package persistence.components;

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
		for(int i=0;params!=null&&i<params.length;i++){
			query.setParameter(i+1, params[i]);
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

}
