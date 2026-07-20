package couto.dev.estoque_service.Service;

import couto.dev.estoque_service.Enum.StatusEstoque;
import couto.dev.estoque_service.database.Enum.statusProduto;
import couto.dev.estoque_service.database.domin.EstoqueEntity;
import couto.dev.estoque_service.database.repository.EstoqueRepository;
import couto.dev.estoque_service.dto.EstoqueRequestDto;
import couto.dev.estoque_service.dto.EstoqueResponseDto;
import couto.dev.estoque_service.dto.ResponseProdutoDto;
import couto.dev.estoque_service.exceptions.EstoqueException;
import couto.dev.estoque_service.exceptions.ProdutoInsdisponivelException;
import couto.dev.estoque_service.feingClient.ProdutoClient;
import couto.dev.estoque_service.mapper.EstoqueMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private  final ProdutoClient produtoClient;
    private final EstoqueMapper estoqueMapper;


    @CircuitBreaker(
            name = "produtoService",
            fallbackMethod = "fallbackProduto"
    )
    public void AdicionarEstoque(EstoqueRequestDto estoqueDto){

        ResponseProdutoDto produto =
                produtoClient.buscarProduto(estoqueDto.getProdutoId());

        if (produto.getStatusProduto() == statusProduto.INATIVO){
            throw new RuntimeException("produto inativo");
        }

        EstoqueEntity estoque = estoqueRepository.findByProdutoId(estoqueDto.getProdutoId()).orElseGet(()-> {
            EstoqueEntity novo = new EstoqueEntity();
            novo.setProdutoId(estoqueDto.getProdutoId());
            novo.setQuantidade(0);
            return novo;
        });
        estoque.setQuantidade(
                estoque.getQuantidade() + estoqueDto.getQuantidade()
        );

        estoqueRepository.save(estoque);

    }

    public void Fallback(
            EstoqueRequestDto dto,
            Throwable ex) throws ProdutoInsdisponivelException {
        log.error("produto-service indisponivel", ex);
        throw new ProdutoInsdisponivelException("não foi possivel validar o produto. tente novamente mais tarde");

    }

    @Transactional
    public void reservaEstoque(EstoqueRequestDto estoqueDto){

        EstoqueEntity estoque = estoqueRepository.findByProdutoId(estoqueDto.getProdutoId()).
                orElseThrow(()-> new RuntimeException("produto não encotrado"));

        if (estoque.getQuantidade() < estoqueDto.getQuantidade()){
            throw new RuntimeException( "estoque insufisiente");
        }

        estoque.setProdutoId(estoqueDto.getProdutoId());
        estoque.setQuantidade(
                estoque.getQuantidade() - estoqueDto.getQuantidade()
        );
        estoqueRepository.save(estoque);

    }



    @Transactional
    public void rollbacEstoque(EstoqueRequestDto dto){//implementar em pagamentos

        EstoqueEntity estoque = estoqueRepository.findByProdutoId(dto.getProdutoId())
                .orElseThrow(()-> new RuntimeException("estoque não encotrado"));


        estoque.setProdutoId(dto.getProdutoId());
        estoque.setQuantidade(
                estoque.getQuantidade() + dto.getQuantidade()

        );
        estoque.setStatusEstoque(StatusEstoque.FALHA_PAGAMENTO);
    }

    @Transactional
    public void confirmarReserva(Integer produtoId){
        EstoqueResponseDto estoque = buscarEstoque(produtoId);
        estoque.setStatusEstoque(StatusEstoque.PAGAMENTO_APROVADO);
    }

    public EstoqueResponseDto buscarEstoque(Integer produtoId){ //criar dtoResponse
        EstoqueEntity estoque = estoqueRepository.findByProdutoId(produtoId)
                .orElseThrow(()-> new EstoqueException("estoque não encontrado"));
        return estoqueMapper.toDto(estoque);
    }

    public List<EstoqueResponseDto> listarEstoque(){
        return estoqueRepository.findAll().stream()
                .map(estoqueMapper::toDto)
                .toList();

    }

}

