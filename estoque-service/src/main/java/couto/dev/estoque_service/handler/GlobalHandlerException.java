package couto.dev.estoque_service.handler;

import couto.dev.estoque_service.exceptions.ErroResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handlerException(Exception ex){
        ErroResponse Response = ErroResponse.builder()
                .menssagem(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErroResponse> handlerBadRequestExeception(BadRequestException ex){
        ErroResponse response = ErroResponse.builder()
                .menssagem(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErroResponse> handlerNotFoundException(NotFoundException ex){
        ErroResponse response = ErroResponse.builder()

                .menssagem(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
