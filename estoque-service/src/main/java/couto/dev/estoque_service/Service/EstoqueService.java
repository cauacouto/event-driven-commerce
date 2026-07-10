package couto.dev.estoque_service.Service;

import couto.dev.estoque_service.Enum.StatusEstoque;
import couto.dev.estoque_service.database.Enum.statusProduto;
import couto.dev.estoque_service.database.domin.EstoqueEntity;
import couto.dev.estoque_service.database.repository.EstoqueRepository;
import couto.dev.estoque_service.dto.EstoqueRequestDto;
import couto.dev.estoque_service.dto.EstoqueResponseDto;
import couto.dev.estoque_service.dto.ResponseProdutoDto;
import couto.dev.estoque_service.feingClient.ProdutoClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private  final ProdutoClient produtoClient;
    
    public void AdicionarEstoque(EstoqueRequestDto estoqueDto){

        ResponseProdutoDto produto =
                produtoClient.buscarProduto(estoqueDto.getProdutoId());

        if (produto.getStatusProduto() == statusProduto.INATIVO){
            throw new RuntimeException("produto inativo");
        }

       EstoqueEntity estoque = new EstoqueEntity();
       estoque.setProdutoId(estoqueDto.getProdutoId());
       estoque.setQuantidade(estoqueDto.getQuantidade());
       this.estoqueRepository.save(estoque);
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
        EstoqueEntity estoque = buscarEstoque(produtoId);
        estoque.setStatusEstoque(StatusEstoque.PAGAMENTO_APROVADO);


    }

    public EstoqueEntity buscarEstoque(Integer produtoId){ //criar dtoResponse
        return estoqueRepository.findByProdutoId(produtoId)
                .orElseThrow(()-> new RuntimeException("estoque não encontrado"));
    }

    public List<EstoqueResponseDto> listarPorId(Integer produtoId){
       return estoqueRepository.findAllById(produtoId);

    }

    }

