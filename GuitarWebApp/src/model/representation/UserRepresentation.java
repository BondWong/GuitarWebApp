package model.representation;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import utils.AuthType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class UserRepresentation{
	@XmlElement
	private String ID;
	@XmlElement
	private AuthType authType;
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
	@XmlElement
	private String campus;
	@XmlElement
	private String dorm;
	@XmlElement
	private String dormNum;
	
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}

	public AuthType getAuthType() {
		return authType;
	}

	public void setAuthType(AuthType authType) {
		this.authType = authType;
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

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getDorm() {
		return dorm;
	}

	public void setDorm(String dorm) {
		this.dorm = dorm;
	}

	public String getDormNum() {
		return dormNum;
	}

	public void setDormNum(String dormNum) {
		this.dormNum = dormNum;
	}
	
}