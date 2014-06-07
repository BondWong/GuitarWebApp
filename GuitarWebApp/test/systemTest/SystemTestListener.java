package systemTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import persistence.DAO;
import service.factory.CommentFactory;
import service.factory.Factory;
import service.factory.PostFactory;
import utils.CommentType;
import utils.EntityManagerFactoryCreator;
import utils.ParamGenerator;
import utils.PostType;
import model.Account;
import model.Comment;
import model.Community;
import model.Post;
import model.User;


/**
 * Application Lifecycle Listener implementation class UserSystemTestListener
 *
 */
@WebListener
public class SystemTestListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SystemTestListener() {
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
    	User user = new User("2011052405");
    	user.setPassword("1901103390");
    	
    	Account a = new Account();
    	a.setUserID(user.getID());
    	a.setPassword(user.getPassword());
    	
    	User u2 = new User("2011052406");
    	u2.setPassword("BM1003mb");
    	
    	Account a2 = new Account();
    	a2.setUserID(u2.getID());
    	a2.setPassword(u2.getPassword());
    	
    	EntityManagerFactory emf = EntityManagerFactoryCreator.getInstance().getEntityManagerFactory();
    	EntityManager em = emf.createEntityManager();
    	DAO<Account> adao = null;
    	try {
			adao = new DAO<Account>(Account.class, em);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	adao.create(a);
    	adao.create(a2);
    	
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
    	user.follow(u2);
    	em.getTransaction().commit();
    	
    	DAO<Community> cdao = null;
    	try {
			cdao = new DAO<Community>(Community.class, em);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	em.getTransaction().begin();
    	Community c1 = new Community();
    	c1.setName("disscussion");
    	c1.setPostType(PostType.DISCUSSION);
    	Community c2 = new Community();
    	c2.setName("activity");
    	c2.setPostType(PostType.ACTIVITY);
    	Community c3 = new Community();
    	c3.setName("question");
    	c3.setPostType(PostType.QUESTION);
    	cdao.create(c1);
    	cdao.create(c2);
    	cdao.create(c3);
    	em.getTransaction().commit();
    	
    	Factory f = new PostFactory();
    	Factory f1 = new CommentFactory();
    	em.getTransaction().begin();
    	for(int i=0;i<10;i++){
    		if(i<5){
    			Post p = (Post)f.create(ParamGenerator.generatePostParam(PostType.DISCUSSION));
    			user.addPost(p);
    			Comment c = (Comment)f1.create(ParamGenerator.generateCommentParam(CommentType.COMMENT));
    			u2.addComment(p, c);
    			u2.likePost(p);
    		}
    		if(i>5){
    			Post p = (Post)f.create(ParamGenerator.generatePostParam(PostType.QUESTION));
    			u2.addPost(p);
    			Comment c = (Comment)f1.create(ParamGenerator.generateCommentParam(CommentType.ANSWER));
    			user.addComment(p, c);
    			user.cancelCollect(p);
    		}
    		
    		if(i>8){
    			Post p = (Post)f.create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
    			u2.addPost(p);
    			user.joinActivity(p);
    		}
    	}
    	udao.update(user);
    	udao.update(u2);
    	em.getTransaction().commit();
    }
	
}
