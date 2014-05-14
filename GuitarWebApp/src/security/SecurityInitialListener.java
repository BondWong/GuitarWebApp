package security;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import utils.ProtectedURLManager;

/**
 * Application Lifecycle Listener implementation class SecurityInitialFilter
 *
 */
@WebListener
public class SecurityInitialListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SecurityInitialListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	ProtectedURLManager.addURL("/post/add");
    	ProtectedURLManager.addURL("/post/delete");
    	ProtectedURLManager.addURL("/post/like");
    	ProtectedURLManager.addURL("/post/cancelLike");
    	ProtectedURLManager.addURL("/post/collect");
    	ProtectedURLManager.addURL("/post/cancelCollect");
    	ProtectedURLManager.addURL("/post/joinActivity");
    	
    	ProtectedURLManager.addURL("/user/follow");
    	
    	ProtectedURLManager.addURL("/comment/add");
    	ProtectedURLManager.addURL("/comment/delete");
    	ProtectedURLManager.addURL("/comment/support");
    	ProtectedURLManager.addURL("/comment/cancelSupport");
    	
    	ProtectedURLManager.addURL("/fileUploader");
    	
    }
	
}
