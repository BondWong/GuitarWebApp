package service.sse;

import java.util.Map;

import model.Post;
import model.representation.CommentRepresentation;

import com.google.gson.Gson;


public abstract class ServerSentEvent {
	protected Map<String, Object> params;
	private Gson gson = new Gson();
	
	public ServerSentEvent(Map<String, Object> params){
		this.params = params;
	}
	
	public abstract String getName();
	
	public abstract String getData();
	
	public String toString(){
		return "name: " + getName() + "\ndata: " + getData();
	}
	
	private String toJson(Object object){
		return gson.toJson(object);
	}
	
	protected String getTwoParamsJsonObject(){
		TwoParamsJsonObject tpjo = new TwoParamsJsonObject();
		tpjo.setUserID((String) params.get("userID"));
		if(params.containsKey("postID"))
			tpjo.setPcID((Long) params.get("postID"));
		else
			tpjo.setPcID((Long) params.get("commentID"));
		
		return toJson(tpjo);
	}
	
	protected String getThreeParamsJsonObject(){
		ThreeParamsJsonObject tpjo = new ThreeParamsJsonObject();
		tpjo.setUserID((String) params.get("userID"));
		tpjo.setPostID((Long) params.get("postID"));
		tpjo.setCommentID((Long) params.get("commentID"));
		
		return toJson(tpjo);
	}
	
	
	protected String getPostShortCutJsonObject(){
		PostRepresentationShortCutJsonObject pscjo = new PostRepresentationShortCutJsonObject();
		pscjo.setUserID((String) params.get("userID"));
		pscjo.setPostRepresentationShortCut((Post.RepresentationShortCut) params.get("postRepresentationShortCut"));
		
		return toJson(pscjo);
	}
	
	protected String getCommentShortCutJsonObject(){
		CommentRepresentationJsonObject cscjo = new CommentRepresentationJsonObject();
		cscjo.setUserID((String) params.get("userID"));
		cscjo.setPostID((Long) params.get("postID"));
		cscjo.setCommentRepresentation((CommentRepresentation) params.get("commentShortCut"));
		
		return toJson(cscjo);
	}
	
	protected String getUidUidJsonObject(){
		UidUidJsonObject uujo = new UidUidJsonObject();
		uujo.setUserID((String) params.get("userID"));
		uujo.setOtherUserID((String) params.get("otherUserID"));
		
		return toJson(uujo);
	}
	
	private class TwoParamsJsonObject{
		private String userID;
		private Long pcID;
		@SuppressWarnings("unused")
		public String getUserID() {
			return userID;
		}
		public void setUserID(String userID) {
			this.userID = userID;
		}
		@SuppressWarnings("unused")
		public Long getPcID() {
			return pcID;
		}
		public void setPcID(Long pcID) {
			this.pcID = pcID;
		}
	}
	
	private class ThreeParamsJsonObject{
		private String userID;
		private Long postID;
		private Long commentID;
		@SuppressWarnings("unused")
		public String getUserID() {
			return userID;
		}
		public void setUserID(String userID) {
			this.userID = userID;
		}
		@SuppressWarnings("unused")
		public Long getPostID() {
			return postID;
		}
		public void setPostID(Long postID) {
			this.postID = postID;
		}
		@SuppressWarnings("unused")
		public Long getCommentID() {
			return commentID;
		}
		public void setCommentID(Long commentID) {
			this.commentID = commentID;
		}
		
	}
	
	private class PostRepresentationShortCutJsonObject{
		private String userID;
		private Post.RepresentationShortCut postRepresentationShortCut;
		@SuppressWarnings("unused")
		public String getUserID() {
			return userID;
		}
		public void setUserID(String userID) {
			this.userID = userID;
		}
		@SuppressWarnings("unused")
		public Post.RepresentationShortCut getPostRepresentationShortCut() {
			return postRepresentationShortCut;
		}
		public void setPostRepresentationShortCut(Post.RepresentationShortCut postRepresentationShortCut) {
			this.postRepresentationShortCut = postRepresentationShortCut;
		}
	}
	
	private class CommentRepresentationJsonObject{
		private String userID;
		private Long postID;
		private CommentRepresentation commentRepresentation;
		@SuppressWarnings("unused")
		public String getUserID() {
			return userID;
		}
		public void setUserID(String userID) {
			this.userID = userID;
		}
		@SuppressWarnings("unused")
		public Long getPostID() {
			return postID;
		}
		public void setPostID(Long postID) {
			this.postID = postID;
		}
		@SuppressWarnings("unused")
		public CommentRepresentation getCommentRepresentation() {
			return commentRepresentation;
		}
		public void setCommentRepresentation(CommentRepresentation commentRepresentation) {
			this.commentRepresentation = commentRepresentation;
		}
	}
	
	private class UidUidJsonObject{
		private String userID;
		private String otherUserID;
		@SuppressWarnings("unused")
		public String getUserID() {
			return userID;
		}
		public void setUserID(String userID) {
			this.userID = userID;
		}
		@SuppressWarnings("unused")
		public String getOtherUserID() {
			return otherUserID;
		}
		public void setOtherUserID(String otherUserID) {
			this.otherUserID = otherUserID;
		}
	}
}
