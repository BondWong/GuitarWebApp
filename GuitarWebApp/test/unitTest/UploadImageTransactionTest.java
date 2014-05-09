package unitTest;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import services.Transaction;
import services.UploadImageTransaction;

public class UploadImageTransactionTest {
	private static String root ="/Users/bondwong/Desktop/";
	
	@Test
	public void testUploadImage() throws Exception{
		Transaction transaction = new UploadImageTransaction();
		
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
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("images", iss);
		params.put("userID", "2011052407");
		params.put("root", root);
		params.put("contentType", "image/png");
		
		transaction.execute(params);
	}
}
