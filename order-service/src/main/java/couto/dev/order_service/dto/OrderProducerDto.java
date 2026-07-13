package couto.dev.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class OrderProducerDto {

    private UUID id;
    private Integer produtoId;
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal valorProduto;
    private OffsetDateTime dataCriacao;

}
