package persistence.components;

import javax.persistence.EntityManager;

public class DeletableImp implements Deletable{
	private EntityManager em;
	
	public DeletableImp(EntityManager em){
		this.em = em;
	}
	
	@Override
	public <T> void delete(String ID, Class<T> type) {
		// TODO Auto-generated method stub
		em.remove(em.find(type, ID));
	}

}
