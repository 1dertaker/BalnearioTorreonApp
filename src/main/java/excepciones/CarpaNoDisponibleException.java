package excepciones;

public class CarpaNoDisponibleException extends RuntimeException {
    public CarpaNoDisponibleException() {
        super("Carpa no disponible");
    }
}
