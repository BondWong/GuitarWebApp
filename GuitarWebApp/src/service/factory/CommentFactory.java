package service.factory;

import model.Comment;

public class CommentFactory implements Factory{
	
	public Comment create(Object object){
		CommentRep commentRep = (CommentRep)object;
		Comment comment = new Comment(commentRep.getContent());
		
		comment.setType(commentRep.getType());
		
		return comment;
	}
}
