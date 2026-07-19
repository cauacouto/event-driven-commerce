package couto.dev.order_service.dto;

import couto.dev.order_service.Enum.StatusPagamento;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagamentoProcessadoEvent {
    private Integer pagamentoId;
    private UUID orderId;
    private BigDecimal valorTotal;
    private StatusPagamento statusPagamento;
}
