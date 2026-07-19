package couto.dev.order_service.configRabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    private static final String PAGAMENTO_EXCHANGE = "pagamento.exchange";
    private static final String ORDER_EXCHANGE = "order.exchange";
    private static final String PAGAMENTO_APROVADO_QUEUE = "pagamentoAprovado.queue";
    private static final String PAGAMENTO_RECUSADO_QUEUE = "pagamentoRecusado.queue";

    @Bean
    public JacksonJsonMessageConverter jacksonJsonMessageConverter(){
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, JacksonJsonMessageConverter converter){
        RabbitTemplate template =
                new RabbitTemplate(connectionFactory);

        template.setMessageConverter(converter);
        return template;
    }

    @Bean
    public RabbitAdmin rabbitAdmin (ConnectionFactory connectionFactory){
        return  new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationEvent> inicializarAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public DirectExchange orderExchange(){
        return new DirectExchange(ORDER_EXCHANGE);
    }


    @Bean
    public DirectExchange pagamentoExchange(){
        return new DirectExchange(PAGAMENTO_EXCHANGE);
    }

    @Bean
    public Queue PagamentoAprovado(){
       return QueueBuilder
                .durable(PAGAMENTO_APROVADO_QUEUE)
               .deadLetterExchange("order.dlx")
                .build();
    }

    @Bean
    public Queue PagamentoRecusado(){
        return QueueBuilder
                .durable(PAGAMENTO_RECUSADO_QUEUE)
                .deadLetterExchange("order.dlx")
                .build();
    }

    @Bean
    public Binding pagamentoBinding(@Qualifier("PagamentoAprovado") Queue pagamentoAprovado,@Qualifier("pagamentoExchange")DirectExchange  pagamentoExchange){
        return BindingBuilder
                .bind(pagamentoAprovado)
                .to(pagamentoExchange)
                .with("pagamento.aprovado");

    }

    @Bean
    public Binding pagamentoRecusadoBinding(@Qualifier("PagamentoRecusado") Queue pagamentoRecusado,@Qualifier("pagamentoExchange")DirectExchange  pagamentoExchange){
        return BindingBuilder
                .bind(pagamentoRecusado)
                .to(pagamentoExchange)
                .with("pagamento.recusado");

    }

}
