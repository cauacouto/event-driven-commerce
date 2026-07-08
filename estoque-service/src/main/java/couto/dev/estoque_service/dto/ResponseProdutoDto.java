package couto.dev.estoque_service.dto;

import couto.dev.estoque_service.database.Enum.statusProduto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseProdutoDto {

    private Integer id;
    private String nome;
    private String descricao;
    private  String categoria;
    private BigDecimal preco;
    @Enumerated(EnumType.STRING)
    private statusProduto statusProduto;
    private LocalDateTime dataEntrada;
}
