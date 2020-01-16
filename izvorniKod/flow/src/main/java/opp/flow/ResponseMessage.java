package opp.flow;

public class ResponseMessage {
	private ErrorCode error_code;
	private String message;
	private String username;
	private UserRole userRole;

	public ResponseMessage(ErrorCode error_code, String message, String username, UserRole userRole) {
		super();
		this.error_code = error_code;
		this.message = message;
		this.username = username;
		this.userRole=userRole;
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

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
}
