package couto.dev.produto_service.repository;

import couto.dev.produto_service.domin.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

    List<Produto> findAllById(Integer id);
}
