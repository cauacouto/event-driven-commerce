package couto.dev.order_service.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class PagamentoAprovadoEvent {

    private UUID orderId;
    private BigDecimal valorTotal;

}