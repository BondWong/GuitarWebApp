package persistence;

import javax.persistence.EntityManager;

public class PostDAOComponentFactory extends DAOComponentFactory{
	@SuppressWarnings("unused")
	private EntityManager em;
	public PostDAOComponentFactory(EntityManager em){
		this.em = em;
	}
	
	@Override
	public Creatable createCreatable(EntityManager em) {
		// TODO Auto-generated method stub
		return new NonCreatable();
	}

	@Override
	public SingleResultReadable createSingleResultReadable(EntityManager em) {
		// TODO Auto-generated method stub
		return new SingleResultReadableImp(em);
	}

	@Override
	public CollectionReadable createCollectionReadable(EntityManager em) {
		// TODO Auto-generated method stub
		return new CollectionReadableImp(em);
	}

	@Override
	public Deletable createDeletable(EntityManager em) {
		// TODO Auto-generated method stub
		return new NonDeletable();
	}
	
}
