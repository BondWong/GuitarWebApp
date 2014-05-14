package service.factory;

import security.validation.PostRep;
import utils.PostType;
import model.Post;

public class PostFactory implements Factory{
	
	public Post create(Object object){
		PostRep postRep = (PostRep) object;
		Post post = new Post(postRep.getTopic(), 
					postRep.getContent(), 
					postRep.getMediaLocation());
		
		post.setType(PostType.valueOf(postRep.getPostType()));
		post.setStartDate(postRep.getStartDate());
		
		return post;
	}
	
}
