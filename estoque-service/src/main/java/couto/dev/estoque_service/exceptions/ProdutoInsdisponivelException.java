package couto.dev.estoque_service.exceptions;

public class ProdutoInsdisponivelException extends Throwable {
    public ProdutoInsdisponivelException(String menssage) {
        super(menssage);
    }
}
