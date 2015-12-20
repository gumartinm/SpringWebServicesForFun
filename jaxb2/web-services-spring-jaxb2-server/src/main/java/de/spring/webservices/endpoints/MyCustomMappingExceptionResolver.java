package de.spring.webservices.endpoints;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.transform.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Marshaller;
import org.springframework.util.CollectionUtils;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.AbstractSoapFaultDefinitionExceptionResolver;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinitionEditor;

import de.spring.webservices.auto.Element;
import de.spring.webservices.auto.ExampleFault;
import de.spring.webservices.exceptions.CustomBusinessException;

public class MyCustomExceptionResolver extends AbstractSoapFaultDefinitionExceptionResolver {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyCustomExceptionResolver.class);
	
	private Marshaller marshaller;

	private Map<String, String> exceptionMappings = new LinkedHashMap<>();

	/**
	 * Set the mappings between exception class names and SOAP Faults. The exception class name can be a substring, with
	 * no wildcard support at present.
	 *
	 * <p>The values of the given properties object should use the format described in
	 * {@code SoapFaultDefinitionEditor}.
	 *
	 * <p>Follows the same matching algorithm as {@code SimpleMappingExceptionResolver}.
	 *
	 * @param mappings exception patterns (can also be fully qualified class names) as keys, fault definition texts as
	 *				   values
	 * @see SoapFaultDefinitionEditor
	 */
	public void setExceptionMappings(Properties mappings) {
		for (Map.Entry<Object, Object> entry : mappings.entrySet()) {
			if (entry.getKey() instanceof String && entry.getValue() instanceof String) {
				exceptionMappings.put((String)entry.getKey(), (String)entry.getValue());
			}
		}
	}
	
	@Override
	protected SoapFaultDefinition getFaultDefinition(Object endpoint, Exception ex) {
		if (!CollectionUtils.isEmpty(exceptionMappings)) {
			String definitionText = null;
			int deepest = Integer.MAX_VALUE;
			for (String exceptionMapping : exceptionMappings.keySet()) {
				int depth = getDepth(exceptionMapping, ex);
				if (depth >= 0 && depth < deepest) {
					deepest = depth;
					definitionText = exceptionMappings.get(exceptionMapping);
				}
			}
			if (definitionText != null) {
				SoapFaultDefinitionEditor editor = new SoapFaultDefinitionEditor();
				editor.setAsText(definitionText);
				return (SoapFaultDefinition) editor.getValue();
			}
		}
		return null;
	}

	/**
	 * Return the depth to the superclass matching. {@code 0} means ex matches exactly. Returns {@code -1} if
	 * there's no match. Otherwise, returns depth. Lowest depth wins.
	 *
	 * <p>Follows the same algorithm as RollbackRuleAttribute, and SimpleMappingExceptionResolver
	 */
	protected int getDepth(String exceptionMapping, Exception ex) {
		return getDepth(exceptionMapping, ex.getClass(), 0);
	}

	@SuppressWarnings("unchecked")
	private int getDepth(String exceptionMapping, Class<? extends Exception> exceptionClass, int depth) {
		if (exceptionClass.getName().indexOf(exceptionMapping) != -1) {
			return depth;
		}
		if (exceptionClass.equals(Throwable.class)) {
			return -1;
		}
		return getDepth(exceptionMapping, (Class<? extends Exception>) exceptionClass.getSuperclass(), depth + 1);
	}

	protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
		SoapFaultDetail detail = fault.addFaultDetail();
		Result result = detail.getResult();
		
		Element element = new Element();
		element.setMessage(ex.getMessage());
		
		if (ex instanceof CustomBusinessException) {
			List<String> arguments = element.getMessageArgs();
			arguments.add("ARGUMENT 1");
			arguments.add("ARGUMENT 2");
		}
		
		ExampleFault customFault = new ExampleFault();
		customFault.setTechnicalError(getStackTrace(ex));
		List<Element> elements = customFault.getElements();
		elements.add(element);
		
		try {
			marshaller.marshal(customFault, result);
		} catch (Exception marshallEx) {
			LOGGER.error("", marshallEx);
		}
	}
	
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}
	
	private static String getStackTrace(final Throwable throwable) {
	     final StringWriter sw = new StringWriter();
	     final PrintWriter pw = new PrintWriter(sw, true);
	     throwable.printStackTrace(pw);
	     return sw.getBuffer().toString();
	}
}
