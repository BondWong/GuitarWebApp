package service;

import java.util.ArrayList;
import java.util.Date;
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
import model.representation.PostRepresentation;

@Path("/test")
public class ModelJsonTest {
	@Path("/Post.RepresentationShortCuts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPostRepresentationShortCuts(){
		Post post = new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		User user1 = new User("2011052406");
		user1.setNickName("Obama");
		user1.setAvatarLink("xx.xx.yy");
		user1.addPost(post);
		User user = new User("2011052407");
		user.likePost(post);
		user.collectPost(post);
		user.joinActivity(post);
		List<Post.RepresentationShortCut> representationShortCuts = new ArrayList<Post.RepresentationShortCut>();
		representationShortCuts.add(post.getRepresentationShortCut());
		return Response.ok(new GenericEntity<List<Post.RepresentationShortCut>>(representationShortCuts){}).build();
	}
	
	@Path("/Post.Representation")
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
		
		User user1 = new User("2011052406");
		user.likePost(post);
		user1.likePost(post);
		user1.collectPost(post);
		user1.joinActivity(post);
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
		Post post = new PostFactory().create(ParamGenerator.generatePostParam(PostType.DISCUSSION));
		Post p =new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		user.follow(u2);
		user.addPost(post);
		u2.addPost(post);
		user.likePost(p);
		user.likePost(post);
		user.collectPost(p);
		user.joinActivity(p);
		user.setAvatarLink("xx.yy.zz");
		user.setBirthday(new Date());
		user.setGender("male");
		user.setLookingFor("girls");
		user.setNickName("Winson");
		user.addImageLink("xx.zz.yy");
		user.addImageLink("zz.yy.xx");
		user.setRelationShip("has many wives");
		
		return Response.ok(user.getRepresentation()).build();
	}
	
	@Path("/User.RepresentationShortCuts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserRepresentationShortCuts(){
		User user = new User("2011052407");
		User u2 = new User("2011052406");
		Post post = new PostFactory().create(ParamGenerator.generatePostParam(PostType.DISCUSSION));
		Post p =new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		user.follow(u2);
		user.addPost(post);
		u2.addPost(post);
		user.likePost(p);
		user.likePost(post);
		user.collectPost(p);
		user.joinActivity(p);
		user.setAvatarLink("xx.yy.zz");
		user.setBirthday(new Date());
		user.setGender("male");
		user.setLookingFor("girls");
		user.setNickName("Winson");
		user.addImageLink("xx.zz.yy");
		user.addImageLink("zz.yy.xx");
		user.setRelationShip("has many wives");
		
		List<User.RepresentationShortCut> representations = new ArrayList<User.RepresentationShortCut>();
		representations.add(user.getRepresentationShortCut());
		representations.add(u2.getRepresentationShortCut());
		
		return Response.ok(new GenericEntity<List<User.RepresentationShortCut>>(representations){}).build();
	}
	
}
