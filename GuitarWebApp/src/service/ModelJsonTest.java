package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import service.factory.CommentFactory;
import service.factory.PostFactory;
import utils.CommentType;
import utils.ParamGenerator;
import utils.PostType;
import model.Post;
import model.User;

@Path("/test")
public class ModelJsonTest {
	@Path("/Post.ShortCuts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPostShortCuts(){
		Post post = new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		List<Post.ShortCut> shortCuts = new ArrayList<Post.ShortCut>();
		shortCuts.add(post.getShortCut());
		return Response.ok(new GenericEntity<List<Post.ShortCut>>(shortCuts){}).build();
	}
	
	@Path("/Post")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFullPost(){
		User user = new User("2011052407");
		Post post = new PostFactory().create(ParamGenerator.generatePostParam(PostType.QUESTION));
		user.addPost(post);
		for(int i=0;i<7;i++){
			User u = new User("201105240"+i);
			u.addComment(post, new CommentFactory().create(ParamGenerator.generateCommentParam(CommentType.ANSWER)));
		}
		
		return Response.ok(Entity.json(post)).build();
	}
	
	@Path("/Post.ShortCut")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPostShortCut(){
		Post post = new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		return Response.ok(new GenericEntity<Post.ShortCut>(post.getShortCut()){}).build();
	}
	
}
