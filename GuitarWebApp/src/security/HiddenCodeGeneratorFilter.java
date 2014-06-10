package security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import utils.ProtectedURLManager;

/**
 * Servlet Filter implementation class HiddenCodeGeneratorFilter
 */
public class HiddenCodeGeneratorFilter implements Filter {

    /**
     * Default constructor. 
     */
    public HiddenCodeGeneratorFilter() {
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
		if(ProtectedURLManager.needHiddenCodeGeneration(uri)){
			String hiddenCode = System.currentTimeMillis() + "";
			HttpSession session = httpRequest.getSession();
			System.out.println("hiddenCode:"+hiddenCode);
			synchronized(session){
				session.setAttribute("hiddenCode", hiddenCode);
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
