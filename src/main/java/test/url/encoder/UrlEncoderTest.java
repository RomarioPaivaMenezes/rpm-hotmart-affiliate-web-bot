package test.url.encoder;

import java.net.URLEncoder;
import java.nio.charset.Charset;

public class UrlEncoderTest {

	public static void main(String[] args) {
		
		String url = "resources/graphics/Welcomé to moduWebVision.net!_component.png";
		System.err.println(url);
		url= URLEncoder.encode(url, "UTF-8");
	
		System.out.println(url);
	}
}
