package tools.Net;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import tools.Rest.RestFactory;
/**
 * 封装neutron api
 * @author Jasonlau
 * @Email laujunbupt0913@163.com
 * 
 *
 */
public class NetApi {
	
	private static final Logger logger = LoggerFactory.getLogger(NetApi.class);
	private static RestTemplate restTemplate=RestFactory.getNeutronTemplate();
	private static String REST_POINT="http://10.0.38.113:9696/v2.0";
	
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
	        return null;
	}
	/**
	 * get操作
	 * @param serviceUrl
	 * @return
	 */
	public static String doGet(String serviceUrl){
		return null;
		
	}
	/**
	 * post 操作
	 * @param serviceUrl
	 * @param requestobj
	 * @return
	 */
	public static String doPost(String serviceUrl,Object requestobj){
		return null;
		
	}
	
	/**
	 * put 操作
	 * @param serviceUrl
	 * @param requestobj
	 * @return
	 */
	public static void doPut(String serviceUrl,Object requestobj){
		
	}
	/**
	 * delete 操作
	 * @param serviceUrl
	 * @param requestobj
	 * @param paraMap
	 */
    public static void doDelete(String serviceUrl,Object requestobj,Map<String, ?> paraMap){
		
	}
	
	
	
	
   
}
