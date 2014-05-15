package service;

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

import security.validation.CommentRep;
import service.transactions.Transaction;
import service.transactions.SSETransactions.AddCommentSSETransaction;
import service.transactions.SSETransactions.CancelSupportSSETransaction;
import service.transactions.SSETransactions.DeleteCommentSSETransaction;
import service.transactions.SSETransactions.SupportAnswerSSETransaction;
import service.transactions.daoTransactions.AddCommentTransaction;
import service.transactions.daoTransactions.CancelSupportTransaction;
import service.transactions.daoTransactions.DeleteCommentTransaction;
import service.transactions.daoTransactions.SupportAnswerTransaction;

@Path("/comment")
public class CommentService {
	private Transaction transaction;
	
	@Path("/add/{userID : \\d+}/{postID : \\d+}")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Response addComment(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID, CommentRep commentRep) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		params.put("commentRep", commentRep);
		
		transaction = new AddCommentTransaction();
		transaction.execute(params);
		transaction = new AddCommentSSETransaction();
		transaction.execute(params);
		
		return Response.ok().build();
	}
	
	@Path("delete/{userID : \\d+}/{postID : \\d+}/{commentID : \\d+}")
	@DELETE
	public Response deleteComment(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID, 
			@PathParam("commentID") Long commentID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		params.put("commentID", commentID);
		
		transaction = new DeleteCommentTransaction();
		transaction.execute(params);
		
		transaction = new DeleteCommentSSETransaction();
		transaction.execute(params);
		
		return Response.ok().build();
	}
	
	@Path("/support/{userID : \\d+}/{commentID : \\d+}")
	@PUT
	public Response supportAnswer(@PathParam("userID") String userID, 
			@PathParam("commentID") Long commentID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("commentID", commentID);
		
		transaction = new SupportAnswerTransaction();
		transaction.execute(params);
		
		transaction = new SupportAnswerSSETransaction();
		transaction.execute(params);
		
		return Response.ok().build();
	}
	
	@Path("/cancelSupport/{userID : \\d+}/{commentID : \\d+}")
	@PUT
	public Response cancelSupport(@PathParam("userID") String userID, 
			@PathParam("commentID") Long commentID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("commentID", commentID);
		
		transaction = new CancelSupportTransaction();
		transaction.execute(params);
		
		transaction = new CancelSupportSSETransaction();
		transaction.execute(params);
		
		return Response.ok().build();
	}
}
