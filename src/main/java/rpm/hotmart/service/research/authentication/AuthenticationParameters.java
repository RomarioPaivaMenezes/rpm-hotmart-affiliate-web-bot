package rpm.hotmart.service.research.authentication;

public class AuthenticationParameters {
	
	private String userSessionId = null;
	private String bearerAutorization = null;
	
	private final String userName = "romarioinf@gmail.com"; 
	private final String password = "Cristo@31"; 
	
	public AuthenticationParameters(String userSessionId, String bearerAutorization) {
		this.userSessionId = userSessionId;
		this.bearerAutorization = bearerAutorization;
	}

	public AuthenticationParameters() {
	}

	/**
	 * @return the userSessionId
	 */
	public String getUserSessionId() {
		return userSessionId;
	}

	/**
	 * @param userSessionId the userSessionId to set
	 */
	public void setUserSessionId(String userSessionId) {
		this.userSessionId = userSessionId;
	}

	/**
	 * @return the bearerAutorization
	 */
	public String getBearerAutorization() {
		return bearerAutorization;
	}

	/**
	 * @param bearerAutorization the bearerAutorization to set
	 */
	public void setBearerAutorization(String bearerAutorization) {
		this.bearerAutorization = bearerAutorization;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	

}
