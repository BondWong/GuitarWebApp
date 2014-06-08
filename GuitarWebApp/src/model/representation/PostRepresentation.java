package model.representation;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.Comment;
import model.User;
import utils.PostType;

@XmlRootElement
	@XmlAccessorType(XmlAccessType.NONE)
	public class PostRepresentation{
		@XmlElement
		private Long ID;
		@XmlElement
		private Set<String>mediaLocation;
		@XmlElement
		private String topic;
		@XmlElement
		private String content;
		@XmlElement
		private PostType type;
		@XmlElement
		private String publishDate;
		@XmlElement
		private boolean active;
		
		@XmlElement
		private Set<CommentRepresentation> commentRepresentations;
		@XmlElement
		private UserRepresentation ownerRepresentation;
		@XmlElement
		private Set<UserRepresentation> likerRepresentations;
		@XmlElement
		private String startDate;
		@XmlElement
		private Set<UserRepresentation> participantRepresentations;
		
		public Long getID() {
			if(ID==null)
				return null;
			return ID;
		}
		
		public void setID(Long iD) {
			ID = iD;
		}

		public Set<String> getMediaLocation() {
			if(mediaLocation==null)
				return null;
			return mediaLocation;
		}

		public void setMediaLocation(Set<String> mediaLocation) {
			this.mediaLocation = mediaLocation;
		}

		public String getTopic() {
			if(topic==null)
				return null;
			return topic;
		}

		public void setTopic(String topic) {
			this.topic = topic;
		}

		public String getContent() {
			if(content==null)
				return null;
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public PostType getType() {
			if(type==null)
				return null;
			return type;
		}

		public void setType(PostType type) {
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
		
		public boolean isActive(){
			return active;
		}
		
		public void setActive(boolean active){
			this.active = active;
		}
		
		public Set<CommentRepresentation> getCommentRepresentations() {
			if(commentRepresentations==null)
				return null;
			return commentRepresentations;
		}

		public void addCommentRepresentations(Set<Comment> comments){
			if(this.commentRepresentations==null){
				this.commentRepresentations = new LinkedHashSet<CommentRepresentation>();
			}
			for(Comment comment : comments){
				this.commentRepresentations.add(comment.getRepresentation());
			}
		}

		public UserRepresentation getOwnerRepresentation() {
			if(ownerRepresentation==null)
				return null;
			return ownerRepresentation;
		}

		public void setOwner(UserRepresentation ownerRepresentation) {
			this.ownerRepresentation = ownerRepresentation;
		}

		public Set<UserRepresentation> getLikerRepresentations() {
			if(likerRepresentations==null)
				return null;
			return likerRepresentations;
		}

		public void addLikerRepresentation(Set<User> likers) {
			if(this.likerRepresentations == null){
				this.likerRepresentations = new LinkedHashSet<UserRepresentation>();
			}
			for(User liker : likers){
				this.likerRepresentations.add(liker.getRepresentation());
			}
		}

		public String getStartDate() {
			if(startDate==null)
				return null;
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public Set<UserRepresentation> getParticipantRepresentations() {
			if(participantRepresentations==null)
				return null;
			return participantRepresentations;
		}

		public void addParticipantRepresentations(Set<User> participants) {
			if(this.participantRepresentations == null){
				this.participantRepresentations = new LinkedHashSet<UserRepresentation>();
			}
			for(User participant : participants){
				this.participantRepresentations.add(participant.getRepresentation());
			}
		}
		
	}
