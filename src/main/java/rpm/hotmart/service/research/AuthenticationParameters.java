package rpm.hotmart.service.research;

public class AuthenticationParameters {
	
	private String userSessionId = null;
	private String bearerAutorization = null;
	
	public AuthenticationParameters(String userSessionId, String bearerAutorization) {
		this.userSessionId = userSessionId;
		this.bearerAutorization = bearerAutorization;
	}

	/**
	 * @return the userSessionId
	 */
	public String getUserSessionId() {
		return userSessionId;
	}

	/**
	 * @return the bearerAutorization
	 */
	public String getBearerAutorization() {
		return bearerAutorization;
	}

}
