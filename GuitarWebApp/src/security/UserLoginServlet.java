package security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import model.User;
import service.transactions.daoTransactions.DAOTransaction;
import service.transactions.daoTransactions.GetUserByIDandPasswordTransaction;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/security/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = null;
		String userID = request.getParameter("userID");
		String password = request.getParameter("password");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("password", password);
		
		DAOTransaction transaction = new GetUserByIDandPasswordTransaction();
		try{
			user = (User) transaction.execute(params);
		} catch(Exception e){
			System.out.println(e);
		}
		
		if(user!=null){
			HttpSession session = request.getSession();
			synchronized(session){
				session.setAttribute(user.getID(), user.getID());
			}
			response.setContentType(MediaType.APPLICATION_JSON);
			response.sendRedirect("/GuitarWebApp/pages/index.html");
		} else{

		}
		
	}

}
