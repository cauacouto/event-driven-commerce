package couto.dev.estoque_service.Service;

import couto.dev.estoque_service.database.Enum.statusProduto;
import couto.dev.estoque_service.database.domin.EstoqueEntity;
import couto.dev.estoque_service.database.repository.EstoqueRepository;
import couto.dev.estoque_service.dto.EstoqueDto;
import couto.dev.estoque_service.dto.EstoqueRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;




    public void AdicionarEstoque(EstoqueDto estoqueDto){

        if (estoqueDto.getStatusProduto() == statusProduto.INATIVO){
            throw new RuntimeException(" produto inativo não poder ser adicionado no estoque");
        }

       EstoqueEntity estoque = new EstoqueEntity();
       estoque.setProdutoId(estoqueDto.getProdutoId());
       estoque.setQuantidade(estoqueDto.getQuantidade());

       estoqueRepository.save(estoque);
    }

    @Transactional
    public void reservaEstoque(EstoqueRequestDto estoqueDto){

        EstoqueEntity estoque = estoqueRepository.findByProdutoId(estoqueDto.getProdutoId()).
                orElseThrow(()-> new RuntimeException("produto não encotrado"));

        if (estoque.getQuantidade() < estoqueDto.getQuantidade()){
            throw new RuntimeException( "estoque insufisiente");
        }

        estoque.setQuantidade(
                estoqueDto.getQuantidade() - estoque.getQuantidade()
        );
        estoqueRepository.save(estoque);

    }



    @Transactional
    public void rollbacEstoque(EstoqueDto dto){//implementar em pagamentos

        EstoqueEntity estoque = estoqueRepository.findByProdutoId(dto.getProdutoId())
                .orElseThrow(()-> new RuntimeException("estoque não encotrado"));

        estoque.setQuantidade(
                dto.getQuantidade() + estoque.getQuantidade()
        );
    }

    public List<EstoqueDto> listarPorId(Integer produtoId){
       return estoqueRepository.findAllById(produtoId);

    }

    }

