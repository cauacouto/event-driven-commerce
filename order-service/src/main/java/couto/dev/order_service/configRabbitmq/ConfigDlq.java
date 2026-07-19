package couto.dev.order_service.configRabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDlq {

    private static final String ORDER_DLQ ="order.dlq";
    private static final String ORDER_DLX ="order.dlx";

    @Bean
    public Queue orderDlq(){
        return QueueBuilder
                .durable(ORDER_DLQ)
                .build();
    }
    @Bean
    public DirectExchange deadLetterExchangeOrder(){
        return new DirectExchange(ORDER_DLX);
    }

    @Bean
    public Binding bindingOrder(@Qualifier("orderDlq")Queue orderDlq, @Qualifier("deadLetterExchangeOrder") DirectExchange deadLetterExchangeOrder){
        return BindingBuilder
                .bind(orderDlq)
                .to(deadLetterExchangeOrder)
                .with(ORDER_DLX);
    }

}
