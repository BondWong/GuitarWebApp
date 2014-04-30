package utils;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;

public class ParamGenerator {
	private static String[] printable = {"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f",
			"g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
			"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","s","T","U",
			"V","W","X","Y","Z","-","_","=","+","*","&","^","%","$","#","@","!","~","<",">",
			"?",",",".","{","}","\\","|","/",";",":","'","(",")"};
	
	public static Map<String, Object> generateCommentParam(CommentType type){
		Map<String, Object> commentParams = new HashMap<String, Object>();
		commentParams.put("content", generateContent(50));
		commentParams.put("commentType", type);
		
		return commentParams;
	}
	
	public static Map<String, Object> generatePostParam(PostType type){
		Map<String, Object> postParams = new HashMap<String, Object>();
		postParams.put("topic",generateContent(10));
		postParams.put("content", generateContent(50));
		postParams.put("mediaLinks", new LinkedHashSet<String>());
		postParams.put("postType", type);
		
		if(type.equals(PostType.ACTIVITY)){
			postParams.put("year", 2014);
			postParams.put("month", new Random(74).nextInt(11));
			postParams.put("day", new Random(51).nextInt(31));
			
		}
		
		return postParams;
	}
	
	private static String generateContent(int length){
		Random random = new Random(47);
		String s = "";
		for(int i =0;i<length;i++){
			s+=printable[random.nextInt(printable.length)];
		}
		
		return s;
	}
}
