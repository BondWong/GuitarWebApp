package services;

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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import factory.PostRep;
import model.Post;
import transactions.AddPostTransaction;
import transactions.CancelCollectTransaction;
import transactions.CancelLikeTransaction;
import transactions.CollectPostTransaction;
import transactions.DeletePostTransaction;
import transactions.FetchPostsByFolloweeTransaction;
import transactions.FetchPostsByTypeTransaction;
import transactions.FetchPostsByUserIDTransaction;
import transactions.GetPostByIDTransaction;
import transactions.JoinActivityTransaction;
import transactions.LikePostTransaction;
import transactions.Transaction;
import utils.PostType;

@Path("/post")
public class PostService {
	private Transaction transaction;
	
	@Path("add/{userID : \\d+}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPost(@PathParam("userID") String userID, 
			PostRep postRep){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postRep", postRep);
		
		transaction = new AddPostTransaction();
		
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
	@Path("delete/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response deletePost(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		transaction = new DeletePostTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
	@SuppressWarnings("unchecked")
	@Path("fetchByUserID/{userID : \\d+}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response fetchPostsByUserID(@PathParam("userID") String userID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		
		transaction = new FetchPostsByUserIDTransaction();
		
		List<Post.ShortCut> posts = new ArrayList<Post.ShortCut>();
		try {
			posts = (List<Post.ShortCut>) transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok(new GenericEntity<List<Post.ShortCut>>(posts){}).build();
	}
	
	@SuppressWarnings("unchecked")
	@Path("fetchByType/{type : \\w+}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response fetchPostsByType(@PathParam("type") PostType type){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		
		transaction = new FetchPostsByTypeTransaction();
		
		List<Post.ShortCut> posts = new ArrayList<Post.ShortCut>();
		try {
			posts = (List<Post.ShortCut>) transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(new GenericEntity<List<Post.ShortCut>>(posts){}).build();
	}
	
	@SuppressWarnings("unchecked")
	@Path("fetchByFollowee/{userID : \\d+}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response fetchPostsByFollowee(@PathParam("userID") String userID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		
		transaction = new FetchPostsByFolloweeTransaction();
		
		List<Post.ShortCut> posts = new ArrayList<Post.ShortCut>();
		try {
			posts = (List<Post.ShortCut>) transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok(new GenericEntity<List<Post.ShortCut>>(posts){}).build();
	}
	
	@Path("getByID/{postID : \\d+}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Post getPostByID(@PathParam("postID") Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("postID", postID);
		
		transaction = new GetPostByIDTransaction();
		
		Post post = null;
		try {
			post = (Post) transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return post;
	}
	
	@Path("like/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response likePost(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		transaction = new LikePostTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
	@Path("cancelLike/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response cancelLike(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		transaction = new CancelLikeTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
	@Path("collect/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response collectPost(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		transaction = new CollectPostTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
	@Path("cancelCollect/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response cancelCollect(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		transaction = new CancelCollectTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
	@Path("join/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response joinActivity(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		transaction = new JoinActivityTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
}
