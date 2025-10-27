package classes;

public class CarpaPlaya extends Carpa {
    public CarpaPlaya(int id, String numero, int capacidad, boolean piletaLibre, boolean sombrilla) {
        super(id, numero, capacidad, piletaLibre, sombrilla);
    }
    @Override
    public String getTipo() { return "Playa"; }
}
