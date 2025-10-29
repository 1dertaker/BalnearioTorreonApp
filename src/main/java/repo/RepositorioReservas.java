package repo;

import classes.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class RepositorioReservas {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path filePath = Path.of(System.getProperty("user.dir"), "reservas.json");

    public RepositorioReservas() {
        try {
            Files.createDirectories(filePath.getParent());
            if (!Files.exists(filePath)) Files.writeString(filePath, "[]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Reserva> readAll() {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            Type listType = new TypeToken<List<Reserva>>() {}.getType();
            List<Reserva> reservas = gson.fromJson(reader, listType);
            return reservas != null ? reservas : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveAll(List<Reserva> reservas) {
        try (Writer writer = Files.newBufferedWriter(filePath)) {
            gson.toJson(reservas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Reserva agregar(Reserva r) {
        List<Reserva> reservas = readAll();
        int nextId = reservas.stream().mapToInt(Reserva::getId).max().orElse(0) + 1;
        r.setId(nextId);
        reservas.add(r);
        saveAll(reservas);
        return r;
    }

    public List<Reserva> listar() {
        return readAll();
    }

    public Optional<Reserva> buscarPorId(int id) {
        return readAll().stream().filter(r -> r.getId() == id).findFirst();
    }

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
