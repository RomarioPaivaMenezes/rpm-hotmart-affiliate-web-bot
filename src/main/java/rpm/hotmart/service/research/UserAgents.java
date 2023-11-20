package rpm.hotmart.service.research;

public enum UserAgents {

	POSTMAN("PostmanRuntime/7.29.2");
	
	private String agent = null;
	
	private UserAgents(String userAgent) {
		this.agent = userAgent;
	}

	/**
	 * @return the userAgent
	 */
	public String getAgent() {
		return agent;
	}
}
