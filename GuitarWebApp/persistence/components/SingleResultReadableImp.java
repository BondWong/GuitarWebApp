package components;

import javax.persistence.EntityManager;

public class SingleResultReadableImp implements SingleResultReadable{
	private EntityManager em;
	
	public SingleResultReadableImp(EntityManager em){
		this.em = em;
	}
	
	@Override
	public <T> T read(String ID, Class<T> type) {
		// TODO Auto-generated method stub
		return em.find(type, ID);
	}

	@Override
	public <T> T read(Long ID, Class<T> type) {
		// TODO Auto-generated method stub
		return em.find(type, ID);
	}

}
