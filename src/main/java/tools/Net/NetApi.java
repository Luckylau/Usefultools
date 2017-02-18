package tools.Net;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import tools.Exception.ApiException;
import tools.Rest.HttpUtils;
import tools.Rest.RestFactory;
/**
 * 封装neutron api,数据传输用json
 * @author Jasonlau
 * @Email laujunbupt0913@163.com
 * 
 *
 */
public class NetApi {
	
	private static final Logger logger = LoggerFactory.getLogger(NetApi.class);
	private static RestTemplate restTemplate=RestFactory.getNeutronTemplate();
	public static String REST_POINT="http://10.0.38.113:9696/v2.0";
	
	/**
	 * 获取和设置rest服务地址
	 * @param url
	 */
	public static void setRestpoint(String url) {
		REST_POINT = url;
	}
	
	public static String getRestpoint(){
		return REST_POINT;
	}
	
	/**
	 * 测试网络服务的连通性
	 * @param serviceUrl
	 * @return
	 */
	public static Boolean testConnect(String serviceUrl) {
		return HttpUtils.testConnect(serviceUrl);
	}
	/**
	 * neutron 封装 get操作
	 * @param serviceUrl
	 * @return
	 * @throws ApiException 
	 */
	public static String doGet(String tenantId,String serviceUrl) throws ApiException{
		String result=null;
		if(!StringUtils.isEmpty(tenantId)){
			serviceUrl=serviceUrl+"?tenant_id="+tenantId;
		}
		try {
			result=doGet(serviceUrl, String.class);
		} catch (RestClientException e) {
			logger.error("Get 请求出错 url="+serviceUrl);
			throw new ApiException(e.getRootCause().getMessage());
		}
		return result;
		
	}
	/**
	 * 通用  get操作
	 * @param url
	 * @param responseType
	 * @param urlVariables
	 * @return
	 * @throws ApiException 
	 */
	public static <T> T doGet(String url, Class<T> responseType, Map<String, ?> urlVariables) throws ApiException{
		T result=null;
		try {
			result=restTemplate.getForObject(url, responseType, urlVariables);
		} catch (RestClientException e) {
			// TODO: handle exception
			logger.error("Get 请求出错 url="+url);
			throw new ApiException(e.getRootCause().getMessage());
		}
		
		return result;
		
	}
	/**
	 * 通用  get操作
	 * @param url
	 * @param responseType
	 * @return
	 * @throws ApiException 
	 */
	public static <T> T doGet(String url, Class<T> responseType) throws ApiException{
		T result=null;
		try {
			result=restTemplate.getForObject(url, responseType);
		} catch (RestClientException e) {
			// TODO: handle exception
			logger.error("Get 请求出错 url="+url);
			throw new ApiException(e.getRootCause().getMessage());
		}
		
		return result;
		
	}
	/**
	 * neutron 封装post 操作
	 * @param serviceUrl
	 * @param requestobj
	 * @return
	 * @throws ApiException 
	 */
	public static String doPost(String tenantId,String serviceUrl,Object requestobj) throws ApiException{
		String result=null;
		if(!StringUtils.isEmpty(tenantId)){
			serviceUrl=serviceUrl+"?tenant_id="+tenantId;
		}
		result=doPost(serviceUrl, requestobj, String.class);
		return result;
		
	}
	
	/**
	 * 通用  post操作
	 * @param url
	 * @param request
	 * @param responseType
	 * @return
	 * @throws ApiException
	 */
	public static <T> T doPost(String url,Object request, Class<T> responseType) throws ApiException{
		T result=null;
		try {
			result=restTemplate.postForObject(url, request, responseType);
		} catch (RestClientException e) {
			// TODO: handle exception
			logger.error("Post 请求出错 url="+url);
			throw new ApiException(e.getRootCause().getMessage());
		}
		
		return result;
	}
	
	/**
	 * 通用 post操作
	 * @param url
	 * @param request
	 * @param responseType
	 * @param urlVariables
	 * @return
	 * @throws ApiException
	 */
	public static <T> T doPost(String url,Object request, Class<T> responseType,Map<String, ?> urlVariables) throws ApiException{
		T result=null;
		try {
			restTemplate.postForObject(url, request, responseType, urlVariables);
		} catch (RestClientException e) {
			// TODO: handle exception
			logger.error("Post 请求出错 url="+url);
			throw new ApiException(e.getRootCause().getMessage());
		}
		
		return result;
	}
	
	/**
	 * put 操作
	 * @param serviceUrl
	 * @param requestobj
	 * @return
	 * @throws ApiException 
	 */
	public static void doPut(String tenantId,String serviceUrl,Object requestobj) throws ApiException{
		if(!StringUtils.isEmpty(tenantId)){
			serviceUrl=serviceUrl+"?tenant_id="+tenantId;
		}
		try {
			doPut(serviceUrl, requestobj);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			logger.error("Put 请求出错 url="+serviceUrl);
			throw new ApiException(e.getRootCause().getMessage());
		}
	}
	
    /**
     * 通用  put操作
     * @param url
     * @param request
     * @throws ApiException
     */
	public static void doPut(String url,Object request) throws ApiException{
		try {
			restTemplate.put(url, request);;
		} catch (RestClientException e) {
			// TODO: handle exception
			logger.error("Put 请求出错 url="+url);
			throw new ApiException(e.getRootCause().getMessage());
		}
	}
	
    /**
     * 通用  put操作
     * @param url
     * @param request
     * @param urlVariables
     * @throws ApiException
     */
	public static void doPut(String url,Object request,Map<String, ?> urlVariables) throws ApiException{
		try {
			restTemplate.put(url, request, urlVariables);
		} catch (RestClientException e) {
			// TODO: handle exception
			logger.error("Put 请求出错 url="+url);
			throw new ApiException(e.getRootCause().getMessage());
		}
	}
	
	public static void doDelete(String tenantId,String serviceUrl) throws ApiException{
		if(!StringUtils.isEmpty(tenantId)){
			serviceUrl=serviceUrl+"?tenant_id="+tenantId;
		}
		try {
			doDelete(serviceUrl);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			logger.error("Delete 请求出错 url="+serviceUrl);
			throw new ApiException(e.getRootCause().getMessage());
		}
	}
	
	/**
	 * 通用delete 操作
	 * @param serviceUrl
	 * @param requestobj
	 * @param paraMap
	 * @throws ApiException 
	 */
    public static void doDelete(String serviceUrl,Map<String, ?> urlVariables) throws ApiException{
    	try {
			restTemplate.delete(serviceUrl, urlVariables);
		} catch (RestClientException e) {
			// TODO: handle exception
			logger.error("Delete 请求出错 url="+serviceUrl);
			throw new ApiException(e.getRootCause().getMessage());
		}
	}
    /**
     * 通用delete 操作
     * @param serviceUrl
     * @throws ApiException
     */
    public static void doDelete(String serviceUrl) throws ApiException{
    	try {
			restTemplate.delete(serviceUrl);
		} catch (RestClientException e) {
			// TODO: handle exception
			logger.error("Delete 请求出错 url="+serviceUrl);
			throw new ApiException(e.getRootCause().getMessage());
		}
	}
	
	
	
	
   
}
