package couto.dev.order_service.service;

import couto.dev.order_service.domin.OrderEntity;
import couto.dev.order_service.dto.OrderRequestDto;
import couto.dev.order_service.dto.ResponseProdutoDto;
import couto.dev.order_service.feingClint.ProdutoClient;
import couto.dev.order_service.repository.OrderRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProdutoClient produtoClient;


    public void criarOrder(OrderRequestDto dto){

     try {
         ResponseProdutoDto produto =
                 produtoClient.buscarProduto(dto.getProdutoId());

         OrderEntity order = new OrderEntity();
         order.setProdutoId(produto.getId());
         order.setQuantidade(dto.getQuantidade());
         orderRepository.save(order);

     }catch (FeignException.NotFound e){
         throw new RuntimeException("produto não encontrado");
     }



    }


}
