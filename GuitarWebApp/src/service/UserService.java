package service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import service.transactions.FollowTransaction;
import service.transactions.Transaction;

@Path("/user")
public class UserService {
	@Path("follow/{userID : \\d+}/{otherUserID : \\d+}")
	@PUT
	public Response follow(@PathParam("userID") String userID, @PathParam("otherUserID") String otherUserID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("otherUserID", otherUserID);
		
		Transaction transaction = new FollowTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
}
