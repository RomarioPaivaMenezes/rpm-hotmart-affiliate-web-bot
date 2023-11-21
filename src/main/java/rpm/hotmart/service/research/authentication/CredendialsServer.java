package rpm.hotmart.service.research.authentication;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.htmlunit.FailingHttpStatusCodeException;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlElement;
import org.htmlunit.html.HtmlPage;

import rpm.hotmart.AppManager;
import rpm.hotmart.service.research.HotmartURLs;

public class CredendialsServer  {

		// http://localhost:8080/RESTfulExample/json/product/post
		public void getCredentials(AppManager appManager) {
			
			Client client = ClientBuilder.newBuilder()
	                .property("connection.timeout", 100)
	                .build();
			
			javax.ws.rs.core.Form form = new Form();
			form.param("signUpProvider", "0");
			form.param("signUpProviderOk", "0");
			form.param("signUpUserProfile", "SELLER");
			form.param("signUpUrl", "");
			form.param("username", appManager.getAuthenticationParameters().getUsername());
			form.param("password", appManager.getAuthenticationParameters().getPassword());
			form.param("execution", getExecutionKey()); 
			form.param("_eventId", "submit");
			form.param("geolocation", "");
			
			WebTarget myResource = client.target(HotmartURLs.HOTMART_URL_LOGIN);
			Response response = myResource.request(MediaType.APPLICATION_JSON)
	        		 .post(Entity.form(form));
			
			NewCookie token = response.getCookies().get("hmVlcIntegration");
			NewCookie sessionId = response.getCookies().get("JSESSIONID");
			
			token.getValue();
			
			AuthenticationParameters authenticationParameters = 
					new AuthenticationParameters(sessionId.getValue(), "Bearer "+token.getValue());
			
			appManager.setAuthenticationParameters(authenticationParameters);
			
		    /*if (response.getStatus() != 200) {
		        throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		    }*/

		    response.close();
			client.close();
		}

	private String getExecutionKey() {
		
		String executionKey = "";
		try {
			WebClient webClient = new WebClient();
			webClient.getOptions().setJavaScriptEnabled(false);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			
			final HtmlPage page = webClient.getPage(HotmartURLs.HOTMART_URL_LOGIN);
			HtmlElement ownerAddressElement = (HtmlElement) page.getElementByName("execution");
			executionKey = ownerAddressElement.getAttribute("value");
			System.out.println(executionKey);
			
			webClient.close();
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return executionKey;
	}
	
}
