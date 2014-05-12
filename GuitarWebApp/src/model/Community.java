package model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import utils.PostType;

@Entity
@Access(AccessType.FIELD)
@NamedQuery(name = "Community.fetchAll", query = "SELECT c FROM Community c")
public class Community {
	@Id
	private String name;
	@Enumerated(EnumType.STRING)
	private PostType type;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public PostType getType() {
		return type;
	}
	
	public void setType(PostType type) {
		this.type = type;
	}
	
}
