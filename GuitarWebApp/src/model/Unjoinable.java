package model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class Unjoinable extends Joinable{
	public Unjoinable(){
		participants = new LinkedHashSet<User>();
		startDate = new Date();
	}
	
	@Override
	public void addParticipant(User participant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<User> getParticipants() {
		// TODO Auto-generated method stub
		return participants;
	}
	
	@Override
	public void setParticipants(Set<User> participants){
		
	}
	
	@Override
	public boolean isJoinable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isTime(Date date) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
