package services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CommentService {
	private Transaction transaction;
	
	@POST
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public void addComment(String userID, Long postID, CommentRep commentRep){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		params.put("commentRep", commentRep);
		
		transaction = new AddCommentTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Consumes(MediaType.TEXT_PLAIN)
	public Response deleteComment(String userID, Long postID, Long commentID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		params.put("commentID", commentID);
		
		transaction = new DeleteCommentTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	public void supportAnswer(String userID, Long commentID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("commentID", commentID);
		
		transaction = new SupportAnswerTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	public void cancelSupport(String userID, Long commentID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("commentID", commentID);
		
		transaction = new CancelSupportTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
