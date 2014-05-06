package services;

import utils.CommentType;

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
