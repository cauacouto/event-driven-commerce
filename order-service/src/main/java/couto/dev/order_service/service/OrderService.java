package couto.dev.order_service.service;

import couto.dev.order_service.Enum.StatusOrder;
import couto.dev.order_service.configRabbitmq.ProducerOrder;
import couto.dev.order_service.domin.OrderEntity;
import couto.dev.order_service.dto.*;
import couto.dev.order_service.feingClint.ProdutoClient;
import couto.dev.order_service.feingClint.estoqueClient;
import couto.dev.order_service.mapping.OrderProducerMapper;
import couto.dev.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final estoqueClient estoqueClient;
    private final ProducerOrder producerOrder;
    private final OrderProducerMapper producerMapper;
    private final ProdutoClient produtoClient;


    public OrderResponseDto criarOrder(OrderRequestDto dto){


        ResponseProdutoDto produto = produtoClient.buscarProduto(dto.getProdutoId());

        estoqueClient.reservaEstoque(
                new ReservaEstoqueDto(dto.getProdutoId(),
                        dto.getQuantidade()


                )
        );

        OrderEntity order = new OrderEntity();
        order.setProdutoId(dto.getProdutoId());
        order.setQuantidade(dto.getQuantidade());
        order.setValorProduto(produto.getPreco());
        order.setStatusOrder(StatusOrder.AGUARDANDO_PAGAMENTO);


       BigDecimal valorTotal = produto.getPreco()
                .multiply(BigDecimal.valueOf(dto.getQuantidade()));

        order.setValorTotal(valorTotal);



        var orderSalva = orderRepository.save(order);

        OrderProducerDto event =

                producerMapper.toEvent(orderSalva);


        producerOrder.enviar(event);
        return producerMapper.toDto(order);
    }


    @Transactional
    public void confirmarPagamento(UUID orderId){
        log.info("Buscando pedido {}", orderId);
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("pedido não encontrado"));
        order.setStatusOrder(StatusOrder.PAGAMENTO_APROVADO);
    }

}



