package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.User;
import model.representation.UserRepresentation;
import service.transactions.Transaction;
import service.transactions.SSETransactions.CancelFollowSSETransaction;
import service.transactions.SSETransactions.FollowSSETransaction;
import service.transactions.daoTransactions.AddImagesTransaction;
import service.transactions.daoTransactions.CancelFollowTransaction;
import service.transactions.daoTransactions.ChangeAvatarTransaction;
import service.transactions.daoTransactions.FollowTransaction;
import service.transactions.daoTransactions.GetUserByIDTransaction;
import service.transactions.daoTransactions.GetUsersByIDsTransaction;
import service.transactions.daoTransactions.UpdateUserProfileTransaction;

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
	
	@Path("updateProfile/{userID : \\d+}")
	@PUT
	public Response updateUserProfile(@PathParam("userID") String userID, @QueryParam("nickName") String nickName,
			@QueryParam("gender") String gender, @QueryParam("lookingFor") String lookingFor, 
			@QueryParam("relationship") String relationship, @QueryParam("birthday") Date birthday) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("nickName", nickName);
		params.put("gender", gender);
		params.put("lookingFor", lookingFor);
		params.put("relationship", relationship);
		params.put("birthday", birthday);
		
		Transaction transaction = new UpdateUserProfileTransaction();
		transaction.execute(params);
		
		return Response.ok().build();
	}
	
	@Path("changeAvatar/{userID : \\d+}")
	@PUT
	public Response changeAvatar(@PathParam("userID") String userID, @QueryParam("avatarLink") String avatarLink) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("avatarLink", avatarLink);
		
		Transaction transaction = new ChangeAvatarTransaction();
		transaction.execute(params);
		
		return Response.ok().build();
	}
	
	@Path("addImages/{userID : \\d+}")
	@PUT
	public Response addImages(@PathParam("userID") String userID, @QueryParam("imageLinks") List<String> imageLinks) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("imageLinks", imageLinks);
		
		Transaction transaction = new AddImagesTransaction();
		transaction.execute(params);
		
		return Response.ok().build();
	}
	
	@Path("getRepresentation/{userID : \\d+}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserRepresentation(@PathParam("userID") String userID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		
		Transaction transaction = new GetUserByIDTransaction();
		User user = null;
		user = (User) transaction.execute(params);

		return Response.ok(new GenericEntity<UserRepresentation>(user.getRepresentation()){}).build();
	}
	
	@Path("getRepresentationShortCut/{userID : \\d+}")
	@GET
	public Response getUserRepresentationShortCut(@PathParam("userID") String userID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		
		Transaction transaction = new GetUserByIDTransaction();
		User user = null;
		user = (User) transaction.execute(params);
		
		return Response.ok(new GenericEntity<User.RepresentationShortCut>(user.getRepresentationShortCut()){}).build();
	}
	
	@SuppressWarnings("unchecked")
	@Path("getRepresentationShortCuts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserRepresentationShortCuts(@QueryParam("userIDs") List<String> userIDs) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userIDs", userIDs);
		
		Transaction transaction = new GetUsersByIDsTransaction();
		List<User.RepresentationShortCut> representations = new ArrayList<User.RepresentationShortCut>();
		List<User> users = (List<User>) transaction.execute(params);
		for(User user : users){
			representations.add(user.getRepresentationShortCut());
		}
		
		return Response.ok(new GenericEntity<List<User.RepresentationShortCut>>(representations){}).build();
	}
}
