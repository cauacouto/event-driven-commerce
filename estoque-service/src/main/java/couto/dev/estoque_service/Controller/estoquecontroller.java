package couto.dev.estoque_service.Controller;

import couto.dev.estoque_service.Service.EstoqueService;
import couto.dev.estoque_service.dto.EstoqueResponseDto;
import couto.dev.estoque_service.dto.EstoqueRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
@RequiredArgsConstructor
public class estoquecontroller {

    private final EstoqueService estoqueService;

    @PostMapping
    public ResponseEntity<Void> adicionarEstoque(@RequestBody EstoqueRequestDto data){
        this.estoqueService.AdicionarEstoque(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/reserva")
    public ResponseEntity<Void> ReservaEstoque(@RequestBody EstoqueRequestDto data){
        this.estoqueService.reservaEstoque(data);
        return ResponseEntity.ok().build();


    }

    @GetMapping("/{id}")
    public ResponseEntity<List<EstoqueResponseDto>> listarEstoque(@PathVariable Integer id){
        return ResponseEntity.ok(estoqueService.listarPorId(id));
    }

}
