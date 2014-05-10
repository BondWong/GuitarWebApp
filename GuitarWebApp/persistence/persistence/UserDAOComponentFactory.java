package persistence;

import javax.persistence.EntityManager;

import components.CollectionReadable;
import components.Creatable;
import components.CreatableImp;
import components.Deletable;
import components.NonCollectionReadable;
import components.NonDeletable;
import components.SingleResultReadable;
import components.SingleResultReadableImp;

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
		return new NonCollectionReadable();
	}

	@Override
	public Deletable createDeletable() {
		// TODO Auto-generated method stub
		return new NonDeletable();
	}

}
