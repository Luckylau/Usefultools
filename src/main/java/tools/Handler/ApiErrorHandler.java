package tools.Handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import tools.Exception.ApiException;
/**
 * 
 * @author Jasonlau
 *
 */
public class ApiErrorHandler implements ResponseErrorHandler {
	private static final Logger logger = LoggerFactory.getLogger(ApiErrorHandler.class);

	public void handleError(ClientHttpResponse clienthttpresponse) throws IOException {
		// TODO Auto-generated method stub
		HttpStatus status=clienthttpresponse.getStatusCode();
		logger.info("ApiErrorHandler 异常转译");
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

	public boolean hasError(ClientHttpResponse clienthttpresponse) throws IOException {
		// TODO Auto-generated method stub
		HttpStatus status = clienthttpresponse.getStatusCode();
		if(!status.is2xxSuccessful()){
			logger.error("捕获  API 调用异常信息：status=" + status + " - " + clienthttpresponse.getStatusText());
            if (clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {
                logger.debug("Returned a error 403 forbidden resposne ");
            }
            return true;
		}
		return false;
	}
	
}
