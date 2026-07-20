package couto.dev.estoque_service.exceptions;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErroResponse {

    private String menssagem;
    private Integer status;
}
