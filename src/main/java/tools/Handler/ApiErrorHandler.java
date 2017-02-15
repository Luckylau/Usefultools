package tools.Handler;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class ApiErrorHandler implements ResponseErrorHandler {

	public void handleError(ClientHttpResponse arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public boolean hasError(ClientHttpResponse arg0) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
