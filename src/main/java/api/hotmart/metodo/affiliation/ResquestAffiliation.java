package api.hotmart.metodo.affiliation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import api.hotmart.metodo.search.Affiliation;
import api.hotmart.metodo.search.affiliation.AffiliationPrice;
import api.hotmart.metodo.search.affiliation.Comission;
import api.hotmart.metodo.search.produto.AffiliationRule;
import api.hotmart.metodo.search.produto.AffiliationType;
import api.hotmart.metodo.search.produto.ProductPrice;
import api.hotmart.metodo.search.produto.ProductTags;
import api.hotmart.metodo.search.produto.Produto;
public class ResquestAffiliation {
	
	private static List<Integer> negativeProduct = new ArrayList<Integer>();
	
	{ /*int[] negatives = {
			172952,
		173588,
		114447,
		185833,
		155750,
		187325,
		1016746,
		1769004,
		693221,
		1178152,
		194049,
		997148,
		161372,
		1977887,
		1016726,
		1013877,
		1014129,
		1014129,
		1047089,
		180492,
		182561,
		1025528,
		1826717,
		173669,
		185604,
		187447,
		1833674,
		1010471,
		1010471,
		117843,
		2638288,
		2638288,
		2621433,
		2621433,
		2703977,
		2703977,
		2519831,
		2519831,
		2674443,
		2674443,
		2537220,
		2658776,
		2658776,
		2038389,
		1817891,
		1130970,
		1013960,
		1013960,
		180527,
		1004713};
	
	List<Integer> list = Arrays
	    // cria o stream
	    .stream(negatives)
	    // convert int para Integer
	    .boxed()
	    // transforma em uma List
	    .collect(Collectors.toList());
	negativeProduct.addAll(list);*/
	
}
	public static void main(String[] args) {
		
		/*  return client
                .target(REST_URI)
                .path("medications/" + id)
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .get(Medication.class);
                */
		
		List<Produto> produtos = null; 
		JSONObject jSONObject = ConectionHotmartSearch(1);
		int page = jSONObject.getInt("totalPages");
		
		for (int i = 1; i < page; i++) {
			//System.out.println("Pagina = "+i);
			JSONObject jSONObject1 = ConectionHotmartSearch(i);
			produtos = parseJsonProdutosToObject(jSONObject1, produtos);
		}
		
		/*produtos.forEach(new Consumer<Produto>() {
			public void accept(Produto p) { 
				System.out.print("Produtor:" + p.getProducerName());
				System.out.print(";Nome do Produto:" + p.getName());
				System.out.print(";Temperatura:" + p.getTemperature());
				System.out.print(";Blueprint:" + p.getBlueprint());
				System.out.print(";Preço:" + p.getPrice().getValue());
				System.out.print(";Valor da Comissao:" + p.getAffiliation().getComission().getPrice().getValue());
				System.out.print(";Percentual da Comissao:" + p.getAffiliation().getComission().getPercentage());
				System.out.println();
			}
		});*/

	}

