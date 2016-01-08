package de.spring.webservices.exceptions;

/**
 * This exception will be caught by org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver
 *
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -4042139454770293299L;

	public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
