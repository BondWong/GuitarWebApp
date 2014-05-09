package intregatedTest;

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

import services.CommentRep;

@Path("/comment")
public class CommentServiceMock {
	
	@Path("/add/{userID : \\d+}/{postID : \\d+}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(@PathParam("userID") String userID, 
			@PathParam("postID") Long postID, CommentRep commentRep){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("postID", postID);
		params.put("commentRep", commentRep);
		
		System.out.println(params);
		
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
		
		System.out.println(params);
		
		return Response.ok().build();
	}
	
	@Path("/support/{userID : \\d+}/{commentID : \\d+}")
	@PUT
	public Response supportAnswer(@PathParam("userID") String userID, 
			@PathParam("commentID") Long commentID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("commentID", commentID);
		
		System.out.println(params);
		
		return Response.ok().build();
	}
	
	@Path("/cancelSupport/{userID : \\d+}/{commentID : \\d+}")
	@PUT
	public Response cancelSupport(@PathParam("userID") String userID, 
			@PathParam("commentID") Long commentID){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("commentID", commentID);
		
		System.out.println(params);
		
		return Response.ok().build();
	}
}
