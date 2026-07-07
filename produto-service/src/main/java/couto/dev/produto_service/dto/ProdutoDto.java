package couto.dev.produto_service.dto;

import couto.dev.produto_service.Enum.statusProduto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProdutoDto {

    private String nome;
    private String descricao;
    private  String categoria;
    private Integer quantidade;
    private BigDecimal preco;
    private LocalDateTime dataEntrada;



    }

