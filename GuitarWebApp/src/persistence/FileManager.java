package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FileManager {
	//private final static String root ="/Users/bondwong/Documents/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GuitarWebApp/";
	private final static String imageDir = "uploadImage";
	
	private static FileManager instance;
	
	private FileManager(){}
	
	public void delete(String link){
		synchronized(this){
		}
	}
	
	public void delete(List<String> links){
		for(String link : links){
			delete(link);
		}
	}
	
	public String save(InputStream file,
			String root, String userID, String contentType) throws IOException{
		synchronized(this){
			File destDir = new File(root+imageDir);
			if(createDir(destDir)){
				File image = new File(root + imageDir + "/" + userID + 
						System.currentTimeMillis() + " " + "." +
						contentType);
				if(image.createNewFile()){
					writeToFile(image, file);
					return image.getAbsolutePath();
				} else{
					throw new IOException();
				}
			} else{
				throw new FileNotFoundException();
			}
		}
	}
	
	public Set<String> save(List<InputStream> files, 
			String root, String userID, String contentType) throws IOException{
		Set<String> links = new LinkedHashSet<String>();
		for(InputStream file : files){
			String link = save(file, root, userID, contentType);
			links.add(link);
		}
		
		return links;
	}
	
	public static FileManager getInstance(){
		if(instance == null){
			synchronized(FileManager.class){
				if(instance == null){
					instance = new FileManager();
				}
			}
		}
		
		return instance;
	}
	
	private boolean createDir(File dir){
		if(dir.exists()){
			return true;
		} else{
			return dir.mkdir();
		}
	}
	
	private void writeToFile(File image, InputStream file) throws IOException{
		byte[] buffer = new byte[1024*1024*200];
		int i = 0;
		int size = 0;
		for(;i==0&&((size = file.read(buffer))!=-1);i++);
		System.out.println(size);
		file.close();
		if(i>1){
			throw new IOException();
		} else{
			OutputStream os = new FileOutputStream(image);
			os.write(buffer, 0, size);
			os.close();
		}
	}
}
