package couto.dev.produto_service.domin;

import couto.dev.produto_service.Enum.statusProduto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "produto_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private  String categoria;
    private Integer quantidade;
    private BigDecimal preco;
    @Enumerated(EnumType.STRING)
    private statusProduto statusProduto;
    private LocalDateTime dataEntrada;



    @PrePersist
    public void dataEntrada(){
        this.dataEntrada = LocalDateTime.now();
    }
}
