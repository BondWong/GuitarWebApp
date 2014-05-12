package persistence;

import javax.persistence.EntityManager;

import persistence.components.CollectionReadable;
import persistence.components.CollectionReadableImp;
import persistence.components.Creatable;
import persistence.components.CreatableImp;
import persistence.components.Deletable;
import persistence.components.NonDeletable;
import persistence.components.NonSingleResultReadable;
import persistence.components.SingleResultReadable;

public class CommunityDAOComponentFactory extends DAOComponentFactory{
	
	private EntityManager em;
	public CommunityDAOComponentFactory(EntityManager em){
		this.em = em;
	}
	
	@Override
	public Creatable createCreatable() {
		// TODO Auto-generated method stub
		return new CreatableImp(em);
	}

	@Override
	public SingleResultReadable createSingleResultReadable() {
		// TODO Auto-generated method stub
		return new NonSingleResultReadable();
	}

	@Override
	public CollectionReadable createCollectionReadable() {
		// TODO Auto-generated method stub
		return new CollectionReadableImp(em);
	}

	@Override
	public Deletable createDeletable() {
		// TODO Auto-generated method stub
		return new NonDeletable();
	}

}
