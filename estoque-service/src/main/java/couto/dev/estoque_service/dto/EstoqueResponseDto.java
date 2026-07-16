package couto.dev.estoque_service.dto;

import couto.dev.estoque_service.Enum.StatusEstoque;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private LocalDateTime atualizado;
    @Enumerated(EnumType.STRING)
    private StatusEstoque statusEstoque;
}
//    public EstoqueResponseDto(Integer id, Integer produtoId, Integer quantidade) {
//    }
//}
