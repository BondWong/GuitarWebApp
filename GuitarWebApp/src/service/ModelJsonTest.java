package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
import model.representation.UserRepresentation;
import model.representation.PostRepresentation;;

@Path("/test")
public class ModelJsonTest {
	@Path("/Post.RepresentationShortCuts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPostRepresentationShortCuts(){
		Post post = new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		List<Post.RepresentationShortCut> representationShortCuts = new ArrayList<Post.RepresentationShortCut>();
		representationShortCuts.add(post.getRepresentationShortCut());
		return Response.ok(new GenericEntity<List<Post.RepresentationShortCut>>(representationShortCuts){}).build();
	}
	
	@Path("/Post")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPostRepresentation(){
		User user = new User("2011052407");
		Post post = new PostFactory().create(ParamGenerator.generatePostParam(PostType.QUESTION));
		user.addPost(post);
		for(int i=0;i<7;i++){
			User u = new User("201105240"+i);
			u.addComment(post, new CommentFactory().create(ParamGenerator.generateCommentParam(CommentType.ANSWER)));
		}
		
		return Response.ok(new GenericEntity<PostRepresentation>(post.getRepresentation()){}).build();
	}
	
	@Path("/Post.RepresentationShortCut")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPostRepresentationShortCut(){
		Post post = new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		return Response.ok(new GenericEntity<Post.RepresentationShortCut>(post.getRepresentationShortCut()){}).build();
	}
	
	@Path("/User.Representation")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserRepresentation(){
		User user = new User("2011052407");
		User u2 = new User("2011052406");
		Post post = new PostFactory().create(ParamGenerator.generatePostParam(PostType.DISSCUSSION));
		Post p =new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		user.follow(u2);
		user.addPost(post);
		u2.addPost(post);
		user.likePost(p);
		user.likePost(post);
		user.collectPost(p);
		user.joinActivity(p);
		
		return Response.ok(user.getRepresentation()).build();
	}
	
	@Path("/User.Representations")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserShortCuts(){
		User user = new User("2011052407");
		User u2 = new User("2011052406");
		Post post = new PostFactory().create(ParamGenerator.generatePostParam(PostType.DISSCUSSION));
		Post p =new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		user.follow(u2);
		user.addPost(post);
		u2.addPost(post);
		user.likePost(p);
		user.likePost(post);
		user.collectPost(p);
		user.joinActivity(p);
		
		List<UserRepresentation> representations = new ArrayList<UserRepresentation>();
		representations.add(user.getRepresentation());
		representations.add(u2.getRepresentation());
		
		return Response.ok(new GenericEntity<List<UserRepresentation>>(representations){}).build();
	}
	
}
