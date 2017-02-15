package tools.Rest;

import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import tools.Handler.ApiErrorHandler;
import tools.Handler.NeutronErrorHandler;



/**
 * 
 * @author Jasonlau
 * @Email laujunbupt0913@163.com
 *
 */
public class RestFactory {
	private static final Logger logger = LoggerFactory.getLogger(RestFactory.class);
	
	public static RestTemplate getNeutronTemplate(){
		RestTemplate restTemplate=getBasicTemplate();
		restTemplate.setErrorHandler(new NeutronErrorHandler());
		ClientHttpRequestInterceptor neutronApiInterceptor =new NeutronApiInterceptor();
		restTemplate.setInterceptors(Collections.singletonList(neutronApiInterceptor));
		return restTemplate;
	}
	public static RestTemplate getBasicTemplate(){
		logger.info("RestTemplate 初始化开始");
		HttpClient httpclient=null;
		try {
			httpclient=HttpUtils.acceptUntrustedCertsHttpClient();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * ByteArrayHttpMessageConverter	数据与字节数组的相互转换
		 * StringHttpMessageConverter	数据与String类型的相互转换
		 * FormHttpMessageConverter	表单与MultiValueMap<string, string=””>的相互转换
		 * SourceHttpMessageConverter	数据与javax.xml.transform.Source的相互转换
		 * MarshallingHttpMessageConverter	使用Spring的Marshaller/Unmarshaller转换XML数据
		 * MappingJackson2HttpMessageConverter	使用Jackson的ObjectMapper转换Json数据
		 * MappingJackson2XmlHttpMessageConverter	使用Jackson的XmlMapper转换XML数据
		 * BufferedImageHttpMessageConverter	数据与java.awt.image.BufferedImage的相互转换
		 *
		 */
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpclient);
		requestFactory.setConnectionRequestTimeout(200);
		requestFactory.setConnectTimeout(5000);
		List<HttpMessageConverter<?>> messageConverters=new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        messageConverters.add(new BufferedImageHttpMessageConverter());
        RestTemplate restTemplate=new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(requestFactory);
        restTemplate.setErrorHandler(new ApiErrorHandler());
		logger.info("RestTemplate 初始化完成");
		return restTemplate;
	}
	
	

}
