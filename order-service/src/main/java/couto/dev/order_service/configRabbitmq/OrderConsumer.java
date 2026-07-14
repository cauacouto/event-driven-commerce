package couto.dev.order_service.configRabbitmq;

import couto.dev.order_service.dto.PagamentoAprovadoEvent;
import couto.dev.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {

    private final OrderService orderService;
     @RabbitListener(queues = "pagamentoAprovado.queue")
    public void ConsumerOrder(@Payload PagamentoAprovadoEvent event){
         log.info("orderId recebido {}", event.getOrderId());
         orderService.confirmarPagamento(event.getOrderId());

     }
}
