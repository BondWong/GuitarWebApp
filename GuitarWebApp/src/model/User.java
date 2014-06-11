package model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import utils.AuthType;
import model.representation.UserRepresentation;

@Entity
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "User.getByIDandPassword",
	query = "SELECT u FROM User u WHERE u.ID = ?1"
			+ " AND u.password = ?2 AND u.active = 1") })
public class User {
	@Id
	private String ID;
	@Version
	private Integer version;
	
	private String password;
	@Enumerated(EnumType.STRING)
	private AuthType authType;
	private String avatarLink;
	private String profileImageLink;
	private String gender;
	private String nickName;
	private String lookingFor;
	private String relationship;
	private String institution;
	private String major;
	private String telnum;
	private String email;
	@Temporal(TemporalType.DATE)
	private Date birthday;
	@Embedded
	private Address address;
	
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
	@JoinTable(name="USER_OTHERUSER",
		joinColumns=@JoinColumn(name="USER_ID"),
		inverseJoinColumns=@JoinColumn(name="OTHERUSER_ID"))
	private Set<User> followees;
	@ManyToMany(mappedBy="followees",fetch=FetchType.LAZY)
	private Set<User> followers;
	
	private boolean active;
	
	public User(){}
	
	public User(String ID){
		this.ID = ID;
		authType = AuthType.MEMBER;
		imageLinks = new LinkedHashSet<String>();
		active = true;
		posts = new LinkedHashSet<Post>();
		collectedPosts = new LinkedHashSet<Post>();
		joinedActivities = new LinkedHashSet<Post>();
		followees = new LinkedHashSet<User>();
		followers = new LinkedHashSet<User>();
		address = new Address();
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
	
	public AuthType getAuthType() {
		return authType;
	}

	public void setAuthType(AuthType authType) {
		this.authType = authType;
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
	
	public void setRelationShip(String relationship){
		this.relationship = relationship;
	}
	
	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getTelnum() {
		return telnum;
	}

	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday(){
		return birthday;
	}
	
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
	
	public void addImageLink(String link){
		imageLinks.add(link);
	}
	
	public void removeImageLink(String link){
		imageLinks.remove(link);
	}
	
	public Set<String> getImageLinks(){
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
	
	public Address getAddress() {
		if(this.address == null)
			return new Address();
		return address;
	}

	public void setAddress(Address address) {
		if(this.address == null)
			this.address = new Address();
		this.address = address;
	}
	
	public void setCampus(String campus) {
		if(this.address == null)
			this.address = new Address();
		this.address.setCampus(campus);
	}
	
	public void setDorm(String dorm) {
		if(this.address == null)
			this.address = new Address();
		this.address.setDorm(dorm);
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
	
	public class RepresentationShortCut{
		private String nickName;
		private String gender;
		private String avatarLink;
		private String lookingFor;
		private String relationship;
		
		private Set<String> imgaeLinks;
		
		public String getNickName() {
			if(this.nickName==null)
				return "";
			return nickName;
		}
		
		public void setNickName(String nickName) {
			this.nickName = nickName;
		}
		
		public String getGender() {
			if(this.gender==null)
				return "";
			return gender;
		}
		
		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getAvatarLink() {
			if(this.avatarLink==null)
				return "";
			return avatarLink;
		}

		public void setAvatarLink(String avatarLink) {
			this.avatarLink = avatarLink;
		}

		public String getLookingFor() {
			if(this.lookingFor==null)
				return "";
			return lookingFor;
		}

		public void setLookingFor(String lookingFor) {
			this.lookingFor = lookingFor;
		}

		public String getRelationship() {
			if(this.relationship==null)
				return "";
			return relationship;
		}

		public void setRelationship(String relationship) {
			this.relationship = relationship;
		}

		public Set<String> getImgaeLinks() {
			if(this.imgaeLinks==null)
				return new LinkedHashSet<String>();
			return imgaeLinks;
		}

		public void setImgaeLinks(Set<String> imgaeLinks) {
			this.imgaeLinks = imgaeLinks;
		}
		
	}
	
	public RepresentationShortCut getRepresentationShortCut(){
		RepresentationShortCut rsc = new RepresentationShortCut();
		
		rsc.setNickName(this.getNickName());
		rsc.setGender(this.getGender());
		rsc.setLookingFor(this.getLookingFor());
		rsc.setRelationship(this.getRelationShip());
		rsc.setAvatarLink(this.getAvatarLink());
		
		return rsc;
	}
	
	public UserRepresentation getRepresentation(){
		UserRepresentation sc = new UserRepresentation();
		
		sc.setID(this.getID());
		sc.setAuthType(this.getAuthType());
		sc.setNickName(this.getNickName());
		sc.setGender(this.getGender());
		sc.setAvatarLink(this.getAvatarLink());
		sc.setLookingFor(this.getLookingFor());
		sc.setRelationship(this.getRelationShip());
		if(this.getBirthday()!=null){
			sc.setBirthday(this.getBirthday().toString());
		} else{
			sc.setBirthday(null);
		}
		
		sc.setImageLinks(this.getImageLinks());
		
		for(User followee : this.followees){
			sc.addFolloweeID(followee.getID());
		}
		
		for(User follower : this.followers){
			sc.addFollowerID(follower.getID());
		}
		
		for(Post post : collectedPosts){
			sc.addCollectedPost(post.getID());
		}
		
		sc.setCampus(this.getAddress().getCampus());
		sc.setDorm(this.getAddress().getDorm());
		sc.setEmail(this.getEmail());
		sc.setInstitution(this.getInstitution());
		sc.setMajor(this.getMajor());
		sc.setTelnum(this.getTelnum());
		
		return sc;
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
