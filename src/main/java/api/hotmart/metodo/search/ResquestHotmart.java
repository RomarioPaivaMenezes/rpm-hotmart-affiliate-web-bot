package api.hotmart.metodo.search;

import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import api.hotmart.metodo.search.affiliation.AffiliationPrice;
import api.hotmart.metodo.search.affiliation.Comission;
import api.hotmart.metodo.search.produto.AffiliationRule;
import api.hotmart.metodo.search.produto.AffiliationType;
import api.hotmart.metodo.search.produto.ProductPrice;
import api.hotmart.metodo.search.produto.ProductTags;
import api.hotmart.metodo.search.produto.Produto;
public class ResquestHotmart {
	

	public static void main(String[] args) {
		
		/*  return client
                .target(REST_URI)
                .path("medications/" + id)
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .get(Medication.class);
                */
		
		List<Produto> produtos = null; 

		for (int page = 1; page < 500; page++) {
			//System.out.println("Pagina = "+page);
			JSONObject jSONObject = ConectionHotmartSearch(page);
			produtos = parseJsonProdutosToObject(jSONObject, produtos);
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
		json.put("name", "");
		json.put("rows", 100);
		json.put("locale", "PT_BR");
		json.put("page", page);
		json.put("userSessionId", "GA1.2.1671460792.1680122838");
		
		WebTarget myResource = client.target("https://api-affiliation-market.hotmart.com/v1/market/search");
		Response response = myResource.request(MediaType.APPLICATION_JSON)
				 .header("User-Agent", "PostmanRuntime/7.29.2")
        		 .header("Authorization", "Bearer H4sIAAAAAAAAAH1SyZKjOhD8Ik%2BAbIx9ZB8wkhosFnHpYLFBYvECNsvXD%2B547zCXOVQoFFXKSmXmZXaqzMoZZs45WGwRMbu3O1%2FKNXtv1%2Fc41Jzjr8vsiDkI5xhIVRYFn%2BE5A2KVt807jaTWs4qBRk3vtuidkV6Clj1CrixQExaoN8wlfotab8AWlSgTa8oNKdHLLV0chonBXM1ZKDiOydneR7MzJ5FZ22xkxda5F1bA4vPIUusouHG4JLG97rcXyEuAdEWCy3DOwFa%2Fxc49m1WURFJzacMFL%2F3s8nJy%2BTqr7SbIh39w%2FruHFwNAPReRHqz8gxHqiux1TpWDpsfL4JDad8K6McLAhLEQ4tBogogMamE1IFsGLzSPWsCHMBA8GVvGhBaTIeANkFQtOgsCJn7lRk6DOBwoMRnlUEx4MCYtnVbe42c%2F1pX1HRTXEqBuy6QVecCrU2GIcyZWrOgKI6nvvOh8kNX%2BmJJqnTVXPewd4t6ICZQhoQLiFCBSr6Ws%2F%2BjlFV%2FGujf9nP%2FVR%2Bskrkab3ybEyxEuHoBLvqy%2BVEXs37IfH5omn3%2F8IYFhns%2FCZMZiSPzQ%2FvgHVt3rJPLf%2BSdAH01jdab%2F3xsVeqFvEdFBsYgcX5DCQPsETV1198XCOs4%2FfseoyZsjy5lTrJiPwqo%2FWWMU2Gs%2B6I4yQaBLvax5amBEB0yChc5iDVtjBwFqYYQa2AYfPjxb%2BWadf00j74NRQ52umXMYmsUKLcHsEm9K%2BIqhoxYxYUdbD1BCJQTgDnLIrt4v%2FqWXXheEG7sm%2BNqIX8RK77eUy10KJCj681OTMiSNQhWf03e4g83eeqmRuekuh4P3G6iLqsRoo5z06uY%2F9mGXU%2FQEYYMjD3vpQy6nohyDSyCr5UXKsk3Kpw3qhXdB9WsYAq%2FCrjWc8ZWmr%2BmIv2c8%2FL7qr7Z8J8x%2FgMy4bI%2FTdDLlvpNVWLvql3w61dpetl%2B754O6oKj2%2FeGqR8pb%2BTZVyZ9WgnkaZbRU5448OFGfgRU%2FtVOtdPu6krN8n5UM5cms0acbEVLeoqrPrz2UtvIUbPJGD89XejjquA%2BmEj7vLFTenZO%2FNkZhFxtD20bJ7XGQYw29xm%2BMD7dCdN0yV5EvpypKv%2Fzy4P0B4KC3XHIEAAA%3D")
        		 .post(Entity.json(json.toString()));
		
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
	    
	    for(int i = 1; i < jSONArray.length(); i++) {
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
		    	System.out.print(";" + produto.getProducerName());
				System.out.print(";" + produto.getName());
				System.out.print(";" + produto.getTemperature());
				System.out.print(";" + produto.getBlueprint());
				System.out.print(";" + produto.getPrice().getValue());
				System.out.print(";" + produto.getAffiliation().getComission().getPrice().getValue());
				System.out.print(";" + produto.getAffiliation().getComission().getPercentage());
				System.out.println(";https://app.hotmart.com/market/details?producerUcode="+produto.getProducerUcoder()+"&productUcode="+produto.getProductUcoder()+" ");
				
				affiliationRequest(produto, "GA1.2.1671460792.1680122838");
	    		//produtos.add(produto);
	    	}

	    }
	    
	    return produtos;
	}
	
