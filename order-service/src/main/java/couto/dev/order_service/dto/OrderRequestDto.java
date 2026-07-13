package couto.dev.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class OrderRequestDto {

    private Integer produtoId;
    private Integer quantidade;


}
