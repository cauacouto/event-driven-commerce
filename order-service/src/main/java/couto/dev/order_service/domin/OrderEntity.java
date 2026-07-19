package couto.dev.order_service.domin;

import couto.dev.order_service.Enum.StatusOrder;
import couto.dev.order_service.Enum.StatusPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Order_DB")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    private Integer produtoId;
    private Integer quantidade;
    private LocalDateTime criada;
    private BigDecimal valorProduto;
    private BigDecimal valorTotal;
    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;
    @Enumerated(value = EnumType.STRING)
    private StatusPagamento statusPagamento;


    @PrePersist
    public void dataCriacao(){
        this.criada = LocalDateTime.now();
    }

}
