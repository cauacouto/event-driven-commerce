package couto.dev.produto_service.dto;

import couto.dev.produto_service.Enum.statusProduto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProdutoResponseDto {
    private Integer id;
    private String nome;
    private String descricao;
    private  String categoria;
    private Integer quantidade;
    private BigDecimal preco;
    @Enumerated(EnumType.STRING)
    private statusProduto statusProduto;
    private LocalDateTime dataEntrada;

    public ProdutoResponseDto(Integer id, String nome, String descricao, String categoria, Integer quantidade, BigDecimal preco, statusProduto statusProduto, LocalDateTime dataEntrada) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.preco = preco;
        this.statusProduto = statusProduto;
        this.dataEntrada = dataEntrada;
    }
}