	private static JSONObject ConectionHotmartSearch(int page) {
		
		Client client = ClientBuilder.newBuilder()
                .property("connection.timeout", 100)
                .build();
		
		JSONObject json = new JSONObject();
		json.put("name", ""); //dearest//newest//hottest//recommended
		//URL varia de acordo com o tipo da pesquisa
		//https://api-affiliation-market.hotmart.com/v1/market/[latest]/search
		
		
		/*json.put("rows", 200);
		json.put("formatId", 8);
		//json.put("locale", "PT_BR");
		json.put("locale", "EN");
		json.put("countryId", "United States");
		json.put("currency","USD");
		json.put("page", page);
		json.put("userSessionId", "GA1.2.1671460792.1680122838");*/
		
		json.put("rows", 200);
		json.put("formatId", 8);
		json.put("locale", "PT_BR");
		//json.put("locale", "EN");
		//json.put("countryId", "United States");
	//	json.put("currency","BLR");
		json.put("page", page);
		json.put("userSessionId", "GA1.2.1671460792.1680122838");
		
		OrderBySearch orderBy = OrderBySearch.HOTTEST;
		json.put("name", orderBy.name().toLowerCase());
		//Pesquisa completa com todos os parametros - ou a maioria deles
		/*
		{"name":"","rows":20,"locale":"EN","currency":"EUR","countryId":"Portugal","price":"RANGE_4","cookieType":0,"page":"1","userSessionId":"GA1.2.1671460792.1680122838"}
		*/
		
		//Countries
		//https://api-affiliation-market.hotmart.com/v1/filter/countries
		
		//Languages
		//https://api-affiliation-market.hotmart.com/v1/filter/languages
		
		//Formatos do produto
		//https://api-affiliation-market.hotmart.com/v1/filter/formats

		String url = "https://api-affiliation-market.hotmart.com/v1/market/%filter%/search";
		
		switch (orderBy) {
		case HOTTEST:
			url = url.replace("%filter%", OrderBySearch.HOTTEST.name().toLowerCase());
			break;
		case DEAREST:
			url = url.replace("%filter%", OrderBySearch.DEAREST.name().toLowerCase());
			break;
		case LATEST:
			url = url.replace("%filter%", OrderBySearch.LATEST.name().toLowerCase());
			break;
		default:
			url = "https://api-affiliation-market.hotmart.com/v1/market/search";
			break;
		}
		
		WebTarget myResource = client.target(url);
		Response response = myResource.request(MediaType.APPLICATION_JSON)
				 .header("User-Agent", "PostmanRuntime/7.29.2")
        		 .header("Authorization", "Bearer H4sIAAAAAAAAAFWSW5OyOBCGf5FbIYCOl3I0DEkGCKfcTAmoHB1GUUh+/eLWt1W7F6mcuvrtfvs5C68u3LKhjRfFEimkQQ90C/XSRFvUjVlievu/zsJTSpiIDOp1kcbvYFFApS6H/nVK9SFwqylP+4c/kFfBpo67gSSMN7xROm7Vjc+COW/tiaaBTgRY8rZUiLQlttDCGW5805M53M88QttUeIKnToeaualUb6zcuMmiuTm5e+BnieQZWvWRxO0VEuugYzlFBVStn8wbC2EQnur9eUgklQ+NmtpCWPnELZJ+a89U9V5F6ohTuh+LgR/42sMpnZ+r9vT/v3yHWT4TGQPC8EzYYcbWZJaDI9f4HQU9De0+jeIwCeLlEsZ6HCUhp8ABp4y/qGLEGfAiqiQstB5azmwVp0jlJliomyurHzpx7SkfYoiFUuPUa7BMBsKMgbf2jsrDW1/DViwwu2rUCnTMJlqkJKL2CFjmvVLXgxUIh2ggsDryZ3g0RnqMVZx1//HmsZA2gKTNFcpyBVuHefVl9/aGWPHuz/m93jPoz8dDQ1sbUusAKQvW/fpAg6NUbv1aZw1OaSJROxaoJ0GQeD6zk0uSECf+BxreF0PYc3cv3rN7e1oevfrfe2wvRmLzr7hTLjHoj6xPgvf7GvPixwQUKnrX8IczT6AerNojOK1MoPYqeYp03Catn9oaacuJWLaCBVj7yIHP8mVlSccw7LAsF2Ku9Qzksdb7rJz9yN8wD8FM2r7DA1LXHCphaM3hDKQBGoY59FndcIvXpO3UXF4FFiv45tf9uREfz+7RGv1+BKBxx+9puE3dE5PbaJ8/hT5bljkuyw113xr/tdFuqaOtjws1+QZ7xLUVNLKt9c05Ch5ktDcfe6ZRv/s0L7rLrr8urxm8nnsnlJqrBRMLz/MpdvmH8fNhd/qXa9fAAD+06I+JAe9Qie2RX+sUCLZnQuBKbMtNQ+E1gE7aH6pbsG8DunV8p3toGb0c4GtjJZxL7bcz71wblyAIvNeSdHToK/3HDD14UWHuC8OOI4mdp0znqgVz3k/f27RbrN0tO//eL/fTIpMSKXP96Xv8Bx55l5fEeVKj+Fq27cdzJqfwpJy1rfr07/Ft0I6zLbe2QbsKX5Bodwo8NEqtHP4GmldRr3QEAAA=")
        		 .post(Entity.json(json.toString()));
		
		//https://api-affiliation-market.hotmart.com/v1/market/hottest/search
		
	    if (response.getStatus() != 200) {
	        throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	    }

	    JSONObject jSONObject =  new JSONObject(response.readEntity(String.class));
	    response.close();
		client.close();
		
	    return jSONObject;
	}

