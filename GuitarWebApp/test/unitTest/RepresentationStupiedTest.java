package unitTest;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.junit.Test;

import services.CommentFactory;
import services.PostFactory;
import utils.CommentType;
import utils.ParamGenerator;
import utils.PostType;
import model.Comment;
import model.Post;
import model.User;

public class RepresentationStupiedTest {
	
	@Test
	public void testPost() throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(Post.class);
		StringWriter stringWriter = new StringWriter();
		User user1 = new User("2011052407");
		User user2 = new User("2011052406");
		Post post = new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		Comment comment = new CommentFactory().create(ParamGenerator.generateCommentParam(CommentType.COMMENT));
		user1.addPost(post);
		user2.addComment(post, comment);
		jaxbContext.createMarshaller().marshal(post, stringWriter);
		System.out.println(stringWriter.toString());
	}
}
