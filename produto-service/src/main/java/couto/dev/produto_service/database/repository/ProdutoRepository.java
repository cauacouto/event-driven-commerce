package couto.dev.produto_service.database.repository;

import couto.dev.produto_service.database.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

    List<Produto> findAllById(Integer id);
}
