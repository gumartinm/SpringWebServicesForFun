package de.spring.webservices.client.transport.http;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.ws.transport.WebServiceConnection;
import org.springframework.ws.transport.http.AbstractHttpWebServiceMessageSender;
import org.springframework.ws.transport.http.ClientHttpRequestConnection;
import org.springframework.ws.transport.http.HttpTransportConstants;


/**
 * Based on ClientHttpRequestMessageSender from the Spring WS framework.
 * 
 * <p>
 * Spring WS framework also provides implementations based on the HTTP clients by Jakarta and Apache HttpClient: 
 * {@link https://hc.apache.org/httpcomponents-client-ga/} and {@link http://hc.apache.org/httpclient-3.x/}
 * </p>
 * 
 * <p>
 * Four implementations for four HTTP clients:
 * <ul>
 * <li> org.springframework.ws.transport.http.ClientHttpRequestMessageSender (ClientHttpRequestFactory from the Spring framework) </li>
 * <li> org.springframework.ws.transport.http.CommonsHttpMessageSender  (Jakarta implementation) </li>
 * <li> org.springframework.ws.transport.http.HttpComponentsMessageSender (Apache HttpClient) </li>
 * <li> org.springframework.ws.transport.http.HttpUrlConnectionMessageSender 
 * (org.springframework.ws.transport.http.HttpUrlConnection internal Spring framework implementation) </li>
 * </ul>
 * </p>
 */
public class CustomHttpRequestMessageSender extends AbstractHttpWebServiceMessageSender {
	private static final String MY_CUSTOM_HEADER = "MY_CUSTOM_HEADER";

	private final ClientHttpRequestFactory requestFactory;

	public CustomHttpRequestMessageSender() {
		requestFactory = new SimpleClientHttpRequestFactory();
	}

	@Override
	public WebServiceConnection createConnection(URI uri) throws IOException {
		ClientHttpRequest request = requestFactory.createRequest(uri, HttpMethod.POST);
		if (isAcceptGzipEncoding()) {
			request.getHeaders().add(HttpTransportConstants.HEADER_ACCEPT_ENCODING,
					HttpTransportConstants.CONTENT_ENCODING_GZIP);
		}
		
		request.getHeaders().add(MY_CUSTOM_HEADER, "gumartinm.name");
		
		return new ClientHttpRequestConnection(request);
	}
	
}
