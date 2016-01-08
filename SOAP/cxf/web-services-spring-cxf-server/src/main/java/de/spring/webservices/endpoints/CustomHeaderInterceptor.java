package de.spring.webservices.endpoints;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpServletConnection;

public class CustomHeaderInterceptor implements EndpointInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomHeaderInterceptor.class);
	private static final String MY_CUSTOM_HEADER = "MY_CUSTOM_HEADER";

	@Override
	public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
		TransportContext transport = TransportContextHolder.getTransportContext();
	
		if (transport != null) {
			HttpServletConnection connection = (HttpServletConnection) transport.getConnection();
			HttpServletRequest headers = connection.getHttpServletRequest();
			String headerValue = headers.getHeader(MY_CUSTOM_HEADER);
			
			if (!StringUtils.isEmpty(headerValue)) {
				LOGGER.info("Custom header value: " + headerValue);
			}
		}
		
		return true;
	}

	@Override
	public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
		return true;
	}

	@Override
	public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
		return true;
	}

	@Override
	public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
		
	}

}
