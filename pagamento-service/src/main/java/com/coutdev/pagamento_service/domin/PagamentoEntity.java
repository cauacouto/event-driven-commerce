package com.coutdev.pagamento_service.domin;

import com.coutdev.pagamento_service.Enum.StatusPagamento;
import com.coutdev.pagamento_service.Enum.TipoPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "pagamentos_DB")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID orderId;
    private BigDecimal valorTotal;
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;
    private OffsetDateTime dataPagamento;

    @PrePersist
    public void dataPagamento(){
        this.dataPagamento = OffsetDateTime.now();
    }

}
