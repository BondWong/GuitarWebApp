package security;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Servlet implementation class LoginServlet
 */
@SuppressWarnings("deprecation")
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String JWCURL = "http://jwc.jnu.edu.cn/web/login.aspx";
	private static final String VALIDATIONCODE = "http://jwc.jnu.edu.cn/web/ValidateCode.aspx";
    private static final String LOGINURL = "http://jwc.jnu.edu.cn/web/login.aspx";
	private static final String SUCCESSFLAG = "你输入的用户名与口令不相符合";
    /**
     * @see HttpServlet#HttpServlet()
     */
	private static ResponseHandler<String> responseHandler;
	
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	responseHandler = new ResponseHandler<String>(){
            public String handleResponse(final HttpResponse response) throws ClientProtocolException,IOException{
                HttpEntity entity = response.getEntity();
                return entity !=null ? EntityUtils.toString(entity) : null;
            }
        };
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		CloseableHttpClient httpClient = new DefaultHttpClient();
		
		HttpGet getPage = new HttpGet(JWCURL);
		httpClient.execute(getPage);
		getPage.abort();
		
		HttpGet getValCode = new HttpGet(VALIDATIONCODE);
		CloseableHttpResponse valCodeResponse = httpClient.execute(getValCode);
		
		try{
		HttpEntity entity = valCodeResponse.getEntity();
	    	if (entity != null) {
	        	InputStream instream = entity.getContent();
	        	try {
	            	// do something useful
	        		byte[] buffer = new byte[instream.available()];
	        		instream.read(buffer);
	        		instream.close();
	        		
	        		response.addHeader( "Content-length" ,  ""   +  buffer.length);
	        		response.setContentType( " application/octet-stream " );
	        		OutputStream toClient  =   new  BufferedOutputStream(response.getOutputStream());  
	        		toClient.write(buffer);
	        		toClient.flush();
	        		toClient.close();
	        		
	        	} finally {
	            	instream.close();
	        	}
	    	}
		} finally {
			valCodeResponse.close();
		}
		getValCode.abort();
		synchronized(session) {
			session.setAttribute("httpClient", httpClient);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String txtFJM = request.getParameter("valCode");
		String txtYHBS = request.getParameter("userID");
		String txtYHMM = "we made one up";
		String btnLogin = "登  录";
		String __EVENTVALIDATION = "/wEWBwKh6Oq+DwKDnbD2DALVp9zJDAKi+6bHDgKC3IeGDAKt86PwBQLv3aq9Bw==";
		String __VIEWSTATE = "/wEPDwUKMjA1ODgwODUwMg9kFgJmD2QWAgIBDw8WAh4EVGV4dAUk5pqo5Y2X5aSn5a2m57u85ZCI5pWZ5Yqh566h55CG57O757ufZGRk";
		
		HttpSession session = request.getSession();
		CloseableHttpClient httpClient = null;
		synchronized(session){
			httpClient = (CloseableHttpClient) session.getAttribute("httpClient");
		}
		
		HttpPost post = new HttpPost(LOGINURL);
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
	    nameValuePairs.add(new BasicNameValuePair("__EVENTVALIDATION",__EVENTVALIDATION));
	    nameValuePairs.add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));
	    nameValuePairs.add(new BasicNameValuePair("btnLogin", btnLogin));
	    nameValuePairs.add(new BasicNameValuePair("txtFJM", txtFJM));
	    nameValuePairs.add(new BasicNameValuePair("txtYHBS", txtYHBS));
	    nameValuePairs.add(new BasicNameValuePair("txtYHMM", txtYHMM));
	    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    
	    String responsePage = httpClient.execute(post, responseHandler);
		
		System.out.println(isOK(responsePage));
		
	}
	
	private boolean isOK(String responsePage) {
		Pattern p = Pattern.compile(SUCCESSFLAG);
		Matcher m = p.matcher(responsePage);
		return m.find();
	}

}
