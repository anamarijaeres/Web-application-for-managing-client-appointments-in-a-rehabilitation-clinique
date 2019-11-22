package opp.flow;

public class ResponseMessage {
	private ErrorCode error_code;
	private String message;
	private String username;
	
	public ResponseMessage(ErrorCode error_code, String message, String username) {
		super();
		this.error_code = error_code;
		this.message = message;
		this.username = username;
	}

	public ResponseMessage() {
		
	}

	public ErrorCode getError_code() {
		return error_code;
	}

	public void setError_code(ErrorCode error_code) {
		this.error_code = error_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
