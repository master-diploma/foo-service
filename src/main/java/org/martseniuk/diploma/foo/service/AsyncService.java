package org.martseniuk.diploma.foo.service;

/**
 * Interface of asynchronous service.
 *
 * @author Roman_Martseniuk
 */
public interface AsyncService {

    /**
     * Send message through RabbitMQ.
     *
     * @param body - content of the message.
     */
    void postMessage(String body);
}
