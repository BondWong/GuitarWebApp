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
import model.User.ShortCut;
import service.transactions.Transaction;
import service.transactions.SSETransactions.FollowSSETransaction;
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
	
	@Path("getShortCut/{userID : \\d+}")
	@GET
	public Response getShortCut(@PathParam("userID") String userID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		
		Transaction transaction = new GetUserByIDTransaction();
		User user = null;
		user = (User) transaction.execute(params);

		return Response.ok(new GenericEntity<User.ShortCut>(user.getShortCut()){}).build();
	}
	
	@SuppressWarnings("unchecked")
	@Path("getShortCuts")
	@GET
	public Response getShortCut(@QueryParam("userIDs") List<String> userIDs) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userIDs", userIDs);
		
		Transaction transaction = new GetUsersByIDsTransaction();
		List<User.ShortCut> shortCuts = new ArrayList<User.ShortCut>();
		shortCuts = (List<ShortCut>) transaction.execute(params);
		
		
		return Response.ok(new GenericEntity<List<User.ShortCut>>(shortCuts){}).build();
	}
}
