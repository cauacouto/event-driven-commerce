package couto.dev.produto_service.dto;

import java.math.BigDecimal;

public record ProdutoResponseDto(
        Integer id,
        String nome,
        Integer quantidade,
        BigDecimal preco

) {}
