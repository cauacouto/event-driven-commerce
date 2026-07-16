package couto.dev.order_service.dto;

import couto.dev.order_service.Enum.StatusOrder;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class OrderResponseDto {

    private UUID id;
    private Integer produtoId;
    private Integer quantidade;
    private BigDecimal valorTotal;
    private LocalDateTime criada;
    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;



}
