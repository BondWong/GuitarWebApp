package unitTest;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import persistence.FileManager;

public class FileManagerTest {
	private static String root ="/Users/bondwong/Desktop/";
	
	@Ignore
	@Test
	public void testSaveFile() throws IOException{
		File file = new File(root+"text.png");
		assertEquals(true, file.exists());
		
		FileManager fileManager = FileManager.getInstance();
		InputStream is = new FileInputStream(file);
		
		fileManager.save(is, root, "2011052407","png");
	}
	
	@Test
	public void testSaveFiles() throws IOException{
		File file1 = new File(root+"text1.png");
		File file2 = new File(root+"text2.png");
		File file3 = new File(root+"text3.png");
		
		assertEquals(true, file1.exists());
		assertEquals(true, file2.exists());
		assertEquals(true, file3.exists());
		
		InputStream is1 = new FileInputStream(file1);
		InputStream is2= new FileInputStream(file2);
		InputStream is3 = new FileInputStream(file3);
		
		List<InputStream> iss = new ArrayList<InputStream>();
		iss.add(is1);
		iss.add(is2);
		iss.add(is3);
		
		FileManager fileManager = FileManager.getInstance();
		fileManager.save(iss, root, "2011052407", "png");
		
	}
}
