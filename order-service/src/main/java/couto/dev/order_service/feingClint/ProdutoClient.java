package couto.dev.order_service.feingClint;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "estoque-service")
public interface ProdutoClient {




}
