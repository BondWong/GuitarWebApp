package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Post;
import security.validation.PostRep;
import service.transactions.SSETransactions.AddPostSSETransaction;
import service.transactions.SSETransactions.CancelCollectSSETransaction;
import service.transactions.SSETransactions.CancelLikeSSETransaction;
import service.transactions.SSETransactions.CollectPostSSETransaction;
import service.transactions.SSETransactions.DeletePostSSETransaction;
import service.transactions.SSETransactions.JoinActivitySSETransaction;
import service.transactions.SSETransactions.LikePostSSETransaction;
import service.transactions.SSETransactions.SSETransaction;
import service.transactions.daoTransactions.AddPostTransaction;
import service.transactions.daoTransactions.CancelCollectTransaction;
import service.transactions.daoTransactions.CancelLikeTransaction;
import service.transactions.daoTransactions.CollectPostTransaction;
import service.transactions.daoTransactions.DAOTransaction;
import service.transactions.daoTransactions.DeletePostTransaction;
import service.transactions.daoTransactions.FetchPostsByFolloweeTransaction;
import service.transactions.daoTransactions.FetchPostsByTypeTransaction;
import service.transactions.daoTransactions.FetchPostsByUserIDTransaction;
import service.transactions.daoTransactions.GetPostByIDTransaction;
import service.transactions.daoTransactions.GetPostsByIDsTransaction;
import service.transactions.daoTransactions.JoinActivityTransaction;
import service.transactions.daoTransactions.LikePostTransaction;
import utils.PostType;

@Path("/post")
public class PostService {
	private DAOTransaction transaction;
	private SSETransaction sseTransaction;
	
	@Path("add/{userID : \\d+}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPost(@PathParam("userID") String userID, 
			PostRep postRep) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postRep", postRep);
		
		transaction = new AddPostTransaction();
		transaction.execute(params);
		sseTransaction = new AddPostSSETransaction();
		sseTransaction.execute(params);
		
		return Response.ok().build();
	}
	
	@Path("delete/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response deletePost(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		transaction = new DeletePostTransaction();
		transaction.execute(params);
		
		sseTransaction = new DeletePostSSETransaction();
		sseTransaction.execute(params);
		
		return Response.ok().build();
	}
	
	@SuppressWarnings("unchecked")
	@Path("fetchByUserID/{userID : \\d+}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response fetchPostsByUserID(@PathParam("userID") String userID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		
		transaction = new FetchPostsByUserIDTransaction();
		
		List<Post.ShortCut> posts = new ArrayList<Post.ShortCut>();
		posts = (List<Post.ShortCut>) transaction.execute(params);
		
		return Response.ok(new GenericEntity<List<Post.ShortCut>>(posts){}).build();
	}
	
	@SuppressWarnings("unchecked")
	@Path("fetchByType/{type : [A-Z]+}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response fetchPostsByType(@PathParam("type") PostType type) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		
		transaction = new FetchPostsByTypeTransaction();
		
		List<Post.ShortCut> posts = new ArrayList<Post.ShortCut>();
		posts = (List<Post.ShortCut>) transaction.execute(params);
		
		return Response.ok(new GenericEntity<List<Post.ShortCut>>(posts){}).build();
	}
	
	@SuppressWarnings("unchecked")
	@Path("fetchByFollowee/{userID : \\d+}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response fetchPostsByFollowee(@PathParam("userID") String userID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		
		transaction = new FetchPostsByFolloweeTransaction();
		
		List<Post.ShortCut> posts = new ArrayList<Post.ShortCut>();
		posts = (List<Post.ShortCut>) transaction.execute(params);
		
		return Response.ok(new GenericEntity<List<Post.ShortCut>>(posts){}).build();
	}
	
	@Path("getByID/{postID : \\d+}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Post getPostByID(@PathParam("postID") Long postID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("postID", postID);
		
		transaction = new GetPostByIDTransaction();
		
		Post post = null;
		post = (Post) transaction.execute(params);
		
		return post;
	}
	
	@SuppressWarnings("unchecked")
	@Path("getByIDs")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPostsByIDs(@QueryParam("postIDs") List<Long> postIDs) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("postIDs", postIDs);
		
		transaction = new GetPostsByIDsTransaction();
		List<Post.ShortCut> posts = new ArrayList<Post.ShortCut>();
		posts = (List<Post.ShortCut>) transaction.execute(params);
		
		return Response.ok(new GenericEntity<List<Post.ShortCut>>(posts){}).build();
	}
	
	@Path("like/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response likePost(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		transaction = new LikePostTransaction();
		transaction.execute(params);
		
		sseTransaction = new LikePostSSETransaction();
		sseTransaction.execute(params);
		
		return Response.ok().build();
	}
	
	@Path("cancelLike/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response cancelLike(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		transaction = new CancelLikeTransaction();
		transaction.execute(params);
		
		sseTransaction = new CancelLikeSSETransaction();
		sseTransaction.execute(params);
		
		return Response.ok().build();
	}
	
	@Path("collect/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response collectPost(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		transaction = new CollectPostTransaction();
		transaction.execute(params);
		
		sseTransaction = new CollectPostSSETransaction();
		sseTransaction.execute(params);
		
		return Response.ok().build();
	}
	
	@Path("cancelCollect/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response cancelCollect(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		transaction = new CancelCollectTransaction();
		transaction.execute(params);
		
		sseTransaction = new CancelCollectSSETransaction();
		sseTransaction.execute(params);
		
		return Response.ok().build();
	}
	
	@Path("join/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response joinActivity(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		transaction = new JoinActivityTransaction();
		transaction.execute(params);
		
		sseTransaction = new JoinActivitySSETransaction();
		sseTransaction.execute(params);
		
		return Response.ok().build();
	}
	
}
