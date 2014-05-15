package security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HiddenCodeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public HiddenCodeFilter() {
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
		System.out.println("HiddenCodeFilter");
		String hiddenCode = request.getParameter("hiddenCode");
		String sessionHiddenCode = request.getParameter("hiddenCode");
		if(sessionHiddenCode!=null&&hiddenCode!=null&&hiddenCode.equals(sessionHiddenCode)){
			// pass the request along the filter chain
			chain.doFilter(request, response);
		} else{
			System.out.println("error");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
