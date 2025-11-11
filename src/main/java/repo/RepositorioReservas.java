package repo;

import classes.;

import com.google.gson.reflect.TypeToken;
import java.io.;
import java.util.*;
import java.util.stream.Collectors;


public class RepositorioReservas extends RepositorioGenerico<Reserva> implements IRepositorio<Reserva> {
    public RepositorioReservas() {
        super("reservas.json", new TypeToken<List<Reserva>>() {}.getType());
    }

    @Override
    public Reserva agregar(Reserva r) {
        List<Reserva> reservas = readAll();
        int nextId = reservas.stream().mapToInt(Reserva::getId).max().orElse(0) + 1;
        r.setId(nextId);
        reservas.add(r);
        saveAll(reservas);
        return r;
    }

    @Override
    public List<Reserva> listar() {
        return readAll();
    }

    @Override
    public Optional<Reserva> buscarPorId(int id) {
        return readAll().stream().filter(r -> r.getId() == id).findFirst();
    }

    @Override
    public boolean eliminar(int id) {
        List<Reserva> reservas = readAll();
        boolean removed = reservas.removeIf(r -> r.getId() == id);
        if (removed) saveAll(reservas);
        return removed;
    }

    public List<Reserva> buscarPorCliente(int clienteId) {
        return readAll().stream()
                .filter(r -> r.getClienteId() == clienteId)
                .collect(Collectors.toList());
    }

    public List<Reserva> buscarPorCarpa(int carpaId) {
        return readAll().stream()
                .filter(r -> r.getCarpaId() == carpaId)
                .collect(Collectors.toList());
    }
}
