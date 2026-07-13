package couto.dev.order_service.configRabbitmq;

import couto.dev.order_service.dto.OrderProducerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerOrder {

    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange orderExchange;


    public void enviar(OrderProducerDto orderProducer){
      rabbitTemplate.convertAndSend(
              orderExchange.getName(),
              "pagamentos.queue",
              orderProducer
      );
    }
}
