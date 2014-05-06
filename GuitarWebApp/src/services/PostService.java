package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Post;
import utils.PostType;

public class PostService {
	
	@POST
	@Consumes({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON})
	public Response addPost(String userID, PostRep postRep){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postRep", postRep);
		
		Transaction transaction = new AddPostTransaction();
		
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
	@PUT
	@Consumes({MediaType.TEXT_HTML})
	public Response deletePost(String userID, Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		Transaction transaction = new DeletePostTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Consumes({MediaType.TEXT_HTML})
	@Produces({MediaType.APPLICATION_JSON})
	public List<Post> fetchPostsByUserID(String userID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		
		Transaction transaction = new FetchPostsByUserIDTransaction();
		
		try {
			return (List<Post>) transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Consumes({MediaType.TEXT_HTML})
	@Produces({MediaType.APPLICATION_JSON})
	public List<Post> fetchPostsByType(PostType type){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		
		Transaction transaction = new FetchPostsByTypeTransaction();
		try {
			return (List<Post>) transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Consumes({MediaType.TEXT_HTML})
	@Produces({MediaType.APPLICATION_JSON})
	public List<Post> fetchPostsByFollowee(String userID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		
		Transaction transaction = new FetchPostsByFolloweeTransaction();
		
		try {
			return (List<Post>) transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@GET
	@Consumes({MediaType.TEXT_PLAIN})
	@Produces({MediaType.APPLICATION_JSON})
	public Post getPostByID(Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("postID", postID);
		
		Transaction transaction = new GetPostByIDTransaction();
		try {
			return (Post) transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PUT
	@Consumes({MediaType.TEXT_PLAIN})
	public Response likePost(String userID, Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		Transaction transaction = new LikePostTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
	@PUT
	@Consumes({MediaType.TEXT_PLAIN})
	public Response cancelLike(String userID, Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		Transaction transaction = new CancelLikeTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
	@PUT
	@Consumes({MediaType.TEXT_PLAIN})
	public Response collectPost(String userID, Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		Transaction transaction = new CollectPostTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
	@PUT
	@Consumes({MediaType.TEXT_PLAIN})
	public Response cancelCollect(String userID, Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		Transaction transaction = new CancelCollectTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
	@PUT
	@Consumes({MediaType.TEXT_PLAIN})
	public Response joinActivity(String userID, Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		Transaction transaction = new JoinActivityTransaction();
		try {
			transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
}
