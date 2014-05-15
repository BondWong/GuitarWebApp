package systemTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import persistence.DAO;
import utils.EntityManagerFactoryCreator;
import model.User;


/**
 * Application Lifecycle Listener implementation class UserSystemTestListener
 *
 */
@WebListener
public class UserSystemTestListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public UserSystemTestListener() {
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
    	User user = new User("2011052407");
    	user.setPassword("1901103390");
    	
    	User u2 = new User("2011052406");
    	u2.setPassword("BM1003mb");
    	
    	EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
    	EntityManager em = emf.createEntityManager();
    	DAO<User> udao = null;
    	try {
			udao = new DAO<User>(User.class, em);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	em.getTransaction().begin();
    	udao.create(user);
    	udao.create(u2);
    	em.getTransaction().commit();
    }
	
}
