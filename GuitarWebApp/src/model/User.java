package model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Access(AccessType.FIELD)
public class User {
	@Id
	private String ID;
	@Version
	private Integer version;
	
	private String password;
	private String avatarLink;
	private String profileImageLink;
	private String gender;
	private String nickName;
	private String lookingFor;
	private String relationship;
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@Transient
	private Authority auth;
	
	@ElementCollection
	private Set<String> imageLinks;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.MERGE
			,mappedBy="owner")
	private Set<Post> posts;
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.MERGE)
	@JoinTable(name="USER_COLLECTEDPOSTS",
		joinColumns=@JoinColumn(name="USER_ID"),
		inverseJoinColumns=@JoinColumn(name="COLLECTEDPOST_ID"))
	private Set<Post> collectedPosts;
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.MERGE)
	@JoinTable(name="USER_JOINEDACTIVITIES",
		joinColumns=@JoinColumn(name="USER_ID"),
		inverseJoinColumns=@JoinColumn(name="JOINEDACTIVITY_ID"))
	private Set<Post> joinedActivities;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="FOLLOWEES_FOLLOWERS",
		joinColumns=@JoinColumn(name="FOLLOWEE_ID"),
		inverseJoinColumns=@JoinColumn(name="FOLLOWER_ID"))
	private Set<User> followees;
	@ManyToMany(mappedBy="followees",fetch=FetchType.LAZY)
	private Set<User> followers;
	
	private boolean active;
	
	public User(){
		auth = new MemberAuth();
	}
	
	public User(String ID){
		this.ID = ID;
		auth = new MemberAuth();
		imageLinks = new LinkedHashSet<String>();
		active = true;
		posts = new LinkedHashSet<Post>();
		collectedPosts = new LinkedHashSet<Post>();
		joinedActivities = new LinkedHashSet<Post>();
		followees = new LinkedHashSet<User>();
		followers = new LinkedHashSet<User>();
	}
	
	public String getID(){
		return ID;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setAvatarLink(String avatarLink){
		this.avatarLink = avatarLink;
	}
	
	public String getAvatarLink(){
		return avatarLink;
	}
	
	public void setProfileImageLink(String profileImageLink){
		this.profileImageLink = profileImageLink;
	}
	
	public String getProfileImageLink(){
		return profileImageLink;
	}
	
	public String getNickName(){
		if(nickName == null)
			return ID;
		
		return nickName;
	}
	
	public void setNickName(String nickName){
		this.nickName = nickName;
	}
	
	public String getGender(){
		return gender;
	}
	
	public void setGender(String gender){
		this.gender = gender;
	}
	
	public String getLookingFor(){
		return lookingFor;
	}
	
	public void setLookingFor(String lookingFor){
		this.lookingFor = lookingFor;
	}
	
	public String getRelationShip(){
		return relationship;
	}
	
	public void setRealationShip(String relationship){
		this.relationship = relationship;
	}
	
	public Date getBirthday(){
		return birthday;
	}
	
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
	
	public List<String> getAuthority(){
		return auth.getAuthority();
	}
	
	@Access(AccessType.PROPERTY)
	public String getAuthType(){
		return auth.getClass().getName();
	}
	
	public void setAuthType(String authType){
		if(authType.equals(MemberAuth.class.getName())){
			auth = new MemberAuth();
		} else if(authType.equals(PostOwnerAuth.class.getName())){
			auth = new PostOwnerAuth();
		} else{
			auth = new GodAuth();
		}
	}
	
	public void updateToPostOwner(){
		auth = new PostOwnerAuth();
	}
	
	public void updateToGod(){
		auth = new GodAuth();
	}
	
	public void addImageLink(String link){
		imageLinks.add(link);
	}
	
	public Set<String> getImageLins(){
		return imageLinks;
	}
	
	public void addPost(Post post){
		post.setOwner(this);
		posts.add(post);
	}
	
	public void deletePost(Post post){
		post.setOwner(null);
		posts.remove(post);
		post.delete();
	}
	
	public Set<Post> getPosts(){
		return posts;
	}
	
	public void addComment(Post post, Comment comment){
		comment.setOwner(this);
		post.addComment(comment);
	}
	
	public void deleteComment(Post post, Comment comment){
		comment.setOwner(null);
		post.removeComment(comment);
	}
	
	public void supportComment(Comment comment){
		comment.support(this);
	}
	
	public void cancelSupport(Comment comment){
		comment.cancelSupport(this);
	}
	
	public void likePost(Post post){
		post.clickLike(this);
	}
	
	public void cancelLike(Post post){
		post.cancelLike(this);
	}
	
	public void collectPost(Post post){
		collectedPosts.add(post);
	}
	
	public void cancelCollect(Post post){
		collectedPosts.remove(post);
	}
	
	public Set<Post> getCollectedPosts(){
		return collectedPosts;
	}
	
	public void joinActivity(Post activity){
		joinedActivities.add(activity);
		activity.addParticipant(this);
	}
	
	public Set<Post> getJoinedActivities(){
		return joinedActivities;
	}
	
	public void follow(User user){
		this.followees.add(user);
		user.followedBy(this);
	}
	
	public void followedBy(User user){
		this.followers.add(user);
	}
	
	public void cancelFollow(User user){
		this.followees.remove(user);
		user.cancelFollowed(this);
	}
	
	public void cancelFollowed(User user){
		this.followers.remove(user);
	}
	
	public Set<User> getFollowees(){
		return this.followees;
	}
	
	public Set<User> getFollowers(){
		return this.followers;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public void forbid(){
		active = false;
		for(Post p : posts){
			p.delete();
		}
	}
	
	@Override
	public boolean equals(Object object){
		if(object instanceof User){
			User other = (User) object;
			if(other.getID().equals(this.ID)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return ID.hashCode();
	}
	
}