	private static boolean filterProduct(Produto produto) {
		
		boolean success = false;
		float value = produto.getAffiliation().getComission().getPrice().getValue();
		
		if(value >= 100 && value <= 300) {
			success = true;
		}else return false;
		
		if(produto.getTemperature() >= 20 && produto.getTemperature() <= 30) {
			success = true;
		}else return false;
		
		/*if(produto.getProductTags().getAffiliationType()==AffiliationType.APPLICATION) {
			success = true;
		}else return false;*/

		return success;
	}

	
	private static JSONObject affiliationRequest(Produto produto, String userSessionId) {
		
		Client client = ClientBuilder.newBuilder()
                .property("connection.timeout", 100)
                .build();
		String nullValue = null;
		
		JSONObject jsonRequestAfiliation = new JSONObject();
		jsonRequestAfiliation.put("userSessionId", userSessionId);
		jsonRequestAfiliation.put("searchId", nullValue);
		jsonRequestAfiliation.put("search", nullValue);
		jsonRequestAfiliation.put("productId", produto.getId());
		jsonRequestAfiliation.put("productTemperature", produto.getTemperature());
		jsonRequestAfiliation.put("productBlueprint", produto.getBlueprint());
		jsonRequestAfiliation.put("productCommission", produto.getAffiliation().getComission().getPercentage());
		jsonRequestAfiliation.put("productMaxPrice", produto.getPrice().getValue());
		jsonRequestAfiliation.put("subscriptionProduct", false);
		jsonRequestAfiliation.put("productRating", produto.getReviewRating());
		
		JSONObject filter = new JSONObject();
		filter.put("categoryId", nullValue);
		filter.put("formatId", 0);
		filter.put("affiliationType", nullValue);
		filter.put("commissionRange", nullValue);
		filter.put("currency", nullValue);
		filter.put("countryId", nullValue);
		filter.put("cookieType", nullValue);
		
		JSONArray productTools = new JSONArray();
		filter.put("productTools", productTools);
		
		WebTarget myResource = client.target("https://api-affiliation-market.hotmart.com/v1/market/affiliation-request");
		Response response = myResource.request(MediaType.APPLICATION_JSON)
				 .header("User-Agent", "PostmanRuntime/7.29.2")
        		 .header("Authorization", "Bearer H4sIAAAAAAAAAH1SyZKjOhD8Ik%2BAbIx9ZB8wkhosFnHpYLFBYvECNsvXD%2B547zCXOVQoFFXKSmXmZXaqzMoZZs45WGwRMbu3O1%2FKNXtv1%2Fc41Jzjr8vsiDkI5xhIVRYFn%2BE5A2KVt807jaTWs4qBRk3vtuidkV6Clj1CrixQExaoN8wlfotab8AWlSgTa8oNKdHLLV0chonBXM1ZKDiOydneR7MzJ5FZ22xkxda5F1bA4vPIUusouHG4JLG97rcXyEuAdEWCy3DOwFa%2Fxc49m1WURFJzacMFL%2F3s8nJy%2BTqr7SbIh39w%2FruHFwNAPReRHqz8gxHqiux1TpWDpsfL4JDad8K6McLAhLEQ4tBogogMamE1IFsGLzSPWsCHMBA8GVvGhBaTIeANkFQtOgsCJn7lRk6DOBwoMRnlUEx4MCYtnVbe42c%2F1pX1HRTXEqBuy6QVecCrU2GIcyZWrOgKI6nvvOh8kNX%2BmJJqnTVXPewd4t6ICZQhoQLiFCBSr6Ws%2F%2BjlFV%2FGujf9nP%2FVR%2Bskrkab3ybEyxEuHoBLvqy%2BVEXs37IfH5omn3%2F8IYFhns%2FCZMZiSPzQ%2FvgHVt3rJPLf%2BSdAH01jdab%2F3xsVeqFvEdFBsYgcX5DCQPsETV1198XCOs4%2FfseoyZsjy5lTrJiPwqo%2FWWMU2Gs%2B6I4yQaBLvax5amBEB0yChc5iDVtjBwFqYYQa2AYfPjxb%2BWadf00j74NRQ52umXMYmsUKLcHsEm9K%2BIqhoxYxYUdbD1BCJQTgDnLIrt4v%2FqWXXheEG7sm%2BNqIX8RK77eUy10KJCj681OTMiSNQhWf03e4g83eeqmRuekuh4P3G6iLqsRoo5z06uY%2F9mGXU%2FQEYYMjD3vpQy6nohyDSyCr5UXKsk3Kpw3qhXdB9WsYAq%2FCrjWc8ZWmr%2BmIv2c8%2FL7qr7Z8J8x%2FgMy4bI%2FTdDLlvpNVWLvql3w61dpetl%2B754O6oKj2%2FeGqR8pb%2BTZVyZ9WgnkaZbRU5448OFGfgRU%2FtVOtdPu6krN8n5UM5cms0acbEVLeoqrPrz2UtvIUbPJGD89XejjquA%2BmEj7vLFTenZO%2FNkZhFxtD20bJ7XGQYw29xm%2BMD7dCdN0yV5EvpypKv%2Fzy4P0B4KC3XHIEAAA%3D")
        		 .post(Entity.json(jsonRequestAfiliation.toString()));
		
	    if (response.getStatus() != 200) {
	    	System.out.println("Failed : HTTP error code : "+ response.getStatus());
//	        throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	    }else if(response.getStatus() == 200) {
	    	System.out.println("Solicitado a Afiliação do Produto: "+produto.getName());
	    }

	    JSONObject jSONObject =  new JSONObject(response.readEntity(String.class));
	    response.close();
		client.close();
		
	    return jSONObject;
	}
}
