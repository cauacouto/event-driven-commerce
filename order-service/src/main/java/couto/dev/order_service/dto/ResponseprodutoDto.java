package couto.dev.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseprodutoDto {

    private Integer id;
    private String nome;
    private String descricao;
    private  String categoria;
    private Integer quantidade;
    private BigDecimal preco;

}
