package couto.dev.estoque_service.feingClient;

import couto.dev.estoque_service.dto.ResponseProdutoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produto-service")
public interface ProdutoClient {


    @GetMapping("/produtos/{id}")
    ResponseProdutoDto buscarProduto(@PathVariable Integer id);
}
