package utils;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Random;

import factory.CommentRep;
import factory.PostRep;

public class ParamGenerator {
	private static String[] printable = {"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f",
			"g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
			"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","s","T","U",
			"V","W","X","Y","Z","-","_","=","+","*","&","^","%","$","#","@","!","~","<",">",
			"?",",",".","{","}","\\","|","/",";",":","'","(",")"};
	
	public static CommentRep generateCommentParam(CommentType type){
		CommentRep commentParams = new CommentRep();
		commentParams.setContent(generateContent(50));
		commentParams.setType(type);
		
		return commentParams;
	}
	
	@SuppressWarnings("deprecation")
	public static PostRep generatePostParam(PostType type){
		PostRep postParams = new PostRep();
		postParams.setTopic(generateContent(10));
		postParams.setContent(generateContent(50));
		postParams.setMediaLocation(new LinkedHashSet<String>());
		postParams.setType(type);
		postParams.setPublishDate(new Date());
		postParams.setStartDate(new Date(2014-1900, new Random(74).nextInt(11),new Random(51).nextInt(31)));
		
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
