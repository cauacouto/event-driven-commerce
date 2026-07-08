package couto.dev.estoque_service.database.repository;

import couto.dev.estoque_service.database.domin.EstoqueEntity;
import couto.dev.estoque_service.dto.EstoqueResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<EstoqueEntity,Integer> {
    Optional<EstoqueEntity> findByProdutoId(Integer produtoId);

    List<EstoqueResponseDto> findAllById(Integer id);
}
