package couto.dev.order_service.service;

import couto.dev.order_service.domin.OrderEntity;
import couto.dev.order_service.dto.OrderRequestDto;
import couto.dev.order_service.dto.ReservaEstoqueDto;
import couto.dev.order_service.dto.ResponseprodutoDto;
import couto.dev.order_service.feingClint.estoqueClient;
import couto.dev.order_service.feingClint.produtoClient;
import couto.dev.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private  final produtoClient produtoClient;
    private final estoqueClient estoqueClient;


    public void criarOrder(OrderRequestDto dto){
        ResponseprodutoDto produto =
                produtoClient.buscarProduto(dto.getProdutoId());

        estoqueClient.reservaEstoque(
                new ReservaEstoqueDto(dto.getProdutoId(),
                        dto.getQuantidade()
                )
        );

        OrderEntity order = new OrderEntity();
        order.setProdutoId(produto.getId());
        order.setQuantidade(dto.getQuantidade());
        orderRepository.save(order);


        //publicar evento




    }


}



