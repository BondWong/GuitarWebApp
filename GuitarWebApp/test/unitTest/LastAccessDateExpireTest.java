package unitTest;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import model.Account;

public class LastAccessDateExpireTest {
	@Test
	public void testLastAccessDateExpire1() {
		Account a = new Account();
		a.setLastAccessDate(new Date());
		boolean result = a.shouldResetChance(new Date());
		
		assertEquals(false, result);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testLastAccessDateExpire2() {
		Account a = new Account();
		a.setLastAccessDate(new Date(2014-1900, 6-1, 6));
		boolean result = a.shouldResetChance(new Date());
		
		assertEquals(true, result);
	}
}
