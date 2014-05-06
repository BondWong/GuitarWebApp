package services;

import model.Post;

public class PostFactory implements Factory{
	
	public Post create(Object object){
		PostRep postRep = (PostRep) object;
		Post post = new Post(postRep.getTopic(), 
					postRep.getContent(), 
					postRep.getMediaLocation());
		
		post.setType(postRep.getType());
		post.setStartDate(postRep.getStartDate());
		
		return post;
	}
	
}
