package rpm.hotmart;

import org.json.JSONObject;

import rpm.hotmart.service.research.HotmartURLs;
import rpm.hotmart.service.research.ResearchOrderBy;
import rpm.hotmart.service.research.ResearchParametersManager;
import rpm.hotmart.service.research.authentication.AuthenticationParameters;
import rpm.hotmart.service.research.authentication.CredendialsServer;

public class AppManager {

	private ResearchOrderBy researchOrder = null;
	private AuthenticationParameters authenticationParameters = new AuthenticationParameters();
	private ResearchParametersManager researchParametersManager = null;
	
	public AppManager(ResearchOrderBy researchOrder, ResearchParametersManager researchParametersManager) {
		this.researchOrder = researchOrder;
		this.researchParametersManager = researchParametersManager;
		CredendialsServer CredendialsServer = new CredendialsServer();
		CredendialsServer.getCredentials(this);
	}

	//All parameters for research. OBS: there may be others
			/*
			{"name":"","rows":20,"locale":"EN","currency":"EUR","countryId":"Portugal","price":"RANGE_4","cookieType":0,"page":"1","userSessionId":"GA1.2.1671460792.1680122838"}
	*/
	public String getUrl(JSONObject researchJson) {
		researchJson.put("name", this.researchOrder.name().toLowerCase());
		
		//URL varies depending on the type of search
		//https://api-affiliation-market.hotmart.com/v1/market/[latest]/search
		String url = HotmartURLs.HOTMART_URL_FILTERS_SEARCH;
		
		switch (this.researchOrder) {
		case HOTTEST:
			url = url.replace("%filter%", ResearchOrderBy.HOTTEST.name().toLowerCase());
			break;
		case DEAREST:
			url = url.replace("%filter%", ResearchOrderBy.DEAREST.name().toLowerCase());
			break;
		case LATEST:
			url = url.replace("%filter%", ResearchOrderBy.LATEST.name().toLowerCase());
			break;
		default:
			url = HotmartURLs.HOTMART_URL_SEARCH;
			break;
		}
		
		return url;
	}
	
	/**
	 * @return the researchOrder
	 */
	public ResearchOrderBy getResearchOrder() {
		return researchOrder;
	}

	/**
	 * @return the authenticationParameters
	 */
	public AuthenticationParameters getAuthenticationParameters() {
		return authenticationParameters;
	}
	
	/**
	 * @param authenticationParameters the authenticationParameters to set
	 */
	public void setAuthenticationParameters(AuthenticationParameters authenticationParameters) {
		this.authenticationParameters = authenticationParameters;
	}

	/**
	 * @return the researchParametersManager
	 */
	public ResearchParametersManager getResearchParametersManager() {
		return researchParametersManager;
	}
	
	
	
}
