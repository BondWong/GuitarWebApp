package unitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.ProtectedURLManager;

public class ProtectedURLManagerTest {
	private static String root = "localhost:8080/GuitarWebApp/";
	private static String post = root + "app/post/";
	private static String comment = root + "app/comment/";
	private static String user = root + "app/user/";
	private static String community = root + "app/community/";
	private static String file = root + "app/fileUploader";
	private static String event = root + "app/event/subscribe";
	private static String security = root + "security/";
	@Test
	public void testAddPost() {
		String url = post + "add";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(true, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testDeletePost() {
		String url = post + "delete";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testFetchPost() {
		String url = post + "fetch";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needLoginProtection(url));
		assertEquals(false, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testGetPost() {
		String url = post + "get";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needLoginProtection(url));
		assertEquals(false, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testLikePost() {
		String url = post + "like";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testCancelListt() {
		String url = post + "cancelLike";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testCollectPost() {
		String url = post + "collect";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testCancelCollect() {
		String url = post + "cancelCollect";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testJoinActivity() {
		String url = post + "join";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testAddComment() {
		String url = comment + "add";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(true, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testdeleteComment() {
		String url = comment + "delete";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testSupportAnswer() {
		String url = comment + "support";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testCancelSupport() {
		String url = comment + "cancelSupport";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testFollowUser() {
		String url = user + "follow";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testCancelFollow() {
		String url = user + "cancelFollow";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testUserGet() {
		String url = user + "get";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	
	@Test
	public void testChangeAvatar() {
		String url = user + "change";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testAddImages() {
		String url = user + "add";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testUpdateUserProfile() {
		String url = user + "update";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testFetchAllCommunities() {
		String url = community + "fetch";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needLoginProtection(url));
		assertEquals(false, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testSubscribe() {
		String url = event;
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testFileUpload() {
		String url = file;
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testLogin() {
		String url = security + "login";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needLoginProtection(url));
		assertEquals(false, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(true, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testLogout() {
		String url = security + "logout";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(false, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testRegister() {
		String url = security + "register";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needLoginProtection(url));
		assertEquals(false, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(true, ProtectedURLManager.needInputValidationProtection(url));
	}
	
	@Test
	public void testDeleteUser() {
		String url = security + "deleteUser";
		assertEquals(true, ProtectedURLManager.needHiddenCodeProtection(url));
		assertEquals(false, ProtectedURLManager.needDeleteHiddenCodeProtection(url));
		assertEquals(true, ProtectedURLManager.needLoginProtection(url));
		assertEquals(true, ProtectedURLManager.needAuthorizationProtection(url));
		assertEquals(false, ProtectedURLManager.needInputValidationProtection(url));
	}
}
