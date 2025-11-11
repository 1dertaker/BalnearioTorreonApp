package excepciones;

public class NombreInvalidoException extends RuntimeException {
    public NombreInvalidoException() {
        super("Nombre vacío");
    }
} 
