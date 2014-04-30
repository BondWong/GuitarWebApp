package services;

import java.util.Map;

import model.Comment;
import model.Supportable;
import model.SupportableImp;
import model.Unsupportable;
import utils.CommentType;

public class CommentFactory implements Factory{
	
	public Comment create(Map<String,Object> params){
		Comment comment = new Comment((String)params.get("content"), (CommentType)params.get("commentType"));
		
		CommentType type = (CommentType)params.get("commentType");
		comment.setType(type);
		if(type.equals(CommentType.ANSWER)){
			Supportable supportable = new SupportableImp();
			comment.setSupportable(supportable);
		} else{
			Supportable supportable = new Unsupportable();
			comment.setSupportable(supportable);
		}
		
		return comment;
	}
}
