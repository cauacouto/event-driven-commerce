package couto.dev.estoque_service.database.domin;

import couto.dev.estoque_service.Enum.StatusEstoque;
import couto.dev.estoque_service.database.Enum.statusProduto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "estoque_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class EstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private Integer produtoId;
    private Integer quantidade;
    @Enumerated(EnumType.STRING)
    private statusProduto statusProduto;
    @Enumerated(EnumType.STRING)
    private StatusEstoque statusEstoque;
    private LocalDateTime atualizado;

    @PrePersist
    public void atualiza(){
        this.atualizado = LocalDateTime.now();
    }




    }


