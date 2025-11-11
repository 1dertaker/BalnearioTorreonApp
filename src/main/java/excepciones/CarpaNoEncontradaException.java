package excepciones;

public class CarpaNoEncontradaException extends RuntimeException {
    public CarpaNoEncontradaException() {
        super("Carpa no encontrada");
    }
} 
