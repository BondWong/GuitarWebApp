package intregatedTest;

import static org.junit.Assert.assertEquals;

import java.io.File;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import services.FileService;

public class FileServiceTest extends JerseyTest{
	private String root ="/Users/bondwong/Desktop/";
	@Override
	protected Application configure(){
		return new ResourceConfig(FileService.class).register(MultiPartFeature.class).register(JacksonFeature.class);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testFileService(){
		FileDataBodyPart form = new FileDataBodyPart("images", new File(root+"text1.png"));
		//FileDataBodyPart form1 = new FileDataBodyPart("images", new File(root+"text2.png"));
		//FileDataBodyPart form2 = new FileDataBodyPart("images", new File(root+"text2.png"));
		
		
		FormDataMultiPart part = (FormDataMultiPart) new FormDataMultiPart()
			.bodyPart(form);
			//.bodyPart(form1)
			//.bodyPart(form2);
		
		Response response = target("/file/images")
				.register(MultiPartFeature.class)
				.request()
				.post(Entity.entity(part, part.getMediaType()));
		
		assertEquals(200, response.getStatus());
	}
}
