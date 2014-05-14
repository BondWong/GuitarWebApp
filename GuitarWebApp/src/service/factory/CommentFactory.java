package service.factory;

import security.validation.CommentRep;
import utils.CommentType;
import model.Comment;

public class CommentFactory implements Factory{
	
	public Comment create(Object object){
		CommentRep commentRep = (CommentRep)object;
		Comment comment = new Comment(commentRep.getContent());
		
		comment.setType(CommentType.valueOf(commentRep.getCommentType()));
		
		return comment;
	}
}
