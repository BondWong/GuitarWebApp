package security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Account;

import service.transactions.daoTransactions.DAOTransaction;
import service.transactions.daoTransactions.GetAccountBySeriesNumTransaction;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */
@WebFilter("/AutoLoginFilter")
public class AutoLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AutoLoginFilter() {
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
		Cookie[] cookies = httpRequest.getCookies();
		String autoLoginSeriesNum = "";
		for(int i=0; cookies!=null&&i<cookies.length; i++){
			if(cookies[i].getName().equals("ALG")){
				autoLoginSeriesNum = cookies[i].getValue();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("autoLoginSeriesNum", autoLoginSeriesNum);
				DAOTransaction transation = new GetAccountBySeriesNumTransaction();
				Account account = new Account();
				try {
					account = (Account) transation.execute(params);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				HttpSession session = httpRequest.getSession();
				synchronized(session){
					session.setAttribute(account.getUserID(),
						account.getUserID());
				}
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
