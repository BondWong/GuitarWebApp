package model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import utils.PostType;

@Entity
@Access(AccessType.FIELD)
@NamedQuery(name="Community.fetchAll", query="SELECT c FROM Community c")
@XmlRootElement
public class Community {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long ID;
	@Version
	private Integer version;
	@XmlElement
	private String name;
	@Enumerated(EnumType.STRING)
	@XmlElement
	private PostType postType;
	
	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public PostType getPostType() {
		return postType;
	}

	public void setPostType(PostType postType) {
		this.postType = postType;
	}
	
	
}
