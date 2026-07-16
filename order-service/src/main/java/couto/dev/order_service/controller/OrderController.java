package couto.dev.order_service.controller;

import couto.dev.order_service.dto.OrderRequestDto;
import couto.dev.order_service.dto.OrderResponseDto;
import couto.dev.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> criarOrder(@RequestBody OrderRequestDto dto){
        OrderResponseDto response = orderService.criarOrder(dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
