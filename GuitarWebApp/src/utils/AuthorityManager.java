package utils;

import java.util.ArrayList;
import java.util.List;

public class AuthorityManager {
	private static List<String> memberAuth = new ArrayList<String>();
	private static List<String> postOwnerAuth = new ArrayList<String>();
	private static List<String> godAuth = new ArrayList<String>();
	static{
		memberAuth.add("PostService-addPost-DISSCUSION");
		memberAuth.add("PostService-addPost-QUESTION");
		memberAuth.add("PostService-deletePost-owner");
		memberAuth.add("PostService-fetch");
		memberAuth.add("PostService-get");
		memberAuth.add("PostService-likePost");
		memberAuth.add("PostService-cancelLike");
		memberAuth.add("PostService-collectPost");
		memberAuth.add("PostService-canceCollect");
		memberAuth.add("PostService-joinActivity");
		
		memberAuth.add("CommentService-addComment");
		memberAuth.add("CommentService-deleteComment-owner");
		memberAuth.add("CommentService-deleteComment-ownedPost");
		memberAuth.add("CommentService-supportAnswer");
		memberAuth.add("CommentService-cancelSupport");
		
		memberAuth.add("UserService-follow");
		memberAuth.add("UserService-cancelFollow");
		memberAuth.add("UserService-get");
		memberAuth.add("UserService-changeAvatar");
		memberAuth.add("UserService-addImages");
		memberAuth.add("UserService-updateUserProfile");
		
		memberAuth.add("CommunityService-fetch");
		
		memberAuth.add("ServerSentEvent-subscribe");
		
		memberAuth.add("FileService-uploadFile");
		
		postOwnerAuth.addAll(memberAuth);
		postOwnerAuth.add("PostService-addPost-ACTIVITY");
		
		godAuth.addAll(postOwnerAuth);
		godAuth.add("PostService-deletePost-*");
		godAuth.add("CommentService-deleteComment-*");
		godAuth.remove("PostService-deletePost-owner");
		godAuth.remove("CommentService-deleteComment-owner");
		godAuth.remove("CommentService-deleteComment-ownedPost");
	}
	
	public static List<String> getMemberAuth() {
		return memberAuth;
	}
	
	public static List<String> getPostOwnerAuth() {
		return postOwnerAuth;
	}
	
	public static List<String> getGodAuth() {
		return godAuth;
	}
}
