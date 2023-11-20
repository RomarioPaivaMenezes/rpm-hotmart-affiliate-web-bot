package rpm.hotmart.service.research;

import org.json.JSONObject;

public class ResearchParametersManager {
	
	private String name = "";
	private int rows = 20;
	private String locale = "EN";
	private String currency = "USD";
	private String contryId = "United States";
	private String price = "RANGE_4";
	private String cookieType = "0";
	private String page = "1";
	
	public ResearchParametersManager setBrazilResearchParameter() {
		this.rows = 200;
		this.locale = "PT_BR";
		this.currency = "BLR";
		this.contryId = "Brazil";
		return this;
	}
	
	public ResearchParametersManager setAmericanResearchParameter() {
		this.rows = 200;
		this.locale = "EN";
		this.currency = "USD";
		this.contryId = "United States";
		return this;
	}
	
	public void setJsonRequestParameters(JSONObject researchJson) {
		researchJson.put("name", this.name);
		researchJson.put("rows", this.rows);
		researchJson.put("formatId", 8);
		researchJson.put("locale", this.locale);
		researchJson.put("currency", this.currency);
		researchJson.put("price", this.price);
		researchJson.put("cookieType", this.cookieType);
		researchJson.put("contryId", this.contryId);
		researchJson.put("page", this.page);
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}
	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the contryId
	 */
	public String getContryId() {
		return contryId;
	}
	/**
	 * @param contryId the contryId to set
	 */
	public void setContryId(String contryId) {
		this.contryId = contryId;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the cookieType
	 */
	public String getCookieType() {
		return cookieType;
	}
	/**
	 * @param cookieType the cookieType to set
	 */
	public void setCookieType(String cookieType) {
		this.cookieType = cookieType;
	}
	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}
	

}
