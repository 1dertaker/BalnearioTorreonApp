package excepciones;

public class DniInvalidoException extends RuntimeException {
    public DniInvalidoException() {
        super("DNI vacío");
    }
}