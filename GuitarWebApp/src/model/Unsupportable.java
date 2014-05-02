package model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Unsupportable implements Supportable{
	private Set<User> supportors = new LinkedHashSet<User>();
	
	public Unsupportable(){}
	@Override
	public void clickSupport(User user) {
		// TODO Auto-generated method stub
	}

	@Override
	public void cancelSupport(User user) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getSupportNum() {
		// TODO Auto-generated method stub
		return this.supportors.size();
	}
	
	@Override
	public void setSupportors(Set<User> supportors) {}
	
	@Override
	public Set<User> getSupportors() {
		// TODO Auto-generated method stub
		return supportors;
	}
	
	@Override
	public boolean isSupportable() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		supportors.clear();
	}
	
}
