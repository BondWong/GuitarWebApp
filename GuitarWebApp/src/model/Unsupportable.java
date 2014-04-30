package model;

import java.util.Collections;
import java.util.Set;

public class Unsupportable implements Supportable{
	private Set<User> supportors;
	
	@SuppressWarnings("unchecked")
	public Unsupportable(){
		supportors = Collections.EMPTY_SET;
	}
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
	public void setSupportors(Set<User> supportors) {
		this.supportors = supportors;
	}
	
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
