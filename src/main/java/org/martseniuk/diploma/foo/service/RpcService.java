package org.martseniuk.diploma.foo.service;

/**
 * Service for remote procedure call.
 *
 * @author Roman_Martseniuk
 */
public interface RpcService {

    /**
     * Call another service using Spring RestTemplate {@link org.springframework.web.client.RestTemplate}.
     *
     * @return response from another service.
     */
    String callDumbClient();

    /**
     * Call another service usign Feign client.
     *
     * @return response from another service.
     */
    String callFeignClient();
}
