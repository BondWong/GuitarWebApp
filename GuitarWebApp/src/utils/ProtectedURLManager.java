package utils;

import java.util.ArrayList;
import java.util.List;

public class ProtectedURLManager {
	private static List<String> urls;
	static{
		urls = new ArrayList<String>();
	}
	
	public static void addURL(String url){
		urls.add(url);
	}
	
	public static boolean contains(String url){
		for(String u : urls){
			if(url.contains(u))
				return true;
		}
		
		return false;
	}
}
