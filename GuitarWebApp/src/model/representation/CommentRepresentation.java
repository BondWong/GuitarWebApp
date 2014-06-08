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
	private String publishDate;
	@XmlElement
	private Set<UserRepresentation> supportorRepresentations;
	@XmlElement
	private UserRepresentation ownerRepresentation;
	
	public Long getID() {
		if(ID==null)
			return null;
		return ID;
	}
	
	public void setID(Long iD) {
		ID = iD;
	}

	public String getContent() {
		if(content==null)
			return null;
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CommentType getType() {
		if(type==null)
			return null;
		return type;
	}

	public void setType(CommentType type) {
		this.type = type;
	}

	public String getPublishDate() {
		if(publishDate==null)
			return null;
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public Set<UserRepresentation> getSupportorRepresentations() {
		if(supportorRepresentations==null)
			return null;
		return supportorRepresentations;
	}

	public void addSupportorShortCuts(UserRepresentation supportorRepresentation) {
		this.supportorRepresentations.add(supportorRepresentation);
	}

	public UserRepresentation getOwnerRepresentation() {
		if(ownerRepresentation==null)
			return null;
		return ownerRepresentation;
	}

	public void setOwnerShortCut(UserRepresentation ownerRepresentation) {
		this.ownerRepresentation = ownerRepresentation;
	}
	
}

