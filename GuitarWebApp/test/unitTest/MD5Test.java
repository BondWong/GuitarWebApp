package unitTest;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import utils.ByteToString;

public class MD5Test {
	@Test
	public void testMD5() throws NoSuchAlgorithmException {
		String data = "1901103390";
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(data.getBytes());
		byte[] hash = digest.digest();
		String result = ByteToString.bytesToHexString(hash);
		assertEquals("7899a51c8e25f5ca31ea775c93d52ec4", result);
	}
}
