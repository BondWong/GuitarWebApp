package persistence;

import javax.persistence.EntityManager;

import persistence.components.CollectionReadable;
import persistence.components.CollectionReadableImp;
import persistence.components.Creatable;
import persistence.components.CreatableImp;
import persistence.components.Deletable;
import persistence.components.NonDeletable;
import persistence.components.SingleResultReadable;
import persistence.components.SingleResultReadableImp;

public class UserDAOComponentFactory extends DAOComponentFactory{
	private EntityManager em;
	
	public UserDAOComponentFactory(EntityManager em){
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
		return new SingleResultReadableImp(em);
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
