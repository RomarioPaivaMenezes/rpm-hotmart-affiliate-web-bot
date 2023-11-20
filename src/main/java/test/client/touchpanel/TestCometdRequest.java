package test.client.touchpanel;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class TestCometdRequest {

	public static void main(String[] args) {
		runRequest();
	}

	static  void runRequest() {
		List<Thread> runList = new ArrayList<Thread>();

		for (int i = 0; i < 1; i++) {
			runList.add(new RequestThread(i));
		}

		for (Thread runnable : runList) {
			runnable.start();
		}
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

			String json = "[{\"channel\":\"/meta/connect\",\"connectionType\":\"long-polling\",\"advice\":{\"timeout\":0},\"id\":\"33\",\"clientId\":\"181de8dwi0cmgt3ppoy6dxvmacg\"}]";

			Response response = client.target("http://localhost:8080/cometd/connect")
					.request()
					.cookie("header.payload", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxZTA3NWFhNy1jOTNhLTRkN2YtOWVhMy1kMThjNmQ3NjkyMTciLCJpYXQiOjE2OTIyNzA4MDgsInN1YiI6InVpIiwiaXNzIjoibW9kdVdlYlVuaXR5IiwidXNlcmlkIjowLCJ1c2VybmFtZSI6ImFkbWluIiwibmFtZSI6IkFkbWluaXN0cmF0b3IiLCJyb2xlIjowLCJ1c2VyU2V0dGluZ3MiOiJ7XCJkZWJ1Z1wiOnRydWV9IiwibGljSGFzaCI6LTE5ODUxNTMwMzgsImV4cCI6MTY5MjM1NzIwOH0")
					.cookie("signature", "IwRnDBYsOQPvHLFoEpNOhru_QDAlZANErG9k35yu7A")
					.post(Entity.json(json));

			if (response.getStatus() != 200) {
				System.out.println("Status: " + response.getStatus());
				throw new RuntimeException("Failed to create");
			} System.out.println(" Tread : "+ t +" Status 200: OK\n");

		}

	}
	
}
