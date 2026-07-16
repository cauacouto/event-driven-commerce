package couto.dev.produto_service.Service;

import couto.dev.produto_service.Enum.statusProduto;
import couto.dev.produto_service.domin.Produto;
import couto.dev.produto_service.dto.ProdutoDto;
import couto.dev.produto_service.dto.ProdutoResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutoService {

    @Autowired
    private couto.dev.produto_service.repository.ProdutoRepository produtoRepository;

    public void saveProduto(ProdutoDto produtoDto){
        Produto produto = Produto.builder()

                .nome(produtoDto.getNome())
                .categoria(produtoDto.getCategoria())
                .descricao(produtoDto.getDescricao())
                .quantidade(produtoDto.getQuantidade())
                .preco(produtoDto.getPreco())
                .statusProduto(statusProduto.ATIVO)
                .dataEntrada(produtoDto.getDataEntrada())
                .build();
        produtoRepository.save(produto);

    }


    public List<Produto> getProdutos(){
        return produtoRepository.findAll();
    }

    @Cacheable(value = "produtos",key = "#id")
    public ProdutoResponseDto buscarPorId(Integer id) {
        log.info("buscando mysql");
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        return new ProdutoResponseDto(
                produto.getId(),
                produto.getNome(),
                produto.getQuantidade(),
                produto.getPreco()

        );
    }

}
