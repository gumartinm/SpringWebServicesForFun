package de.spring.webservices.operations;

/**
 * <p>
 * Operations: WSDL v1.1 and v2.0 
 * </p>
 * See: <a href="http://www.w3.org/TR/wsdl#_porttypes">http://www.w3.org/TR/wsdl#_porttypes</a><br>
 * See: <a href="http://www.w3.org/TR/2007/REC-wsdl20-adjuncts-20070626/#patterns">
 * http://www.w3.org/TR/2007/REC-wsdl20-adjuncts-20070626/#patterns</a>
 * 
 */
public interface Operations {

    /**
     * <p>
     * Request-response operation WSDL v1.1
     * </p>
     * See: <a
     * href="http://www.w3.org/TR/wsdl#_request-response">http://www.w3.org
     * /TR/wsdl#_request-response</a>
     * 
     * @param <T>
     *            Describes {@link Response}
     * @param <E>
     *            Describes {@link Request}
     */
    public static interface RequestResponse<T extends Response, E extends Request> {
        T requestResponse(E request);
    }

    /**
     * <p>
     * One-way operation WSDL v1.1
     * </p>
     * See: <a
     * href="http://www.w3.org/TR/wsdl#_one-way">http://www.w3.org/TR/wsdl
     * #_one-way</a>
     * 
     * @param <T>
     *            Describes {@link Response}
     * @param <E>
     *            Describes {@link Request}
     */
    public interface OneWay<E extends Request> {
        void oneWay(E request);
    }

    /**
     * <p>
     * Notification operation WSDL v1.1
     * </p>
     * See: <a
     * href="http://www.w3.org/TR/wsdl#_notification">http://www.w3.org/TR
     * /wsdl#_notification</a>
     * 
     * @param <T>
     *            Describes {@link Response}
     * @param <E>
     *            Describes {@link Request}
     */
    public interface Notification<T extends Response> {
        T notification();
    }

    /**
     * <p>
     * In-Only message exchange pattern WSDL 2.0
     * </p>
     * See: <a
     * href="http://www.w3.org/TR/2007/REC-wsdl20-adjuncts-20070626/#patterns">
     * http://www.w3.org/TR/2007/REC-wsdl20-adjuncts-20070626/#patterns</a>
     * 
     * @param <E> 
     *             Describes {@link Request}
     */
    public interface InOnly<E extends Request> {
        void inOnly(E request);
    }

    /**
     * <p>
     * Robust In-Only message exchange pattern WSDL 2.0
     * </p>
     * See: <a
     * href="http://www.w3.org/TR/2007/REC-wsdl20-adjuncts-20070626/#patterns">
     * http://www.w3.org/TR/2007/REC-wsdl20-adjuncts-20070626/#patterns</a>
     * 
     * @param <E> 
     *             Describes {@link Request}
     */
    public interface RobustInOnly<E extends Request> {
        void robustInOnly(E request);
    }

    /**
     * <p>
     * In-Out message exchange pattern WSDL 2.0
     * </p>
     * See: <a
     * href="http://www.w3.org/TR/2007/REC-wsdl20-adjuncts-20070626/#patterns">
     * http://www.w3.org/TR/2007/REC-wsdl20-adjuncts-20070626/#patterns</a>
     * 
     * @param <T>
     *            Describes {@link Response}
     * @param <E>
     *            Describes {@link Request}
     */
    public interface InOut<T extends Response, E extends Request> {
        T inOut(E request);
    }
}