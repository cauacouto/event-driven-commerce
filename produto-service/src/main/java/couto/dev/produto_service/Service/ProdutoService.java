package couto.dev.produto_service.Service;

import couto.dev.produto_service.Enum.statusProduto;
import couto.dev.produto_service.database.model.Produto;
import couto.dev.produto_service.database.repository.ProdutoRepository;
import couto.dev.produto_service.dto.ProdutoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

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

    public List<Produto> buscarPorId(Integer id){
        return produtoRepository.findAllById(id);
    }


}
