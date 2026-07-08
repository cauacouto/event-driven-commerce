package couto.dev.produto_service.Controller;

import couto.dev.produto_service.Service.ProdutoService;
import couto.dev.produto_service.domin.Produto;
import couto.dev.produto_service.dto.ProdutoDto;
import couto.dev.produto_service.dto.ProdutoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

     @PostMapping
    public ResponseEntity<Void> criarProdutos(@RequestBody ProdutoDto data){
         this.produtoService.saveProduto(data);
         return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    public ResponseEntity<List<Produto>> ListarProdutos(){
         return ResponseEntity.ok(produtoService.getProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> BuscarProdutoId(@PathVariable Integer id){
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }
}
