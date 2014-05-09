package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/file")
public class FileService {
	private Transaction transaction;
	@Context
	private ServletContext context;
	
	@SuppressWarnings("unchecked")
	@Path("/images")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadImages(@FormDataParam("images") List<FormDataBodyPart> bodyParts,
			@FormDataParam("userID") String userID){
		List<InputStream> images = new ArrayList<InputStream>();
		for(FormDataBodyPart part : bodyParts){
			try {
				images.add(new FileInputStream(part.getEntityAs(File.class)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("images", images);
		params.put("userID", userID);
		params.put("contentType", "image/png");
		params.put("root", /*context.getRealPath("/")*/"/Users/bondwong/Desktop/");
		
		
		transaction = new UploadImageTransaction();
		
		Set<String> links = new LinkedHashSet<String>();
		try {
			links = (Set<String>) transaction.execute(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		
		return Response.ok(new GenericEntity<Set<String>>(links){}, MediaType.APPLICATION_JSON).build();
	}
	
}
