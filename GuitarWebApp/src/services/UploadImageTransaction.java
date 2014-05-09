package services;

import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import persistence.FileManager;

public class UploadImageTransaction implements Transaction{

	@SuppressWarnings("unchecked")
	@Override
	public Object execute(Map<String, Object> params)
			throws Exception {
		List<InputStream> images = (List<InputStream>) params.get("images");
		String userID = (String) params.get("userID");
		String root = (String) params.get("root");
		String contentType = (String) params.get("contentType");
		
		contentType = contentType.substring(contentType.indexOf("/")+1, 
				contentType.length());
		System.out.println(contentType);
		
		Set<String> links = new LinkedHashSet<String>();
		
		FileManager fileManager = FileManager.getInstance();
		fileManager.save(images, root, userID, contentType);
		
		return links;
	}

}
