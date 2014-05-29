package unitTest;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.junit.Test;

import service.factory.CommentFactory;
import service.factory.PostFactory;
import utils.CommentType;
import utils.ParamGenerator;
import utils.PostType;
import model.Comment;
import model.Post;
import model.User;
import model.representation.PostRepresentation;

public class RepresentationStupiedTest {
	
	@Test
	public void testPostRepresentation() throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(PostRepresentation.class);
		StringWriter stringWriter = new StringWriter();
		User user1 = new User("2011052407");
		User user2 = new User("2011052406");
		Post post = new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		Comment comment = new CommentFactory().create(ParamGenerator.generateCommentParam(CommentType.COMMENT));
		user1.addPost(post);
		user2.addComment(post, comment);
		jaxbContext.createMarshaller().marshal(post.getRepresentation(), stringWriter);
		System.out.println(stringWriter.toString());
	}
}
