package persistence;

import javax.persistence.EntityManager;

public class UserDAOComponentFactory extends DAOComponentFactory{
	@SuppressWarnings("unused")
	private EntityManager em;
	
	public UserDAOComponentFactory(EntityManager em){
		this.em = em;
	}
	@Override
	public Creatable createCreatable(EntityManager em) {
		// TODO Auto-generated method stub
		return new CreatableImp(em);
	}

	@Override
	public SingleResultReadable createSingleResultReadable(EntityManager em) {
		// TODO Auto-generated method stub
		return new SingleResultReadableImp(em);
	}

	@Override
	public CollectionReadable createCollectionReadable(EntityManager em) {
		// TODO Auto-generated method stub
		return new NonCollectionReadable();
	}

	@Override
	public Deletable createDeletable(EntityManager em) {
		// TODO Auto-generated method stub
		return new NonDeletable();
	}

}
