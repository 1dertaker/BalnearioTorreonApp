package web.balneariotorreonapp.ui;

import classes.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import excepciones.*;
import manejoJson.JSONUtiles;
import repo.*;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.*;
import com.google.gson.reflect.TypeToken;
import web.balneariotorreonapp.HelloController;


public class JavaBridge {

    private RepositorioCarpas repoCarpas;
    private RepositorioClientes repoClientes;
    private RepositorioReservas repoReservas;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final Path PATH_CARPAS = Path.of("data", "carpas.json");
    private final Path PATH_CLIENTES = Path.of("data", "clientes.json");
    private final Path PATH_RESERVAS = Path.of("data", "reservas.json");
    private final HelloController controller;

    public JavaBridge(HelloController controller) {
        this.controller = controller;
        this.repoCarpas = new RepositorioCarpas();
        this.repoClientes = new RepositorioClientes();
        this.repoReservas = new RepositorioReservas();
        System.out.println("JavaBridge iniciado - Interno");
    }

    public JavaBridge(RepositorioCarpas rc, RepositorioClientes rcl, RepositorioReservas rr, HelloController controller) {
        this.repoCarpas = rc;
        this.repoClientes = rcl;
        this.repoReservas = rr;
        this.controller = controller;
        System.out.println("JavaBridge iniciado - Externo");
    }

    public String crearCarpa(int capacidad, boolean vistaMar, boolean sombrilla) {
        Map<String, Object> res = new HashMap<>();
        try {
            if (capacidad <= 0) throw new CapacidadInvalidaException();

            Carpa carpa = new Carpa(capacidad, vistaMar, sombrilla);
            repoCarpas.agregar(carpa);
            JSONUtiles.guardarLista(PATH_CARPAS, repoCarpas.listar());

            res.put("ok", true);
            res.put("id", carpa.getId());
            res.put("mensaje", "Carpa creada con éxito");
        } catch (RuntimeException e) {
            res.put("ok", false);
            res.put("error", e.getMessage());
        }
        return gson.toJson(res);
    }

    public boolean eliminarCarpa(int id) {
        return controller.eliminarCarpa(id);
    }

    public String listarCarpasJson() {
        return gson.toJson(repoCarpas.listar());
    }

    public String crearCliente(String nombre, String dni, String telefono) {
        Map<String, Object> res = new HashMap<>();
        try {
            if (nombre == null || nombre.isBlank()) throw new NombreInvalidoException();
            if (dni == null || dni.isBlank()) throw new DniInvalidoException();

            Cliente cliente = new Cliente(nombre, dni, telefono);
            repoClientes.agregar(cliente);
            JSONUtiles.guardarLista(PATH_CLIENTES, repoClientes.listar());

            res.put("ok", true);
            res.put("id", cliente.getId());
            res.put("mensaje", "Cliente agregado correctamente");
        } catch (RuntimeException e) {
            res.put("ok", false);
            res.put("error", e.getMessage());
        }
        return gson.toJson(res);
    }

    public boolean eliminarCliente(int id) {
        return controller.eliminarCliente(id);
    }

    public String listarClientesJson() {
        return gson.toJson(repoClientes.listar());
    }

    public String crearReserva(int idCliente, int idCarpa, String desde, String hasta, String tipo) {
        Map<String, Object> res = new HashMap<>();
        try {
            Optional<Cliente> clienteOpt = repoClientes.buscarPorId(idCliente);
            Optional<Carpa> carpaOpt = repoCarpas.buscarPorId(idCarpa);

            if (clienteOpt.isEmpty()) throw new ClienteNoEncontradoException();
            if (carpaOpt.isEmpty()) throw new CarpaNoEncontradaException();

            Carpa carpa = carpaOpt.get();
            if (!carpa.isDisponible()) throw new CarpaNoDisponibleException();

            Reserva reserva = new Reserva(idCliente, idCarpa, desde, hasta, tipo);
            repoReservas.agregar(reserva);

            carpa.setDisponible(false);
            repoCarpas.actualizar(carpa);

            JSONUtiles.guardarLista(PATH_RESERVAS, repoReservas.listar());
            JSONUtiles.guardarLista(PATH_CARPAS, repoCarpas.listar());

            res.put("ok", true);
            res.put("reservaId", reserva.getId());
            res.put("mensaje", "Reserva creada correctamente");
        } catch (RuntimeException e) {
            res.put("ok", false);
            res.put("error", e.getMessage());
        }
        return gson.toJson(res);
    }

    public boolean eliminarReserva(int id) {
        return controller.eliminarReserva(id);
    }

    public String listarReservasJson() {
        return gson.toJson(repoReservas.listar());
    }

    public String buscarReservasPorCliente(int idCliente) {
        return gson.toJson(repoReservas.buscarPorCliente(idCliente));
    }

    public String buscarReservasPorCarpa(int idCarpa) {
        return gson.toJson(repoReservas.buscarPorCarpa(idCarpa));
    }

    public double calcularPrecioReserva(String tipo, boolean sombrilla, boolean vistaMar) {
        return controller.calcularPrecioReserva(tipo, sombrilla, vistaMar);
    }

    public String recargarDatos() {
        try {
            Type listCarpa = new TypeToken<List<Carpa>>() {}.getType();
            Type listCliente = new TypeToken<List<Cliente>>() {}.getType();
            Type listReserva = new TypeToken<List<Reserva>>() {}.getType();

            List<Carpa> carpas = JSONUtiles.leerLista(PATH_CARPAS, listCarpa);
            List<Cliente> clientes = JSONUtiles.leerLista(PATH_CLIENTES, listCliente);
            List<Reserva> reservas = JSONUtiles.leerLista(PATH_RESERVAS, listReserva);

            return gson.toJson(Map.of(
                    "ok", true,
                    "carpas", carpas,
                    "clientes", clientes,
                    "reservas", reservas
            ));
        } catch (Exception e) {
            return gson.toJson(Map.of(
                    "ok", false,
                    "error", e.getMessage()
            ));
        }
    }
}

