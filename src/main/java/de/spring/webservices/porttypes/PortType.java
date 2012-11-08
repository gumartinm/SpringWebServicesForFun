package de.spring.webservices.porttypes;

/**
 * <p>
 * WSDL Port Types
 * </p>
 * See: <a href="http://www.w3.org/TR/wsdl#_porttypes">http://www.w3.org/TR/wsdl#_porttypes</a>
 *
 */
public interface PortType<E extends Request, T extends Response> {

    /**
     * <p>
     * Request-response operation
     * </p>
     * See: <a href="http://www.w3.org/TR/wsdl#_request-response">http://www.w3.org/TR/wsdl#_request-response</a>
     *
     * @param <T> Describes {@link Response}
     * @param <E> Describes {@link Request}
     */
    public interface RequestResponse<T, E> {
        T requestResponse (E request);
    }
    
    /**
     * <p>
     * One-way operation
     * </p>
     * See: <a href="http://www.w3.org/TR/wsdl#_one-way">http://www.w3.org/TR/wsdl#_one-way</a>
     *
     * @param <T> Describes {@link Response}
     * @param <E> Describes {@link Request}
     */
    public interface OneWay<E> {
        void oneWay (E request);
    }
    
    /**
     * <p>
     * Notification operation
     * </p>
     * See: <a href="http://www.w3.org/TR/wsdl#_notification">http://www.w3.org/TR/wsdl#_notification</a>
     *
     * @param <T> Describes {@link Response}
     * @param <E> Describes {@link Request}
     */
    public interface Notification<T> {
        T notification ();
    }
}
