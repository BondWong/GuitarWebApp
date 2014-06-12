package model;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class Address {
	private String campus;
	private String dorm;
	
	public Address() {
		campus = "";
		dorm = "";
	}
	
	public String getCampus() {
		return campus;
	}
	
	public void setCampus(String campus) {
		this.campus = campus;
	}
	
	public String getDorm() {
		return dorm;
	}
	
	public void setDorm(String dorm) {
		this.dorm = dorm;
	}
	
}
