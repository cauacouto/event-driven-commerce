package couto.dev.order_service.service;

import couto.dev.order_service.Enum.StatusOrder;
import couto.dev.order_service.domin.OrderEntity;
import couto.dev.order_service.dto.OrderRequestDto;
import couto.dev.order_service.dto.ReservaEstoqueDto;
import couto.dev.order_service.feingClint.estoqueClient;
import couto.dev.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final estoqueClient estoqueClient;


    public void criarOrder(OrderRequestDto dto){

        estoqueClient.reservaEstoque(
                new ReservaEstoqueDto(dto.getProdutoId(),
                        dto.getQuantidade()
                )
        );

        OrderEntity order = new OrderEntity();
        order.setProdutoId(dto.getProdutoId());
        order.setQuantidade(dto.getQuantidade());
        order.setValorProduto(dto.getValorPorduto());
        order.setStatusOrder(StatusOrder.AGUARDANDO_PAGAMENTO);


        BigDecimal valorTotal = order.getValorProduto()
                .multiply(BigDecimal.valueOf(dto.getQuantidade()));

        order.setValorTotal(valorTotal);



        orderRepository.save(order);


        //publicar evento




    }


}



