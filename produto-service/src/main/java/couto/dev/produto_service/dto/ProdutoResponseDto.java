package couto.dev.produto_service.dto;

import couto.dev.produto_service.Enum.statusProduto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoResponseDto(
        Integer id,
        String nome,
        String descricao,
        String categoria,
        Integer quantidade,
        BigDecimal preco,
        statusProduto statusProduto,
        LocalDateTime dataEntrada
) {}
