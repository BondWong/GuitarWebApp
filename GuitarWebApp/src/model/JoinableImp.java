package model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class JoinableImp extends Joinable{	
	public JoinableImp(){
		participants = new LinkedHashSet<User>();
		startDate = new Date();
	}
	
	@Override
	public void addParticipant(User participant) {
		// TODO Auto-generated method stub
		participants.add(participant);
	}

	@Override
	public Set<User> getParticipants() {
		// TODO Auto-generated method stub
		return participants;
	}
	
	@Override
	public void setParticipants(Set<User> participants){
		this.participants = participants;
	}
	
	@Override
	public boolean isJoinable() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	protected boolean isTime(Date date) {
		// TODO Auto-generated method stub
		long currentTime = date.getTime();
		long startTime = this.startDate.getTime();
		
		if(startTime - currentTime <=REMINDINGTIME){
			return true;
		} else{
			return false;
		}
	}
	
}
