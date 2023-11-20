package rpm.hotmart.domain.product;

import rpm.hotmart.domain.affiliation.Affiliation;

public class Product {

	private int id;
	private String name;
	private String producerName;
	private float temperature;
	private float reviewRating;
	private float blueprint;
	private String locale;
	private ProductPrice price;
	private ProductTags productTags;
	private Affiliation affiliation;
	private String producerUcoder;
	private String productUcoder;

	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the producerUcoder
	 */
	public String getProducerUcoder() {
		return producerUcoder;
	}

	/**
	 * @param producerUcoder the producerUcoder to set
	 */
	public void setProducerUcoder(String producerUcoder) {
		this.producerUcoder = producerUcoder;
	}

	/**
	 * @return the productUcoder
	 */
	public String getProductUcoder() {
		return productUcoder;
	}

	/**
	 * @param productUcoder the productUcoder to set
	 */
	public void setProductUcoder(String productUcoder) {
		this.productUcoder = productUcoder;
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
	 * @return the producerName
	 */
	public String getProducerName() {
		return producerName;
	}

	/**
	 * @param producerName the producerName to set
	 */
	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	/**
	 * @return the temperature
	 */
	public float getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	/**
	 * @return the reviewRating
	 */
	public float getReviewRating() {
		return reviewRating;
	}

	/**
	 * @param reviewRating the reviewRating to set
	 */
	public void setReviewRating(float reviewRating) {
		this.reviewRating = reviewRating;
	}

	/**
	 * @return the blueprint
	 */
	public float getBlueprint() {
		return blueprint;
	}

	/**
	 * @param blueprint the blueprint to set
	 */
	public void setBlueprint(float blueprint) {
		this.blueprint = blueprint;
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
	 * @return the price
	 */
	public ProductPrice getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(ProductPrice price) {
		this.price = price;
	}

	/**
	 * @return the productTags
	 */
	public ProductTags getProductTags() {
		return productTags;
	}

	/**
	 * @param productTags the productTags to set
	 */
	public void setProductTags(ProductTags productTags) {
		this.productTags = productTags;
	}

	/**
	 * @return the affiliation
	 */
	public Affiliation getAffiliation() {
		return affiliation;
	}

	/**
	 * @param affiliation the affiliation to set
	 */
	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}
	
	
	

}
