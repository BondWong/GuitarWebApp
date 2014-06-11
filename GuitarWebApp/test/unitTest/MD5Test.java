package unitTest;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import utils.ByteToString;

public class MD5Test {
	@Test
	public void testMD5() throws NoSuchAlgorithmException {
		String data = "hello";
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(data.getBytes());
		byte[] hash = digest.digest();
		String result = ByteToString.bytesToHexString(hash);
		assertEquals("5d41402abc4b2a76b9719d911017c592", result);
	}
}
