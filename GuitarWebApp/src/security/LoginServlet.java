package security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import service.transactions.daoTransactions.DAOTransaction;
import service.transactions.daoTransactions.GetAccountTransaction;
import service.transactions.daoTransactions.UpdateAccountTransaction;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/security/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Account account = null;
		String userID = request.getParameter("userID");
		String password = request.getParameter("password");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);

		DAOTransaction transaction = new GetAccountTransaction();
		try {
			account = (Account) transaction.execute(params);
		} catch (Exception e) {
			System.out.println(e);
			response.setStatus(500);
			return;
		}

		if (account != null && !account.isProtected(new Date())) {
			if (password.equals(account.getPassword())) {
				HttpSession session = request.getSession();
				synchronized (session) {
					session.setAttribute("userID", account.getUserID());
					account.setAutoLoginSeriesNum(session.getId());
					Cookie cookie = new Cookie("ALG", session.getId());
					cookie.setHttpOnly(true);
					cookie.setPath("/GuitarWebApp");
					cookie.setMaxAge(15 * 24 * 60 * 60);
					String targetURL = "";
					synchronized (session) {
						targetURL = (String) session.getAttribute("targetURL");
						if (targetURL == null || targetURL.equals(""))
							targetURL = "/GuitarWebApp/pages/circle.jsp";
					}
					response.addCookie(cookie);
					response.sendRedirect(targetURL);
				}
			} else {
				account.setLastAccessDate(new Date());
				account.setChance((short) (account.getChance() - 1));
				response.sendRedirect("/GuitarWebApp/pages/signIn.jsp?invalid=true");
			}

			params.clear();
			params.put("account", account);
			DAOTransaction t = new UpdateAccountTransaction();
			try {
				t.execute(params);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendError(500);
				return;
			}

		} else {
			response.sendRedirect("/GuitarWebApp/pages/signIn.jsp?invalid=true");
		}

	}

}
