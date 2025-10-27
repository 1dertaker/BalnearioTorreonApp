package classes;

public class Cliente {
    private int id;
    private String nombre;
    private String contacto;

    public Cliente(int id, String nombre, String contacto) {
        this.id = id; this.nombre = nombre; this.contacto = contacto;
    }
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getContacto() { return contacto; }
}
