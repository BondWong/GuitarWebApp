package service.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import model.User;
import model.User.ShortCut;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetLoginUserShortCut
 */
@WebServlet("/GetLoginUserShortCut")
public class GetLoginUserShortCut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLoginUserShortCut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userID = request.getParameter("userID");
		
		HttpSession session = request.getSession();
		User.ShortCut shortCut = null;
		synchronized(session){
			shortCut = (ShortCut) session.getAttribute(userID);
		} 
		
		response.setContentType(MediaType.APPLICATION_JSON);
		response.getWriter().write(new Gson().toJson(shortCut));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
