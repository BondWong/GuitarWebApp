package boostrap;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class Boostrap
 *
 */
@WebListener
public class Boostrap implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Boostrap() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // TODO Auto-generated method stub
    	ThreadPoolExecutor executor = (ThreadPoolExecutor) servletContextEvent 
    			.getServletContext().getAttribute("executor"); 
    			executor.shutdown();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // TODO Auto-generated method stub
    	ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 60000L, 
    			TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100)); 
    			servletContextEvent.getServletContext().setAttribute("executor", 
    			executor); 
    	
    }
	
}
