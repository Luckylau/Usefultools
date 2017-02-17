package tools.Rest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

/**
 * 
 * @author Jasonlau
 * @Email laujunbupt0913@163.com
 */
public class NeutronApiInterceptor implements ClientHttpRequestInterceptor{
	private static final Logger logger = LoggerFactory.getLogger(NeutronApiInterceptor.class);
	/**
	 *  neutron 的header有6种类型的信息
	 *  X_USER_ID="";//Determine the user ID
	    X_PROJECT_ID="";//Determine the tenant
	    X_ROLES="user";//Suck out the roles
	    X_AUTH_TOKEN="";//Get the auth token
	    X_PROJECT_NAME="";//Human-friendly names
	    X_USER_NAME="";//Human-friendly names
	 * 
	 */
	private static final String X_USER_ID="luckylau";
	private static final String X_ROLES_USER="user";
	private static final String X_ROLES_ADMIN="admin";
	private static final String X_PROJECT_ID="tenant_id";

	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpRequestWrapper requestWrapper=new HttpRequestWrapper(request);
		/**Example:
		   http://10.0.38.113:9696/v2.0/fw/firewalls?tenant_id=liujuntest
		**/
		String queryString=request.getURI().getRawQuery();
		logger.info("request.getURI().getRawQuery():"+queryString);
		if(queryString!=null&&queryString.contains(X_PROJECT_ID)){
			List<NameValuePair> pairs = URLEncodedUtils.parse(queryString, Charset.forName("UTF-8"));
			for(NameValuePair pair:pairs){
				if(X_PROJECT_ID.equals(pair.getName())){
					requestWrapper.getHeaders().add("X_PROJECT_ID", pair.getValue());
					requestWrapper.getHeaders().add("X_ROLES", X_ROLES_USER);
					requestWrapper.getHeaders().add("X_USER_ID", X_USER_ID);
					return execution.execute(requestWrapper, body);
				}
				
			} 
		}
		requestWrapper.getHeaders().add("X_PROJECT_ID", "admin");
		requestWrapper.getHeaders().add("X_ROLES", X_ROLES_ADMIN);
		requestWrapper.getHeaders().add("X_USER_ID", X_USER_ID);
		return execution.execute(requestWrapper, body);
	}
	

}
