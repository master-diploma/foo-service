package org.martseniuk.diploma.foo.service.impl;

import org.martseniuk.diploma.foo.feign.BarFeignClient;
import org.martseniuk.diploma.foo.service.RpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation for remote procedure call service.
 *
 * @author Roman_Martseniuk
 */
@Service
public class RpcServiceImpl implements RpcService {

    private RestTemplate restTemplate;
    private BarFeignClient barFeignClient;

    @Autowired
    public RpcServiceImpl(RestTemplate restTemplate, BarFeignClient barFeignClient) {
        this.restTemplate = restTemplate;
        this.barFeignClient = barFeignClient;
    }

    @Retryable(
            value = {Exception.class},
            maxAttempts = 4,
            backoff = @Backoff(delay = 1000)
    )
    public String callDumbClient() {
        return restTemplate.getForEntity("http://bar-service:8080/api/setting", String.class).getBody();
    }

    public String callFeignClient() {
        return barFeignClient.getSetting();
    }
}
