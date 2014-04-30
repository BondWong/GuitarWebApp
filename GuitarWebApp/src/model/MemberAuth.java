package model;

import java.util.ArrayList;
import java.util.List;

public class MemberAuth implements Authority{
	private List<String> auths;
	
	public MemberAuth(){
		auths = new ArrayList<String>();
	}
	
	@Override
	public List<String> getAuthority() {
		// TODO Auto-generated method stub
		return auths;
	}

	@Override
	public void setAuthority(List<String> auths) {
		// TODO Auto-generated method stub
		this.auths = auths;
	}

}
