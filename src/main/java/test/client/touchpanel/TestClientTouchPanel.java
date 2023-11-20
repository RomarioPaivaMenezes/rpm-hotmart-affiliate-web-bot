package test.client.touchpanel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.util.HttpHeaderNames;
import org.json.JSONObject;

public class TestClientTouchPanel {

	public static void main(String[] args) {
		runRequest();
	}

	static  void runRequest() {
		List<Thread> runList = new ArrayList<Thread>();

		for (int i = 0; i < 5; i++) {
			runList.add(new RequestThread(i));
		}

		for (Thread runnable : runList) {
			runnable.start();
		}
	}
	
	public synchronized static String touchPanelAuthentication() {
		Client client = ClientBuilder.newClient();

		UUID touchPanelId =UUID.randomUUID();

		String json = "{\r\n"
				+ "    \"username\":\"admin\", \r\n"
				+ "    \"password\": \"Passwd**\",\r\n"
				+ "    \"touchpanelId\":"+touchPanelId+"\r\n"
				+ "}";


		Response response = client.target(
				"http://localhost:8080/api/touchpanel/v1/auth")
				.request()
				.post(Entity.json(json));
		

		JSONObject jsonObject = new  JSONObject(response.readEntity(String.class));
		
		return "Bearer "+jsonObject.getString("token");
	}

	public static class RequestThread extends Thread{

		int t = 0;

		public RequestThread(int t) {
			this.t = t;
		}	

		@Override
		public void run() {
			
			Client client = ClientBuilder.newClient();
			System.out.println("*** Creating a touchPanel ***"+" Tread : "+t);

			String json = "{\r\n"
					+ "    \"touchpanelReleaseMajor\": 1,\r\n"
					+ "    \"touchpanelReleaseMinor\": 0,\r\n"
					+ "    \"touchpanelReleaseBuild\" : 2,\r\n"
					+ "    \"operatingSystem\":\"Windows\",\r\n"
					+ "    \"computerName\": \"Rmenezes_critical\",\r\n"
					+ "    \"ip\": \"107.0.0.1\",\r\n"
					+ "    \"screenWidth\" : \"122\",\r\n"
					+ "    \"screenHeight\" : \"233\",\r\n"
					+ "    \"physicalSize\": \"100\"\r\n"
					+ "}";

			Response response = client.target("http://localhost:8080/api/touchpanel/v1/configuration/environment")
					.request()
					.property("REMOTE_ADDR", "192.0.1.0")
					.header(HttpHeaderNames.AUTHORIZATION, touchPanelAuthentication())
					.put(Entity.json(json));

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed to create");
			} System.out.println(" Tread : "+ t +" Status 200: OK\n");

		}

	}
	
}
