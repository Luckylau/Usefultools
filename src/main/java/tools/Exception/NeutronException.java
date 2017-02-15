package tools.Exception;
import com.google.gson.annotations.Expose;
/**
 * 
 * @author Jasonlau
 *
 */
public class NeutronException extends ApiException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8001024644819184349L;
	@Expose
    private String type;
    @Expose
    private String message;
    @Expose
    private String detail;
    
    public NeutronException(String type, String message, String detail) {
        super(type, message);
        this.type = type;
        this.message = message;
        this.detail = detail;
    }
    
    @Override
	public String getType() {
		return type;
	}

    @Override
	public void setType(String type) {
		this.type = type;
	}
    
    @Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
    
    



}
