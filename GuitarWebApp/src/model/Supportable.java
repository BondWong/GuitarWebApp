package model;

import java.util.Set;

public interface Supportable {
	public void clickSupport(User user);
	
	public void cancelSupport(User user);
	
	public int getSupportNum();
	
	public Set<User> getSupportors();
	
	public void setSupportors(Set<User> supportors);
	
	public boolean isSupportable();
	
	public void delete();
}
