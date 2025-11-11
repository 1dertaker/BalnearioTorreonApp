package web.balneariotorreonapp;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import repo.*;
import classes.*;
import com.google.gson.Gson;
import service.PricingEngine;

import java.nio.file.Path;
import java.util.*;


public class HelloController {

    @FXML
    private WebView webView;

    private final Gson gson = new Gson();

    private RepositorioCarpas repoCarpas;
    private RepositorioClientes repoClientes;
    private RepositorioReservas repoReservas;


    private final Path PATH_CARPAS = Path.of("data", "carpas.json");
    private final Path PATH_CLIENTES = Path.of("data", "clientes.json");
    private final Path PATH_RESERVAS = Path.of("data", "reservas.json");


    public HelloController(WebEngine webEngine) {
        System.out.println("HelloController y JAVA FX cargados...");

        repoCarpas = new RepositorioCarpas();
        repoClientes = new RepositorioClientes();
        repoReservas = new RepositorioReservas();
    }

    @FXML
    public void initialize() {


        System.out.println("Carpas cargadas: " + repoCarpas.listar().size());
        System.out.println("Clientes cargados: " + repoClientes.listar().size());
        System.out.println("Reservas cargadas: " + repoReservas.listar().size());

        try {
            WebEngine engine = webView.getEngine();
            String resource = getClass().getResource("/index.html").toExternalForm();
            engine.load(resource);

            engine.getLoadWorker().stateProperty().addListener((obs, old, newState) -> {
                if (newState == Worker.State.SUCCEEDED) {
                    JSObject window = (JSObject) engine.executeScript("window");
                    window.setMember("javaApp", this);
                    System.out.println("Carpetas y archivos cargados correctamente...");
                    System.out.println("HTML cargado correctamente...");
                }
            });

        } catch (Exception e) {
            System.err.println("Error al cargar el HTML: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public String crearCarpa(int capacidad, boolean vistaMar, boolean sombrilla) {
        try {
            Carpa c = new Carpa();
            c.setCapacidad(capacidad);
            c.setVistaMar(vistaMar);
            c.setSombrilla(sombrilla);
            c.setDisponible(true);

            repoCarpas.agregar(c);

            return gson.toJson(Map.of("ok", true, "id", c.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(Map.of("ok", false, "error", e.getMessage()));
        }
    }

    public boolean eliminarCarpa(int idCarpa) {

        return repoCarpas.eliminar(idCarpa);
    }

    public String listarCarpasJson() {
        try {
            return gson.toJson(repoCarpas.listar());
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }
    }


    public String crearCliente(String nombre, String dni, String telefono) {
        try {
            Cliente cli = new Cliente();
            cli.setNombre(nombre);
            cli.setDni(dni);
            cli.setTelefono(telefono);

            repoClientes.agregar(cli);

            return gson.toJson(Map.of("ok", true, "id", cli.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(Map.of("ok", false, "error", e.getMessage()));
        }
    }

    public boolean eliminarCliente(int idCliente) {
        List<Reserva> reservasCliente = repoReservas.buscarPorCliente(idCliente);
        for (Reserva r : reservasCliente) {
            repoCarpas.buscarPorId(r.getCarpaId()).ifPresent(c -> {
                c.setDisponible(true);
                repoCarpas.actualizar(c);
            });
            repoReservas.eliminar(r.getId());
        }

        return repoClientes.eliminar(idCliente);
    }

    public String listarClientesJson() {
        try {
            return gson.toJson(repoClientes.listar());
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }
    }

    public String crearReserva(int clienteId, int carpaId, String fechaDesde, String fechaHasta, String tipo) {
        try {
            Optional<Cliente> cliOpt = repoClientes.buscarPorId(clienteId);
            Optional<Carpa> carpaOpt = repoCarpas.buscarPorId(carpaId);

            if (cliOpt.isEmpty())
                return gson.toJson(Map.of("ok", false, "error", "Cliente no encontrado"));
            if (carpaOpt.isEmpty())
                return gson.toJson(Map.of("ok", false, "error", "Carpa no encontrada"));

            Reserva r = new Reserva(clienteId, carpaId, fechaDesde, fechaHasta, tipo);
            repoReservas.agregar(r);

            Carpa carpa = carpaOpt.get();
            carpa.setDisponible(false);
            repoCarpas.actualizar(carpa);

            return gson.toJson(Map.of("ok", true, "reservaId", r.getId()));

        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(Map.of("ok", false, "error", e.getMessage()));
        }
    }

    public boolean eliminarReserva(int idReserva) {
        Optional<Reserva> opt = repoReservas.buscarPorId(idReserva);
        if (opt.isEmpty()) return false;

        Reserva res = opt.get();
        repoCarpas.buscarPorId(res.getCarpaId()).ifPresent(c -> {
            c.setDisponible(true);
            repoCarpas.actualizar(c);
        });

        return repoReservas.eliminar(idReserva);
    }

    public String listarReservasJson() {
        try {
            return gson.toJson(repoReservas.listar());
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }
    }

    public double calcularPrecioReserva(String tipo, boolean sombrilla, boolean vistaMar) {
        return PricingEngine.calcularPrecio(tipo, sombrilla, vistaMar);
    }


    public boolean estaListo() {
        boolean listo = repoCarpas != null && repoClientes != null && repoReservas != null;
        System.out.println("Repositorios cargados. " + listo);
        return listo;
    }

    public void log(String msg) {
        System.out.println("Log. " + msg);
    }
}