	private static List<Produto> parseJsonProdutosToObject(JSONObject jSONObject, List<Produto> produtos) {
		JSONArray jSONArray = jSONObject.getJSONArray("content");
	    
		if(produtos==null) {
			 produtos = new ArrayList<Produto>();
		}
	    
	    for(int i = 0; i < jSONArray.length(); i++) {
	    	JSONObject content = jSONArray.getJSONObject(i);
	    	JSONObject jsonProduto = content.getJSONObject("product");
	    	
	    	Produto produto = new Produto();
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
	    	Comission comission = new Comission();
	    	AffiliationPrice affiliationPrice = new AffiliationPrice();
	    	
	    	affiliation.setType(content.getJSONObject("affiliation").getInt("type"));
	    	
	    	affiliationPrice.setCurrency(content.getJSONObject("affiliation").getJSONObject("commission").getJSONObject("price").getString("currency"));
	    	affiliationPrice.setValue(content.getJSONObject("affiliation").getJSONObject("commission").getJSONObject("price").getFloat("value"));
	    	
	    	comission.setPrice(affiliationPrice);
	    	comission.setPercentage(content.getJSONObject("affiliation").getJSONObject("commission").getFloat("percentage"));
	    	
	    	affiliation.setComission(comission);
	    	produto.setAffiliation(affiliation);
	    	
	    	if(filterProduct(produto)) {
	    		/*System.out.print("Tipo de Afiliação: "+AffiliationType.APPLICATION);
		    	System.out.print(";Produtor:" + produto.getProducerName());
				System.out.print(";Nome do Produto:" + produto.getName());
				System.out.print(";Temperatura:" + produto.getTemperature());
				System.out.print(";Blueprint:" + produto.getBlueprint());
				System.out.print(";Preço:" + produto.getPrice().getValue());
				System.out.print(";Valor da Comissao:" + produto.getAffiliation().getComission().getPrice().getValue());
				System.out.print(";Percentual da Comissao:" + produto.getAffiliation().getComission().getPercentage());
				System.out.println(";https://app.hotmart.com/market/details?producerUcode="+produto.getProducerUcoder()+"&productUcode="+produto.getProductUcoder()+" ");*/
				
				System.out.print(""+AffiliationType.APPLICATION);
				System.out.print(";" + produto.getId());
				System.out.print(";" + produto.getProducerName());
				System.out.print(";" + produto.getName());
				System.out.print(";" + produto.getTemperature());
				System.out.print(";" + produto.getBlueprint());
				System.out.print(";" + produto.getPrice().getValue());
				System.out.print(";" + produto.getAffiliation().getComission().getPrice().getValue());
				System.out.print(";" + produto.getAffiliation().getComission().getPercentage());
				System.out.println(";https://app.hotmart.com/market/details?producerUcode="+produto.getProducerUcoder()+"&productUcode="+produto.getProductUcoder()+" ");
	    		//produtos.add(produto);
				negativeProduct.add(produto.getId());
	    	}

	    }
	    
	    return produtos;
	}
	
	private static boolean filterProduct(Produto produto) {
		
		boolean success = false;
		float value = produto.getAffiliation().getComission().getPrice().getValue();
		
		if(!negativeProduct.contains(produto.getId())) {
			success = true;
		}else return false;
		
		if(value >= 100) {
		//if(value >= 60 && value <= 300) {
			success = true;
		}else return false;
		
		if(produto.getTemperature() >= 20 && produto.getTemperature() <= 60) {
			success = true;
		}else return false;
		
		if(produto.getReviewRating() == 0 || produto.getReviewRating() >= 3) {
			success = true;
		}else return false;
		
		/*if(produto.getProductTags().getAffiliationType()==AffiliationType.ANYONE) {
			success = true;
		}else return false;*/
		
		/*if(produto.getBlueprint() >= 50) {
			success = true;
		}else return false;*/
		
		
		return success;
	}
	
	private boolean listIgnore(int idProduct) {
		return negativeProduct.contains(idProduct);
	}
	
	public enum OrderBySearch{
		HOTTEST, DEAREST, LATEST;
	}

}
