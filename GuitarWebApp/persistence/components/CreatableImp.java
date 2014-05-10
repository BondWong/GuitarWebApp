package components;

import javax.persistence.EntityManager;

public class CreatableImp implements Creatable{
	private EntityManager em;
	
	public CreatableImp(EntityManager em){
		this.em = em;
	}
	
	@Override
	public <T> void create(T t) {
		// TODO Auto-generated method stub
		em.persist(t);
	}

}
