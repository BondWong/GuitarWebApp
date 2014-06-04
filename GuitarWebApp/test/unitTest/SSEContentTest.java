package unitTest;

import java.util.HashMap;
import java.util.Map;

import model.Post;
import model.User;

import org.junit.Test;

import service.factory.CommentFactory;
import service.factory.PostFactory;
import service.sse.AddCommentServerSentEvent;
import service.sse.AddPostServerSentEvent;
import service.sse.CancelCollectServerSentEvent;
import service.sse.CancelLikeServerSentEvent;
import service.sse.CancelSupportServerSentEvent;
import service.sse.CollectPostServerSentEvent;
import service.sse.DeleteCommentServerSentEvent;
import service.sse.DeletePostServerSentEvent;
import service.sse.FollowServerSentEvent;
import service.sse.JoinActivityServerSentEvent;
import service.sse.LikePostServerSentEvent;
import service.sse.SupportAnswerServerSentEvent;
import utils.CommentType;
import utils.ParamGenerator;
import utils.PostType;

public class SSEContentTest {

	@Test
	public void testAddCommentSSE(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", "2011052407");
		params.put("postID", new Long(24));
		params.put("comment", new CommentFactory().create(ParamGenerator.generateCommentParam(CommentType.COMMENT)));
		AddCommentServerSentEvent acsse = new AddCommentServerSentEvent(params);
		System.out.println(acsse.toString());
	}
	
	@Test
	public void testAddPostSSE(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", "2011052407");
		User user = new User("2011052407");
		user.setPassword("");
		user.setNickName("Bond");
		user.setAvatarLink("1");
		Post post = new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		user.addPost(post);
		params.put("postShortCut", post.getRepresentationShortCut());
		AddPostServerSentEvent acsse = new AddPostServerSentEvent(params);
		System.out.println(acsse.toString());
	}
	
	@Test
	public void testCancelCollectSSE(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", "2011052407");
		params.put("postID", new Long(25));
		CancelCollectServerSentEvent acsse = new CancelCollectServerSentEvent(params);
		System.out.println(acsse.toString());
	}
	
	@Test
	public void testCancelLikeSSE(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", "2011052407");
		params.put("postID", new Long(25));
		CancelLikeServerSentEvent acsse = new CancelLikeServerSentEvent(params);
		System.out.println(acsse.toString());
	}
	
	@Test
	public void testCancelSupportSSE(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", "2011052407");
		params.put("commentID", new Long(25));
		CancelSupportServerSentEvent acsse = new CancelSupportServerSentEvent(params);
		System.out.println(acsse.toString());
	}
	
	@Test
	public void testCollectPostSSE(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", "2011052407");
		params.put("commentID", new Long(25));
		CollectPostServerSentEvent acsse = new CollectPostServerSentEvent(params);
		System.out.println(acsse.toString());
	}
	
	@Test
	public void testDeleteCommentSSE(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", "2011052407");
		params.put("postID", new Long(25));
		params.put("commentID", new Long(2));
		DeleteCommentServerSentEvent acsse = new DeleteCommentServerSentEvent(params);
		System.out.println(acsse.toString());
	}
	
	@Test
	public void testDeletePostSSE(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", "2011052407");
		params.put("postID", new Long(25));
		DeletePostServerSentEvent acsse = new DeletePostServerSentEvent(params);
		System.out.println(acsse.toString());
	}
	
	@Test
	public void testFollowSSE(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", "2011052407");
		params.put("otherUserID", "2011052406");
		FollowServerSentEvent acsse = new FollowServerSentEvent(params);
		System.out.println(acsse.toString());
	}
	
	@Test
	public void testJoinActivitySSE(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", "2011052407");
		params.put("postID", new Long(25));
		JoinActivityServerSentEvent acsse = new JoinActivityServerSentEvent(params);
		System.out.println(acsse.toString());
	}
	
	@Test
	public void testLikePostSSE(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", "2011052407");
		params.put("postID", new Long(25));
		LikePostServerSentEvent acsse = new LikePostServerSentEvent(params);
		System.out.println(acsse.toString());
	}
	
	@Test
	public void testSupportAnswerSSE(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", "2011052407");
		params.put("postID", new Long(25));
		SupportAnswerServerSentEvent acsse = new SupportAnswerServerSentEvent(params);
		System.out.println(acsse.toString());
	}
	
}
