package couto.dev.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class OrderRequestDto {

    private Integer produtoId;
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal valorPorduto;

}
