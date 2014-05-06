package services;

import java.util.Date;
import java.util.Set;

import utils.PostType;

public class PostRep {
	private String topic;
	private String content;
	private Set<String> mediaLocation;
	private PostType type;
	private Date publishDate;
	private Date startDate;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<String> getMediaLocation() {
		return mediaLocation;
	}

	public void setMediaLocation(Set<String> mediaLocation) {
		this.mediaLocation = mediaLocation;
	}

	public PostType getType() {
		return type;
	}

	public void setType(PostType type) {
		this.type = type;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
}
