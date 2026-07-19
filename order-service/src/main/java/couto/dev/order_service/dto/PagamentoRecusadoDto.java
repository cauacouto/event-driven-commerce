package couto.dev.order_service.dto;

import couto.dev.order_service.Enum.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagamentoRecusadoDto {
    private Integer pagamentoId;
    private UUID orderId;
    private BigDecimal valorTotal;
    private String motivoRecusa;
    private StatusPagamento statusPagamento;
}
