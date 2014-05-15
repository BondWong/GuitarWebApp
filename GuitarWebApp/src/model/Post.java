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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import utils.PostType;

@Entity
@Access(AccessType.FIELD)
@NamedQueries({@NamedQuery(name="Post.fetchByUserID",query="SELECT p FROM Post p "
		+ "WHERE p.owner.ID = ?1 ORDER BY p.ID DESC"),
	@NamedQuery(name="Post.fetchByType",query="SELECT p FROM Post p "
			+ "WHERE p.type = ?1 ORDER BY p.ID DESC"),
	@NamedQuery(name="Post.fetchByFollowee",query="SELECT p FROM Post p "
			+ "WHERE p.owner IN(SELECT uf FROM User u JOIN u.followees uf WHERE "
			+ "u.ID = ?1 ) ORDER BY p.ID DESC")})
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Post {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	@XmlElement
	private Long ID;
	@Version
	private Integer version;
	
	@XmlElement
	private String topic;
	@XmlElement
	private String content;
	@ElementCollection
	@XmlElement
	private Set<String> mediaLocation;
	@Transient
	@XmlElement
	private PostType type;
	@Temporal(TemporalType.TIMESTAMP)
	@XmlElement
	private Date publishDate;
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.MERGE,
			orphanRemoval=true)
	@XmlElement
	private Set<Comment> comments;
	@ManyToOne
	@JoinColumn(name="OWNER_ID")
	@XmlElement
	private User owner;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name="POST_LIKERS",
		joinColumns=@JoinColumn(name="POST_ID"),
		inverseJoinColumns=@JoinColumn(name="LIKER_ID"))
	@XmlElement
	private Set<User> likers;
	
	@XmlElement
	private boolean active;
	@Transient
	@XmlTransient
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
	public PostType getType(){
		return type;
	}
	
	public void setType(PostType type){
		this.type = type;
		if(type.equals(PostType.ACTIVITY)){
			this.joinable = new JoinableImp();
		}
		else{
			this.joinable = new Unjoinable();
		}
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
	
	public Set<User> getLikers(){
		return likers;
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
	@XmlElement
	public Date getStartDate(){
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
	@XmlElement
	public Set<User> getParticipants(){
		return joinable.getParticipants();
	}
	
	public void setParticipants(Set<User> participants){
		this.joinable.setParticipants(participants);
	}
	
	public int getParticipantsNum(){
		return getParticipants().size();
	}
	
	public class ShortCut{
		private Long ID;
		private Set<String>mediaLocation;
		private String topic;
		private String content;
		private PostType type;
		private String publishDate;
		
		private int likeNum;
		private int participentsNum;
		
		private String ownerAvatarLink;
		private String ownerNickName;
		
		public Long getID() {
			return ID;
		}
		
		public void setID(Long iD) {
			ID = iD;
		}

		public Set<String> getMediaLocation() {
			return mediaLocation;
		}

		public void setMediaLocation(Set<String> mediaLocation) {
			this.mediaLocation = mediaLocation;
		}

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
		
		public PostType getType() {
			return type;
		}
		
		public void setType(PostType type) {
			this.type = type;
		}
		

		public String getPublishDate() {
			return publishDate;
		}

		public void setPublishDate(String publishDate) {
			this.publishDate = publishDate;
		}

		public int getLikeNum() {
			return likeNum;
		}
		
		public void setLikeNum(int likeNum) {
			this.likeNum = likeNum;
		}
		
		public int getParticipentsNum() {
			return participentsNum;
		}
		
		public void setParticipentsNum(int participentsNum) {
			this.participentsNum = participentsNum;
		}

		public String getOwnerAvatarLink() {
			return ownerAvatarLink;
		}

		public void setOwnerAvatarLink(String ownerAvatarLink) {
			this.ownerAvatarLink = ownerAvatarLink;
		}

		public String getOwnerNickName() {
			return ownerNickName;
		}

		public void setOwnerNickName(String ownerNickName) {
			this.ownerNickName = ownerNickName;
		}
		
	}
	
	public ShortCut getShortCut(){
		ShortCut psc = new ShortCut();
		
		psc.setID(this.getID());
		psc.setMediaLocation(this.getMediaLocation());
		psc.setContent(this.getContent());
		psc.setTopic(this.getTopic());
		psc.setType(this.getType());
		psc.setLikeNum(this.getLikeNum());
		psc.setParticipentsNum(this.getParticipantsNum());
		psc.setPublishDate(this.getPusblishDate());
		psc.setOwnerNickName(/*this.getOwner().getNickName()*/"fucker");
		psc.setOwnerAvatarLink(/*this.getOwner().getAvatarLink()*/"xxx.xxx.x");
		
		return psc;
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
