package security;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.core.MediaType;

import security.validation.CommentRep;

import com.google.gson.Gson;

/**
 * Servlet Filter implementation class CommentRepValidationFilter
 */
//@WebFilter("/app/comment/add/*")
public class CommentRepValidationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CommentRepValidationFilter() {
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

		String postRepJson = request.getParameter("post");
		CommentRep commentRep = new Gson().fromJson(postRepJson, CommentRep.class);
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<CommentRep>> violations = new LinkedHashSet<ConstraintViolation<CommentRep>>(); 
		violations = validator.validate(commentRep);
		
		if(violations.size()==0){
			// pass the request along the filter chain
			chain.doFilter(request, response);
		} else{
			response.setContentType(MediaType.APPLICATION_JSON);
			response.getWriter().write(violations.iterator().next().getMessage());
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
