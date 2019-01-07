package org.martseniuk.diploma.foo.service.impl;

import org.martseniuk.diploma.foo.service.AsyncService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Implementation of asynchronous service.
 *
 * @author Roman_Martseniuk
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    private String routingkey;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public AsyncServiceImpl(@Value("${spring.rabbitmq.routingkey}") String routingkey,
                            RabbitTemplate rabbitTemplate) {
        this.routingkey = routingkey;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void postMessage(String body) {
        rabbitTemplate.convertAndSend(routingkey, body);
    }
}
