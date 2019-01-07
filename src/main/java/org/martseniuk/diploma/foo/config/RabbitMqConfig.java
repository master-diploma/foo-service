package org.martseniuk.diploma.foo.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for rabbitmq.
 *
 * @author Roman_Martseniuk
 **/
@Configuration
public class RabbitMqConfig {

    private String exchange;
    private ConnectionFactory connectionFactory;

    @Autowired
    public RabbitMqConfig(@Value("${spring.rabbitmq.exchange}") String exchange,
                          ConnectionFactory connectionFactory) {
        this.exchange = exchange;
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(exchange);
        return rabbitTemplate;
    }

    /**
     * Creates new header exchange on rabbitmq server with name from rabbitmq.properties, if it doesn't exist
     *
     * @return instance of HeadersExchange
     */
    @Bean
    public DirectExchange headersExchange() {
        return new DirectExchange(exchange);
    }
}
