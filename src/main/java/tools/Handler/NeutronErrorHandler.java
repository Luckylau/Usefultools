package tools.Handler;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import tools.Exception.ApiException;
import tools.Exception.NeutronException;
import tools.Rest.NetJSON;

public class NeutronErrorHandler implements ResponseErrorHandler {
	private static final Logger logger = LoggerFactory.getLogger(NeutronErrorHandler.class);

    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
    	String body=IOUtils.toString(clientHttpResponse.getBody());
    	HttpStatus status=clientHttpResponse.getStatusCode();
    	NeutronException exception=null;
    	try {
    		exception=NetJSON.fromJson(body, "NeutronError", NeutronException.class);
		} catch (Exception e) {
			/**
			 * 400 Bad Request
			 * 403 Forbidden 
			 * 404 Not Found
			 * 500 Internal Server Error
			 */
			if(HttpStatus.BAD_REQUEST.equals(status)){
				logger.error(HttpStatus.BAD_REQUEST + " response.");
	            throw new ApiException("请求无效！");
			}
			if(HttpStatus.FORBIDDEN.equals(status)){
				logger.error(HttpStatus.FORBIDDEN + " response.");
	            throw new ApiException("目标禁止访问！");
			}
			if(HttpStatus.NOT_FOUND.equals(status)){
				logger.error(HttpStatus.NOT_FOUND + " response.");
	            throw new ApiException("目标不存在！");
			}
			if(HttpStatus.INTERNAL_SERVER_ERROR.equals(status)){
				logger.error(HttpStatus.INTERNAL_SERVER_ERROR + " response.");
	            throw new ApiException("目标服务处理异常！");
			}
			if(status.is4xxClientError()){
				logger.error("客户端请求格式错误.");
	            throw new ApiException(status.name(), status.getReasonPhrase());
			}
			if(status.is5xxServerError()){
				logger.error(HttpStatus.INTERNAL_SERVER_ERROR + " response.");
	            throw new ApiException("目标服务处理异常！");
			}
		}
    	throw exception;
	
	
    }

    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
    	HttpStatus status = clientHttpResponse.getStatusCode();
		if(!status.is2xxSuccessful()){
			logger.error("捕获  API 调用异常信息：status=" + status + " - " + clientHttpResponse.getStatusText());
            if (clientHttpResponse.getStatusCode() == HttpStatus.FORBIDDEN) {
                logger.debug("Returned a error 403 forbidden resposne ");
            }
            return true;
		}
		return false;
    }
}
