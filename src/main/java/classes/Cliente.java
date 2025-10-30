package classes;

public class Cliente {
    private int id;
    private String nombre;
    private String dni;
    private String telefono;

    public Cliente() {}

    public Cliente(String nombre, String dni, String telefono) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return nombre + " (DNI: " + dni + ")";
    }
}
