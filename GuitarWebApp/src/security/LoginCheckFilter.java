package security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ProtectedURLManager;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
public class LoginCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String uri = httpRequest.getRequestURI();
		if(ProtectedURLManager.needLoginProtection(uri)){
		// pass the request along the filter chain
			HttpSession session = httpRequest.getSession();
			String userID = request.getParameter("userID");
			String sessionUserID = "";
			synchronized(session){
				sessionUserID = (String) session.getAttribute("userID");
			}
			if(userID!=null&&sessionUserID!=null&&userID.equals(sessionUserID)){
				chain.doFilter(request, response);
			} else{
				synchronized(session){
					session.setAttribute("targetURL", uri);
				}
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.setStatus(401);
			}
		} else{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
