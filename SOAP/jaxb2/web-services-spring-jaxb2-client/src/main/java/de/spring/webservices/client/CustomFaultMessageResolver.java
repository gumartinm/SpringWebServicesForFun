package de.spring.webservices.client;

import java.io.IOException;
import java.util.Iterator;

import javax.xml.transform.Source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.FaultMessageResolver;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.SoapFaultDetailElement;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.client.core.SoapFaultMessageResolver;

// maven-jaxb2-plugin for WSDL DOES generate again the objects in web-services-spring-jaxb2-globalxsds :(
// So I guess it is better to use the objects generated in this package
// than the ones from globalxsds even if they should be the same.
import de.spring.webservices.client.auto.GeneralFault;

/**
 * Enables us to log custom Fault remote messages. 
 *
 */
public class CustomFaultMessageResolver implements FaultMessageResolver {

private static final Logger LOGGER = LoggerFactory.getLogger(CustomFaultMessageResolver.class);
	
	private final FaultMessageResolver defaultMessageResolver = new SoapFaultMessageResolver();
	
	private Unmarshaller unmarshaller;
	
	@Override
	public void resolveFault(WebServiceMessage message) throws IOException {
		
		// Same behavior as default message resolver (SoapFaultMessageResolver) but this implementation also
		// logs error information.
		if (LOGGER.isErrorEnabled()) {
			try {
				logErrorInformation(message);
			} catch (Exception ex) {
				LOGGER.error("CustomFaultMessageResolver exception:", ex);
			}
		}
		
		defaultMessageResolver.resolveFault(message);
	}
	
	private void logErrorInformation(WebServiceMessage message) throws XmlMappingException, IOException {
		SoapMessage soapMessage = (SoapMessage) message;
		SoapBody body = soapMessage.getSoapBody();
		SoapFault soapFault = body != null ? body.getFault() : null;
		SoapFaultDetail detail = soapFault != null ? soapFault.getFaultDetail() : null;
		
		if (detail != null) {
			Iterator<SoapFaultDetailElement> iterator = detail.getDetailEntries();
			while (iterator.hasNext()) {
				SoapFaultDetailElement bodyElement = iterator.next();
				Source detailSource = bodyElement.getSource();
				// TODO: How to check if I am receiving GeneralFault before trying to unmarshal?
				//       Right now there will be exception if unmarshal doesn't return a GeneralFault object.
				GeneralFault error = (GeneralFault)this.unmarshaller.unmarshal(detailSource);
				LOGGER.error("TECHNICALERROR:");
				LOGGER.error(error.getTechnicalError());
				LOGGER.error("ELEMENTS:");
				error.getElements().forEach(element -> {
					LOGGER.error("MESSAGE: " + element.getMessage());
					LOGGER.error("MESSAGEARGS:");
					element.getMessageArgs().forEach(messageArg -> LOGGER.error(messageArg));
				});
			}
		}
	}
	
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
}
