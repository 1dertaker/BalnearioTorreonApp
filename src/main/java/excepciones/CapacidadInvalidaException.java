package excepciones;

public class CapacidadInvalidaException extends RuntimeException {
    public CapacidadInvalidaException() {
        super("Capacidad inválida");
    }
}
