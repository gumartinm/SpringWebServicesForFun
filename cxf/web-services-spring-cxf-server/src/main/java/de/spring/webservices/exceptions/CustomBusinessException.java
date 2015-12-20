package de.spring.webservices.exceptions;

import java.util.List;

/**
 * This exception will be caught by de.spring.webservices.endpoints.MyCustomExceptionResolver
 *
 */
public class CustomBusinessException extends RuntimeException {
	private final List<String> arguments;

	public CustomBusinessException(List<String> arguments) {
		super();
		this.arguments = arguments;
    }

    public CustomBusinessException(String message, List<String> arguments) {
        super(message);
        
        this.arguments = arguments;
    }

    public CustomBusinessException(String message, Throwable cause, List<String> arguments) {
        super(message, cause);
        
        this.arguments = arguments;	
    }

	public List<String> getArguments() {
		return arguments;
	}
}
