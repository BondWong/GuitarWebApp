package model.representation;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class UserRepresentation{
	@XmlElement
	private String ID;
	@XmlElement
	private List<String> auth;
	@XmlElement
	private String avaterLink;
	@XmlElement
	private String gender;
	@XmlElement
	private String nickName;
	@XmlElement
	private String lookingFor;
	@XmlElement
	private String relationship;
	@XmlElement
	private String birthday;
	@XmlElement
	private Set<String> imageLinks;
	@XmlElement
	private Set<Long> collectedPosts;
	@XmlElement
	private Set<String> followeesID;
	@XmlElement
	private Set<String> followersID;
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}

	public String getAvaterLink() {
		return avaterLink;
	}

	public void setAvaterLink(String avaterLink) {
		this.avaterLink = avaterLink;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLookingFor() {
		return lookingFor;
	}

	public void setLookingFor(String lookingFor) {
		this.lookingFor = lookingFor;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public List<String> getAuth() {
		return auth;
	}

	public void setAuth(List<String> auth) {
		this.auth = auth;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Set<String> getImageLinks() {
		return imageLinks;
	}

	public void setImageLinks(Set<String> imageLinks) {
		this.imageLinks = imageLinks;
	}

	public Set<String> getFolloweesID() {
		return followeesID;
	}

	public void addFolloweeID(String followeeID) {
		if(followeesID==null)
			this.followeesID = new LinkedHashSet<String>();
		this.followeesID.add(followeeID);
	}

	public Set<String> getFollowersID() {
		return followersID;
	}

	public void addFollowerID(String followerID) {
		if(followersID==null)
			this.followersID = new LinkedHashSet<String>();
		this.followersID.add(followerID);
	}

	public Set<Long> getCollectedPosts() {
		return collectedPosts;
	}
	
	public void addCollectedPost(Long collectedPost) {
		if(this.collectedPosts == null){
			this.collectedPosts = new LinkedHashSet<Long>();
		}
		this.collectedPosts.add(collectedPost);
	}
	
}