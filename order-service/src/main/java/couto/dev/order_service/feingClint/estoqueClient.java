package couto.dev.order_service.feingClint;

import couto.dev.order_service.dto.ReservaEstoqueDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "estoque-service")
public interface estoqueClient {


      @PostMapping("/estoque/reserva")
      void reservaEstoque(@RequestBody ReservaEstoqueDto requestEstoqueDto);

}
