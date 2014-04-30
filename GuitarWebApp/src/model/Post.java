package model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import utils.PostType;

@Entity
@Access(AccessType.FIELD)
public class Post {
	@Id @GeneratedValue(strategy=GenerationType.TABLE)
	private Long ID;
	@Version
	private Integer version;
	
	private String topic;
	private String content;
	@ElementCollection
	private Set<String> mediaLocation;
	private PostType type;
	@Temporal(TemporalType.DATE)
	private Date publishDate;
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.MERGE,
			orphanRemoval=true)
	private Set<Comment> comments;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OWNER_ID")
	private User owner;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LIKER_ID")
	private Set<User> likers;
	
	@Transient
	private boolean active;
	@Transient
	private Joinable joinable;
	
	public Post(){
		this.joinable = new Unjoinable();
	}
	
	public Post(String topic, String content, Set<String> list){
		this.topic = topic;
		this.content = content;
		this.mediaLocation = list;
		this.publishDate = new Date();
		
		this.likers = new LinkedHashSet<User>();
		comments = new LinkedHashSet<Comment>();
		
		this.active = true;
	}
	
	public Long getID(){
		return ID;
	}
	
	public String getTopic(){
		return topic;
	}
	
	public String getContent(){
		return content;
	}
	
	public Set<String> getMediaLocation(){
		return mediaLocation;
	}
	@Access(AccessType.PROPERTY)
	@Enumerated(EnumType.STRING)
	public String getType(){
		return type.toString();
	}
	
	public void setType(PostType type){
		this.type = type;
		if(this.type.equals(PostType.ACTIVITY))
			this.joinable = new JoinableImp();
	}
	
	public String getPusblishDate(){
		return publishDate.toString();
	}
	
	public void clickLike(User user){
		likers.add(user);
	}
	
	public void cancelLike(User user){
		likers.remove(user);
	}
	
	public int getLikeNum(){
		return likers.size();
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public void addComment(Comment comment){
		this.comments.add(comment);
	}
	
	public void removeComment(Comment comment){
		this.comments.remove(comment);
		comment.delete();
	}
	
	public Set<Comment> getComments(){
		return comments;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public void delete(){
		active = false;
	}
	
	public void setJoinable(Joinable joinable){
		this.joinable = joinable;
	}
	
	public boolean isJoinable(){
		return joinable.isJoinable();
	}
	
	@Access(AccessType.PROPERTY)
	@Temporal(TemporalType.TIMESTAMP)
	public String getStartDate(){
		return joinable.getStartDate();
	}
	
	public void setStartDate(Date date){
		joinable.setStartDate(date);
	}
	
	public void addParticipant(User participant){
		joinable.addParticipant(participant);
	}
	
	@Access(AccessType.PROPERTY)
	@OneToMany(cascade=CascadeType.MERGE)
	@JoinTable(name="POSTS_PARTICIPANTS",
		joinColumns=@JoinColumn(name="POST_ID"),
		inverseJoinColumns=@JoinColumn(name="PARTICIPANT_ID"))
	public Set<User> getParticipants(){
		return joinable.getParticipants();
	}
	
	public void setParticipants(Set<User> participants){
		this.joinable.setParticipants(participants);
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Post){
			Post other = (Post) o;
			if(other.getID()!=null&&this.ID!=null){
				return other.getID().equals(this.ID);
			} else if(other.getID()==null&&this.ID==null){
				return super.equals(other);
			} else{
				return false;
			}
		}
		
		return false;
	}
	
	@Override
	public int hashCode(){
		if(this.ID==null){
			return super.hashCode();
		} else{
			return this.ID.hashCode();
		}
	}
	
}
