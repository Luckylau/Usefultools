package tools.Exception;


import com.fasterxml.jackson.databind.JsonMappingException;

@SuppressWarnings("deprecation")
public class ApiException extends JsonMappingException {

	private static final long serialVersionUID = -1112091380788622184L;
	private String type;
	

	public ApiException(String msg) {
		super(msg);
	}
	public ApiException(String type, String msg) {
        super(msg);
        this.type = type;
    }

    
	public ApiException(String msg, Throwable rootCause) {
        super(msg, rootCause);
    }

    public ApiException(String type, String msg, Throwable rootCause) {
        super(msg, rootCause);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	
	

}
