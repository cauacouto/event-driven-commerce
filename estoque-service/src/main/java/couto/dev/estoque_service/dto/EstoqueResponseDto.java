package couto.dev.estoque_service.dto;

import couto.dev.estoque_service.database.Enum.statusProduto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueResponseDto {

    private Integer produtoId;
    private Integer quantidade;
    private statusProduto statusProduto;
    private LocalDateTime atualizado;

    public EstoqueResponseDto(Integer id, Integer produtoId, Integer quantidade) {
    }
}
