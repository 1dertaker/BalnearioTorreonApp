package classes;

public abstract class Carpa {
    public int id;
    protected String numero;
    protected int capacidad; // por ejemplo 4 personas
    protected boolean piletaLibre;
    protected boolean sombrilla;
    protected boolean habilitada; // true = activa, false = dada de baja

    public Carpa(int id, String numero, int capacidad, boolean piletaLibre, boolean sombrilla) {
        this.id = id;
        this.numero = numero;
        this.capacidad = capacidad;
        this.piletaLibre = piletaLibre;
        this.sombrilla = sombrilla;
        this.habilitada = true;
    }

    public Carpa(int id, int capacidad, boolean pileta, boolean sombrilla, double precioDia, double precioMes, double precioTemporada, boolean disponible) {
    }

    public int getId() { return id; }
    public String getNumero() { return numero; }
    public int getCapacidad() { return capacidad; }
    public boolean isPiletaLibre() { return piletaLibre; }
    public boolean isSombrilla() { return sombrilla; }
    public boolean isHabilitada() { return habilitada; }
    public void setHabilitada(boolean habilitada) { this.habilitada = habilitada; }

    public abstract String getTipo();
}
