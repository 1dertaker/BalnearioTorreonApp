package classes;

public class Carpa {
    private int id;
    private int capacidad;
    private boolean disponible;
    private boolean vistaMar;
    private boolean sombrilla;

    public Carpa() {}

    public Carpa(int capacidad, boolean vistaMar, boolean sombrilla) {
        this.capacidad = capacidad;
        this.vistaMar = vistaMar;
        this.sombrilla = sombrilla;
        this.disponible = true;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public boolean isVistaMar() { return vistaMar; }
    public void setVistaMar(boolean vistaMar) { this.vistaMar = vistaMar; }

    public boolean isSombrilla() { return sombrilla; }
    public void setSombrilla(boolean sombrilla) { this.sombrilla = sombrilla; }

    @Override
    public String toString() {
        return "Carpa #" + id + " (" + capacidad + " personas, " +
                (vistaMar ? "frente al mar" : "interior") +
                (sombrilla ? ", sombrilla" : "") + ")";
    }
}

