package services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import utils.CommentType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CommentRep {
	private String content;
	private CommentType type;

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
	
}
