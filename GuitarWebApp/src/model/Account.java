package model;


import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Access(AccessType.FIELD)
@NamedQuery(name = "Account.getBySeriesNum", 
	query = "SELECT a FROM Account a WHERE a.autoLoginSeriesNum = ?1")
public class Account {
	@Id
	private String userID;
	private String password;
	private short chance;
	private String autoLoginSeriesNum;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastAccessDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date expireDate;
	
	public Account() {
		this.chance = 5;
	}
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public short getChance() {
		return chance;
	}

	public void setChance(short chance) {
		this.chance = chance;
	}

	public boolean hasChance() {
		return this.chance>0;
	}
	
	public void resetChance() {
		this.chance = 5;
	}
	
	public String getAutoLoginSeriesNum() {
		return autoLoginSeriesNum;
	}

	public void setAutoLoginSeriesNum(String autoLoginSeriesNum) {
		this.autoLoginSeriesNum = autoLoginSeriesNum;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
	
	public boolean shouldResetChance(Date date) {
		if(this.lastAccessDate==null)
			return false;
		
		return (date.getTime() - this.getLastAccessDate().getTime())/1000/60/60/24 >= 1;
	}
	
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public boolean isExpired(Date date) {
		if(this.expireDate == null)
			return true;
		return date.after(expireDate);
	}
	
	public boolean isProtected(Date date) {
		if(hasChance())
			return false;
		if(shouldResetChance(date)) {
			resetChance();
			return !hasChance();
		} 
		return true;
	}
	
}
