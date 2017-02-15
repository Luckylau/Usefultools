package tools.Rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * httpclient工具类
 * @author Jasonlau
 * @Email laujunbupt0913@163.com
 *
 */
public class HttpUtils {
	
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	
    private static final int MaxTotal = 200;
    private static final int MaxPerRoute = 100;
    
    public static Boolean testConnect(String url){
    	return null;
    }
    
    
    
	public static CloseableHttpClient acceptUntrustedCertsHttpClient() throws KeyManagementException, NoSuchAlgorithmException{
		
    	HttpClientBuilder httpClientBuilder=HttpClientBuilder.create();
    	//SSL验证策略：绕过证书，解决访问https问题
			SSLContext sslContext=createIgnoreVerifySSL();

    	//SSL验证策略：建立信任所有认证的策略
    	/**
    	try {
			SSLContext sslContext=SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy(){

				public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					// TODO Auto-generated method stub
					return true;
				}
				
			}).build();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}**/
    	//主机验证策略：关闭主机名验证，接受任何有效的和符合目标主机的SSL会话
    	HostnameVerifier hostnameVerifier=NoopHostnameVerifier.INSTANCE;
    	
    	SSLConnectionSocketFactory  sslConnectionSocketFactory =new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
    	
    	//设置协议http和https对应的处理socket链接工厂的对象
    	Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().
    			register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslConnectionSocketFactory)
				.build();
    	
    	PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
    	connMgr.setDefaultMaxPerRoute(MaxPerRoute);
    	connMgr.setMaxTotal(MaxTotal);
    	httpClientBuilder.setConnectionManager(connMgr);
    	httpClientBuilder.setSSLContext(sslContext);
 
    	
    	CloseableHttpClient closeableHttpClient=httpClientBuilder.build();
		return closeableHttpClient;
    	
    }
	
	public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException{
		SSLContext sc = SSLContext.getInstance("SSLv3"); 
		X509TrustManager trustManager=new X509TrustManager() {
			
			public X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// TODO Auto-generated method stub
				
			}
			
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// TODO Auto-generated method stub
				
			}
		};
		sc.init(null, new TrustManager[]{trustManager}, null);
		return sc;
	}

}
