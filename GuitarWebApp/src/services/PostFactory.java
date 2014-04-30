package services;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import model.Joinable;
import model.JoinableImp;
import model.Post;
import model.Unjoinable;
import utils.PostType;

public class PostFactory implements Factory{
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Post create(Map<String,Object> params){
		Post post = new Post((String)params.get("topic"), 
					(String)params.get("content"), 
					(Set<String>)params.get("mediaLinks"));
		
		PostType type = (PostType) params.get("postType");
		post.setType(type);
		if(type.equals(PostType.ACTIVITY)){
			Joinable joinable = new JoinableImp();
			post.setJoinable(joinable);
			int year = (int) params.get("year") - 1900;
			int month = (int) params.get("month");
			int day = (int) params.get("day");
			post.setStartDate(new Date(year, month, day));
		} else{
			Joinable joinable = new Unjoinable();
			post.setJoinable(joinable);
		}
		
		return post;
	}
	
}
