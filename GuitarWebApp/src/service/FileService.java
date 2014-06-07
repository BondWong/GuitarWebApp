package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;


@WebServlet("/app/fileUploader")
public class FileService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String root;
	private DiskFileItemFactory factory;
	private ServletFileUpload upload;
	
    public FileService() {
        super();
    }
    
    public void init(){
    	setUp();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(!isMultipart){
				throw new FileUploadBase.InvalidContentTypeException();
			}
			
			List<String> links = process(request);
			response.setContentType("application/json");
			response.setStatus(200);
			response.getWriter().write(new Gson().toJson(links));
			
		} catch (FileUploadBase.InvalidContentTypeException icte){
			response.sendError(406);
		} catch(FileUploadBase.FileSizeLimitExceededException fsle){
			response.sendError(413);
		} catch(FileUploadException fue){
			response.sendError(400);
		}  catch(Exception e){
			response.sendError(500);
			System.out.println(500);
		}
	}
	
	private void setUp(){
		factory = new DiskFileItemFactory();
    	File repository = (File) this.getServletContext()
				.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024*1024*10);
		root = getServletConfig().getServletContext()
        		.getRealPath("/");
	}
	
	private List<String> process(HttpServletRequest request) throws FileUploadException,
		FileUploadBase.FileSizeLimitExceededException, 
		Exception{
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> iter = items.iterator();
		List<String> links = new ArrayList<String>();
		
		while (iter.hasNext()){
			FileItem item = iter.next();
			
			String userID = "";
			if(item.isFormField()){
				userID = item.getString();
				continue;
			}
			
			File dir = new File(root + "fileDir");
			if(!dir.exists()){
				dir.mkdir();
			}
			
			String contentType = item.getContentType();
			if(!contentType.contains("image"))
				continue;
			
			File uploaddedFile = new File(root + "fileDir/" + 
				userID + System.currentTimeMillis() + "." + 
				contentType.substring(contentType.indexOf("/")+1, contentType.length()));
		
			item.write(uploaddedFile);
			links.add(uploaddedFile.getAbsolutePath());
		}
		
		return links;
	}
}
