package model;

import java.util.Date;
import java.util.Set;

public abstract class Joinable {
	protected final static long REMINDINGTIME = 30*60*1000;
	protected Set<User> participants;
	protected Date startDate;
	
	public abstract void addParticipant(User participant);
	
	public abstract Set<User> getParticipants();
	
	public abstract void setParticipants(Set<User> participants);
	
	public abstract boolean isJoinable();
	
	protected abstract boolean isTime(Date date);
	
	public Date getStartDate(){
		return startDate;
	}
	
	public void setStartDate(Date date){
		startDate = date;
	}
	
}
