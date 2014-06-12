package utils;

import java.util.ArrayList;
import java.util.List;

public class ProtectedURLManager {
	private static List<String> protectedURLs;
	private static List<String> hiddenCodeGeneratorURLs;
	private static List<String> hiddenCodeProtectionURLs;
	private static List<String> deleteHiddenCodeProtectionURLs;
	private static List<String> loginProtectionURLs;
	private static List<String> authorizationProtectionURLs;
	private static List<String> inputValidationProtectionURLs;
	
	static{
		protectedURLs = new ArrayList<String>();
		protectedURLs.add("app");
		protectedURLs.add("security");
		
		hiddenCodeGeneratorURLs = new ArrayList<String>();
		hiddenCodeGeneratorURLs.add("activities.jsp");
		hiddenCodeGeneratorURLs.add("circle.jsp");
		hiddenCodeGeneratorURLs.add("home.jsp");
		hiddenCodeGeneratorURLs.add("profile.jsp");
		hiddenCodeGeneratorURLs.add("Q&A.jsp");
		hiddenCodeGeneratorURLs.add("regAndLogin.jsp");
		hiddenCodeGeneratorURLs.add("show.jsp");
		hiddenCodeGeneratorURLs.add("userCircle.jsp");
		
		hiddenCodeProtectionURLs = new ArrayList<String>();
		hiddenCodeProtectionURLs.add("post");
		hiddenCodeProtectionURLs.add("comment");
		hiddenCodeProtectionURLs.add("user");
		hiddenCodeProtectionURLs.add("community");
		hiddenCodeProtectionURLs.add("fileUploader");
		hiddenCodeProtectionURLs.add("event");
		hiddenCodeProtectionURLs.add("login");
		hiddenCodeProtectionURLs.add("register");
		hiddenCodeProtectionURLs.add("logout");
		hiddenCodeProtectionURLs.add("deleteUser");
		
		deleteHiddenCodeProtectionURLs = new ArrayList<String>();
		deleteHiddenCodeProtectionURLs.add("login");
		deleteHiddenCodeProtectionURLs.add("register");
		
		loginProtectionURLs = new ArrayList<String>();
		loginProtectionURLs.add("post/add");
		loginProtectionURLs.add("post/delete");
		loginProtectionURLs.add("post/like");
		loginProtectionURLs.add("post/cancelLike");
		loginProtectionURLs.add("post/collect");
		loginProtectionURLs.add("post/cancelCollect");
		loginProtectionURLs.add("post/join");
		loginProtectionURLs.add("comment");
		loginProtectionURLs.add("user");
		loginProtectionURLs.add("event");
		loginProtectionURLs.add("fileUploader");
		loginProtectionURLs.add("logout");
		loginProtectionURLs.add("deleteUser");
		
		authorizationProtectionURLs = new ArrayList<String>();
		authorizationProtectionURLs.add("post/add");
		authorizationProtectionURLs.add("post/delete");
		authorizationProtectionURLs.add("post/like");
		authorizationProtectionURLs.add("post/cancelLike");
		authorizationProtectionURLs.add("post/collect");
		authorizationProtectionURLs.add("post/cancelCollect");
		authorizationProtectionURLs.add("post/join");
		authorizationProtectionURLs.add("comment");
		authorizationProtectionURLs.add("user");
		authorizationProtectionURLs.add("event");
		authorizationProtectionURLs.add("fileUploader");
		authorizationProtectionURLs.add("deleteUser");
		
		inputValidationProtectionURLs = new ArrayList<String>();
		inputValidationProtectionURLs.add("post/add");
		inputValidationProtectionURLs.add("comment/add");
		inputValidationProtectionURLs.add("login");
		inputValidationProtectionURLs.add("register");
	}
	
	public static boolean needHiddenCodeProtection(String url) {
		for(String u : hiddenCodeProtectionURLs) {
			if(url.contains(u))
				return true;
		}
		
		return false;
	}
	
	public static boolean needDeleteHiddenCodeProtection(String url) {
		for(String u : deleteHiddenCodeProtectionURLs) {
			if(url.contains(u))
				return true;
		}
		
		return false;
	}
	
	public static boolean needLoginProtection(String url) {
		for(String u : loginProtectionURLs) {
			if(url.contains(u))
				return true;
		}
		
		return false;
	}
	
	public static boolean needAuthorizationProtection(String url) {
		for(String u : authorizationProtectionURLs) {
			if(url.contains(u))
				return true;
		}
		
		return false;
	}
	
	public static boolean needInputValidationProtection(String url) {
		for(String u : inputValidationProtectionURLs) {
			if(url.contains(u))
				return true;
		}
		
		return false;
	}

	public static boolean needHiddenCodeGeneration(String url) {
		// TODO Auto-generated method stub
		for(String u : hiddenCodeGeneratorURLs) {
			if(url.contains(u))
				return true;
		}
		return false;
	}
	
}
