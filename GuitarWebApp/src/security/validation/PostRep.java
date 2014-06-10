package security.validation;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PostRep {
	@NotNull(groups={NonActivityGroup.class,ActivityGroup.class})
	@NotEmpty(groups={NonActivityGroup.class,ActivityGroup.class})
	@Length(max=50,groups={NonActivityGroup.class,ActivityGroup.class})
	@SafeHtml(groups={NonActivityGroup.class,ActivityGroup.class})
	private String topic;
	@NotNull(groups={NonActivityGroup.class,ActivityGroup.class})
	@NotEmpty(groups={NonActivityGroup.class,ActivityGroup.class})
	@Length(max=2000,groups={NonActivityGroup.class,ActivityGroup.class})
	@SafeHtml(groups={NonActivityGroup.class,ActivityGroup.class})
	private String content;
	@NotNull(groups={NonActivityGroup.class,ActivityGroup.class})
	@Size(min=0, max=9,groups={NonActivityGroup.class,ActivityGroup.class})
	private Set<String> mediaLocation;
	@NotNull(groups={NonActivityGroup.class,ActivityGroup.class})
	@Pattern(regexp = "QUESTION|ACTIVITY|DISCUESSION",groups={NonActivityGroup.class,ActivityGroup.class})
	private String postType;
	@NotNull(groups={NonActivityGroup.class,ActivityGroup.class})
	@Past(groups={NonActivityGroup.class,ActivityGroup.class})
	private Date publishDate;
	@NotNull(groups=ActivityGroup.class)
	@Future(groups=ActivityGroup.class)
	private Date startDate;
	private String information;

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

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
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

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	
}
