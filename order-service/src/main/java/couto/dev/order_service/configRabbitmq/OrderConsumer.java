package couto.dev.order_service.configRabbitmq;

import couto.dev.order_service.dto.PagamentoProcessadoEvent;
import couto.dev.order_service.dto.PagamentoRecusadoDto;
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
    public void Aprovado(@Payload PagamentoProcessadoEvent event){
         orderService.confirmarPagamento(event.getOrderId());

     }



    @RabbitListener(queues = "pagamentoRecusado.queue")
    public void Recusado(@Payload PagamentoRecusadoDto event){
        orderService.RecusarPagamento(event.getOrderId());

    }

}
