package excepciones;

public class ClienteNoEncontradoException extends RuntimeException {
    public ClienteNoEncontradoException() {
        super("Cliente no encontrado");
    }
} 
