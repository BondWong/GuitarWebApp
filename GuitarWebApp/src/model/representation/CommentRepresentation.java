package model.representation;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import utils.CommentType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class CommentRepresentation{
	@XmlElement
	private Long ID;
	@XmlElement
	private String content;
	@XmlElement
	private CommentType type;
	@XmlElement
	private Set<UserRepresentation> supportorRepresentations;
	@XmlElement
	private UserRepresentation ownerRepresentation;
	
	public Long getID() {
		return ID;
	}
	
	public void setID(Long iD) {
		ID = iD;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CommentType getType() {
		return type;
	}

	public void setType(CommentType type) {
		this.type = type;
	}

	public Set<UserRepresentation> getSupportorRepresentations() {
		return supportorRepresentations;
	}

	public void addSupportorShortCuts(UserRepresentation supportorRepresentation) {
		this.supportorRepresentations.add(supportorRepresentation);
	}

	public UserRepresentation getOwnerRepresentation() {
		return ownerRepresentation;
	}

	public void setOwnerShortCut(UserRepresentation ownerRepresentation) {
		this.ownerRepresentation = ownerRepresentation;
	}
	
}

