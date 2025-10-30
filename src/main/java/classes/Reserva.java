package classes;

public class Reserva {
    private int id;
    private int clienteId;
    private int carpaId;
    private String fechaDesde;
    private String fechaHasta;
    private String tipo;
    private boolean activa;

    public Reserva() {}

    public Reserva(int clienteId, int carpaId, String fechaDesde, String fechaHasta, String tipo) {
        this.clienteId = clienteId;
        this.carpaId = carpaId;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.tipo = tipo;
        this.activa = true;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public int getCarpaId() { return carpaId; }
    public void setCarpaId(int carpaId) { this.carpaId = carpaId; }

    public String getFechaDesde() { return fechaDesde; }
    public void setFechaDesde(String fechaDesde) { this.fechaDesde = fechaDesde; }

    public String getFechaHasta() { return fechaHasta; }
    public void setFechaHasta(String fechaHasta) { this.fechaHasta = fechaHasta; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }

    @Override
    public String toString() {
        return "Reserva #" + id + " [Cliente " + clienteId + ", Carpa " + carpaId +
                ", " + tipo + " desde " + fechaDesde + " hasta " + fechaHasta + "]";
    }
}


