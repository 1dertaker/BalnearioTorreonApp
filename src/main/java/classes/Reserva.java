package classes;


import java.time.LocalDate;
import java.util.Set;

public class Reserva {
    private int id;
    private int carpaId;
    private int clienteId;
    private LocalDate desde;
    private LocalDate hasta;
    private TipoAlquiler tipoAlquiler;
    private Set<ServicioExtra> extras;
    private double precioFinal;

    public Reserva(int id, int carpaId, int clienteId, LocalDate desde, LocalDate hasta, TipoAlquiler tipoAlquiler, Set<ServicioExtra> extras) {
        this.id = id; this.carpaId = carpaId; this.clienteId = clienteId; this.desde = desde; this.hasta = hasta; this.tipoAlquiler = tipoAlquiler; this.extras = extras;
    }

    public int getCarpaId() { return carpaId; }
    public LocalDate getDesde() { return desde; }
    public LocalDate getHasta() { return hasta; }
    public void setPrecioFinal(double p) { this.precioFinal = p; }
    public double getPrecioFinal() { return precioFinal; }
    public TipoAlquiler getTipoAlquiler() { return tipoAlquiler; }
    public Set<ServicioExtra> getExtras() { return extras; }
}
﻿
1dertaker
1ndertaker
 
 
 
 
 https://github.com/1dertaker
