package rpm.hotmart.service.research;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import rpm.hotmart.AppManager;

public class HotmartResearchClient {

public static JSONObject startAfilianteResearch(AppManager appManager, int page) {
		
		Client client = ClientBuilder.newBuilder()
                .property("connection.timeout", 100)
                .build();
		
		JSONObject researchJson = new JSONObject();
		
		ResearchParametersManager researchParametersManager = appManager.getResearchParametersManager();
		researchParametersManager.setJsonRequestParameters(researchJson);
		researchJson.put("page", page);
		//Ex. GA1.2.1671460792.1680122838
		researchJson.put("userSessionId", appManager.getAuthenticationParameters().getUserSessionId());
		
		String url = appManager.getUrl(researchJson);
		
		WebTarget myResource = client.target(url);
		Response response = myResource.request(MediaType.APPLICATION_JSON)
				 .header("User-Agent", UserAgents.POSTMAN.getAgent())
        		 .header("Authorization", appManager.getAuthenticationParameters().getBearerAutorization())
        		 .post(Entity.json(researchJson.toString()));
		
		
	    if (response.getStatus() != 200) {
	        throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	    }

	    JSONObject responseJson =  new JSONObject(response.readEntity(String.class));
	    response.close();
		client.close();
		
	    return responseJson;
	}

}
