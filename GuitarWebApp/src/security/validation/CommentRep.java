package security.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CommentRep {
	@NotNull
	@NotEmpty
	@Length(max=1000)
	@SafeHtml
	private String content;
	@NotNull
	@Pattern(regexp = "COMMENT|ANSWER")
	private String commentType;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String  commentType) {
		this.commentType = commentType;
	}
	
}
