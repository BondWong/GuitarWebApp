package unitTest;

import static org.junit.Assert.*;

import java.util.Date;

import model.Account;

import org.junit.Test;

public class IsAccountProtectedTest {
	@Test
	public void testIsAccountProtected() {
		Account a = new Account();
		
		boolean hasChance = a.hasChance();
		assertEquals(true, hasChance);
		
		boolean shouldResetChance = a.shouldResetChance(new Date());
		assertEquals(false, shouldResetChance);
		
		boolean isProtected = a.isProtected(new Date());
		assertEquals(false, isProtected);
	}
	
	@Test
	public void testIsAccountProtected2() {
		Account a = new Account();
		
		a.setChance((short) 0);
		boolean hasChance = a.hasChance();
		assertEquals(false, hasChance);
		
		boolean shouldResetChance = a.shouldResetChance(new Date());
		assertEquals(false, shouldResetChance);
		
		boolean isProtected = a.isProtected(new Date());
		assertEquals(true, isProtected);
	}
	
	@Test
	public void testIsAccountProtected3() {
		Account a = new Account();
		
		a.setChance((short) 0);
		a.setLastAccessDate(new Date());
		boolean hasChance = a.hasChance();
		assertEquals(false, hasChance);
		
		boolean shouldResetChance = a.shouldResetChance(new Date());
		assertEquals(false, shouldResetChance);
		
		boolean isProtected = a.isProtected(new Date());
		assertEquals(true, isProtected);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testIsAccountProtected4() {
		Account a = new Account();
		
		a.setChance((short) 0);
		Date d = new Date(2014-1900, 6-1, 6);
		a.setLastAccessDate(d);
		boolean hasChance = a.hasChance();
		assertEquals(false, hasChance);
		
		boolean shouldResetChance = a.shouldResetChance(new Date());
		assertEquals(true, shouldResetChance);
		
		boolean isProtected = a.isProtected(new Date());
		assertEquals(false, isProtected);
	}
}
