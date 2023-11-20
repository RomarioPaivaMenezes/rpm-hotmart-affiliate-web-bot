package rpm.hotmart.service.research.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import rpm.hotmart.domain.affiliation.Affiliation;
import rpm.hotmart.domain.affiliation.AffiliationPrice;
import rpm.hotmart.domain.affiliation.Comission;
import rpm.hotmart.domain.product.AffiliationType;
import rpm.hotmart.domain.product.Product;
import rpm.hotmart.domain.product.ProductPrice;
import rpm.hotmart.domain.product.ProductTags;

public class ProductResearchUtil {

	public static List<Product> parseJsonProdutosToObject(JSONObject productResponse, List<Product> products, List<Integer> negativeProduct) {
		JSONArray jSONArray = productResponse.getJSONArray("content");
	    
		if(products==null) {
			 products = new ArrayList<Product>();
		}
	    
	    for(int i = 0; i < jSONArray.length(); i++) {
	    	JSONObject content = jSONArray.getJSONObject(i);
	    	JSONObject jsonProduto = content.getJSONObject("product");
	    	
	    	Product produto = new Product();
	    	produto.setId(jsonProduto.getInt("id"));
	    	produto.setBlueprint(jsonProduto.getFloat("blueprint"));
	    	produto.setLocale(jsonProduto.getString("locale"));
	    	produto.setName(jsonProduto.getString("name"));
	    	produto.setProductUcoder(jsonProduto.getString("ucode"));
	    	produto.setProducerName(content.getJSONObject("producer").getString("name"));
	    	produto.setProducerUcoder(content.getJSONObject("producer").getString("ucode"));
	    	
	    	produto.setReviewRating(jsonProduto.getFloat("reviewRating"));
	    	produto.setTemperature(jsonProduto.getFloat("temperature"));
	    	
	    	ProductPrice price = new ProductPrice();
	    	jsonProduto.getJSONObject("price");
	    	price.setCurrency(jsonProduto.getJSONObject("price").getString("currency"));
	    	price.setValue(jsonProduto.getJSONObject("price").getFloat("value"));
	    	produto.setPrice(price);
	    	
	    	ProductTags productTags = new ProductTags();
	    	productTags.setAffiliationCookieDuration(jsonProduto.getJSONObject("tags").getInt("affiliationCookieDuration"));
	    	//productTags.setAffiliationRule(AffiliationRule.valueOf(jsonProduto.getJSONObject("tags").getString("affiliationRule")));
	    	productTags.setAffiliationType(AffiliationType.valueOf(jsonProduto.getJSONObject("tags").getString("affiliationType")));
	    	productTags.setBohasAlternativePage(jsonProduto.getJSONObject("tags").getBoolean("hasAlternativePage"));
	    	productTags.setHasAffiliationResource(jsonProduto.getJSONObject("tags").getBoolean("hasAffiliationResource"));
	    	productTags.setHasGlobalAffiliation(jsonProduto.getJSONObject("tags").getBoolean("hasGlobalAffiliation"));
	    	productTags.setHasHotleads(jsonProduto.getJSONObject("tags").getBoolean("hasHotleads"));
	    	produto.setProductTags(productTags);
	    	
	    	Affiliation affiliation = new Affiliation();
	    	affiliation.setType(content.getJSONObject("affiliation").getInt("type"));

	    	AffiliationPrice affiliationPrice = new AffiliationPrice();
	    	affiliationPrice.setCurrency(content.getJSONObject("affiliation").getJSONObject("commission").getJSONObject("price").getString("currency"));
	    	affiliationPrice.setValue(content.getJSONObject("affiliation").getJSONObject("commission").getJSONObject("price").getFloat("value"));
	    	
	    	Comission comission = new Comission();
	    	comission.setPrice(affiliationPrice);
	    	comission.setPercentage(content.getJSONObject("affiliation").getJSONObject("commission").getFloat("percentage"));
	    	
	    	affiliation.setComission(comission);
	    	produto.setAffiliation(affiliation);
	    	
	    	if(ResearchFilters.filterProduct(produto, negativeProduct)) {
	    		products.add(produto);
				negativeProduct.add(produto.getId());
	    	}
	    }
	    return products;
	}
	
}
