package persistence;

import javax.persistence.EntityManager;

import persistence.components.CollectionReadable;
import persistence.components.CollectionReadableImp;
import persistence.components.Creatable;
import persistence.components.Deletable;
import persistence.components.NonCreatable;
import persistence.components.NonDeletable;
import persistence.components.SingleResultReadable;
import persistence.components.SingleResultReadableImp;

public class PostDAOComponentFactory extends DAOComponentFactory{
	private EntityManager em;
	public PostDAOComponentFactory(EntityManager em){
		this.em = em;
	}
	
	@Override
	public Creatable createCreatable() {
		// TODO Auto-generated method stub
		return new NonCreatable();
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
