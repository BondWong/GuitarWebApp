package services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import factory.CommentRep;
import transactions.AddCommentTransaction;
import transactions.CancelSupportTransaction;
import transactions.DeleteCommentTransaction;
import transactions.SupportAnswerTransaction;
import transactions.Transaction;

@Path("/comment")
public class CommentService {
	private Transaction transaction;
	
	@Path("/add/{userID : \\d+}/{postID : \\d+}")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Response addComment(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID, CommentRep commentRep){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		params.put("commentRep", commentRep);
		
		transaction = new AddCommentTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
	@Path("delete/{userID : \\d+}/{postID : \\d+}/{commentID : \\d+}")
	@DELETE
	public Response deleteComment(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID, 
			@PathParam("commentID") Long commentID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		params.put("commentID", commentID);
		
		transaction = new DeleteCommentTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok().build();
	}
	
	@Path("/support/{userID : \\d+}/{commentID : \\d+}")
	@PUT
	public Response supportAnswer(@PathParam("userID") String userID, 
			@PathParam("commentID") Long commentID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("commentID", commentID);
		
		transaction = new SupportAnswerTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
	@Path("/cancelSupport/{userID : \\d+}/{commentID : \\d+}")
	@PUT
	public Response cancelSupport(@PathParam("userID") String userID, 
			@PathParam("commentID") Long commentID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("commentID", commentID);
		
		transaction = new CancelSupportTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
}
