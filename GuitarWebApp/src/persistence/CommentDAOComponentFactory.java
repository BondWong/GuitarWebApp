package persistence;

import javax.persistence.EntityManager;

public class CommentDAOComponentFactory extends DAOComponentFactory{
	@SuppressWarnings("unused")
	private EntityManager em;
	
	public CommentDAOComponentFactory(EntityManager em){
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
		return new NonCollectionReadable();
	}

	@Override
	public Deletable createDeletable(EntityManager em) {
		// TODO Auto-generated method stub
		return new NonDeletable();
	}

}
