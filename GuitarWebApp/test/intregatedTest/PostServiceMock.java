package intregatedTest;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import factory.PostRep;
import model.Post;
import utils.PostType;

@Path("/post")
public class PostServiceMock {
	
	@Path("add/{userID : \\d+}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPost(@PathParam("userID") String userID, 
			PostRep postRep){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postRep", postRep);
		
		System.out.println(params);
		
		return Response.ok().build();
	}
	
	@Path("delete/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response deletePost(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		System.out.println(params);
		
		return Response.ok().build();
	}
	
	@Path("fetchByUserID/{userID : \\d+}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Post> fetchPostsByUserID(@PathParam("userID") String userID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		
		System.out.println(params);
		
		return null;
	}
	
	@Path("fetchByType/{type : \\w+}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Post> fetchPostsByType(@PathParam("type") PostType type){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		
		System.out.println(params);
		
		return null;
	}
	
	@Path("fetchByFollowee/{userID : \\d+}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Post> fetchPostsByFollowee(@PathParam("userID") String userID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		
		System.out.println(params);
		
		return null;
	}
	
	@Path("getByID/{postID : \\d+}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Post getPostByID(@PathParam("postID") Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("postID", postID);
		
		System.out.println(params);
		
		return null;
	}
	
	@Path("like/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response likePost(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		System.out.println(params);
		
		return Response.ok().build();
	}
	
	@Path("cancelLike/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response cancelLike(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		System.out.println(params);
		
		return Response.ok().build();
	}
	
	@Path("collect/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response collectPost(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		System.out.println(params);
		
		return Response.ok().build();
	}
	
	@Path("cancelCollect/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response cancelCollect(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		System.out.println(params);
		
		return Response.ok().build();
	}
	
	@Path("join/{userID : \\d+}/{postID : \\d+}")
	@PUT
	public Response joinActivity(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		
		System.out.println(params);
		
		return Response.ok().build();
	}
	
}
