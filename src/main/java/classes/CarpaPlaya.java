package classes;

public class CarpaPlaya extends Carpa {
    private boolean cochera;

    public CarpaPlaya() {
        super();
    }

    public CarpaPlaya(int capacidad, boolean vistaMar, boolean sombrilla, boolean cochera) {
        super(capacidad, vistaMar, sombrilla);
        this.cochera = cochera;
    }

    public boolean isCochera() { return cochera; }
    public void setCochera(boolean cochera) { this.cochera = cochera; }

    @Override
    public String toString() {
        return super.toString() + (cochera ? " con cochera" : "");
    }
}
