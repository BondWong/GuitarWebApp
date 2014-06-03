package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import model.User;
import model.representation.UserRepresentation;
import service.transactions.Transaction;
import service.transactions.SSETransactions.CancelFollowSSETransaction;
import service.transactions.SSETransactions.FollowSSETransaction;
import service.transactions.daoTransactions.CancelFollowTransaction;
import service.transactions.daoTransactions.FollowTransaction;
import service.transactions.daoTransactions.GetUserByIDTransaction;
import service.transactions.daoTransactions.GetUsersByIDsTransaction;

@Path("/user")
public class UserService {
	@Path("follow/{userID : \\d+}/{otherUserID : \\d+}")
	@PUT
	public Response follow(@PathParam("userID") String userID, @PathParam("otherUserID") String otherUserID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("otherUserID", otherUserID);
		
		Transaction transaction = new FollowTransaction();
		transaction.execute(params);
		
		transaction = new FollowSSETransaction();
		transaction.execute(params);
		
		return Response.ok().build();
	}
	
	@Path("cancelFollow/{userID : \\d+}/{otherUserID : \\d+}")
	@PUT
	public Response cancelFollow(@PathParam("userID") String userID, @PathParam("otherUserID") String otherUserID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("otherUserID", otherUserID);
		
		Transaction transaction = new CancelFollowTransaction();
		transaction.execute(params);
		
		transaction = new CancelFollowSSETransaction();
		transaction.execute(params);
		
		return Response.ok().build();
	}
	
	@Path("getRepresentation/{userID : \\d+}")
	@GET
	public Response getUserRepresentation(@PathParam("userID") String userID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		
		Transaction transaction = new GetUserByIDTransaction();
		User user = null;
		user = (User) transaction.execute(params);

		return Response.ok(new GenericEntity<UserRepresentation>(user.getRepresentation()){}).build();
	}
	
	@SuppressWarnings("unchecked")
	@Path("getRepresentations")
	@GET
	public Response getUsersRepresentation(@QueryParam("userIDs") List<String> userIDs) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userIDs", userIDs);
		
		Transaction transaction = new GetUsersByIDsTransaction();
		List<UserRepresentation> representations = new ArrayList<UserRepresentation>();
		representations = (List<UserRepresentation>) transaction.execute(params);
		
		
		return Response.ok(new GenericEntity<List<UserRepresentation>>(representations){}).build();
	}
}
