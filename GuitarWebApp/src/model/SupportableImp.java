package model;

import java.util.LinkedHashSet;
import java.util.Set;

public class SupportableImp implements Supportable{
	private Set<User> supportors = new LinkedHashSet<User>();
	
	public SupportableImp(){}
	
	@Override
	public void clickSupport(User user) {
		// TODO Auto-generated method stub
		supportors.add(user);
	}

	@Override
	public void cancelSupport(User user) {
		// TODO Auto-generated method stub
		supportors.remove(user);
	}
	
	public int getSupportNum(){
		return this.supportors.size();
	}
	
	@Override
	public Set<User> getSupportors(){
		return this.supportors;
	}
	
	@Override 
	public void setSupportors(Set<User> supportors) {
		this.supportors = supportors;
	}
	
	@Override
	public boolean isSupportable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void delete(){
		supportors.clear();
	}
	
}
