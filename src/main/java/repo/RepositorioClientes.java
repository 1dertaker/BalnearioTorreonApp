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

public class RepositorioClientes {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path filePath = Path.of(System.getProperty("user.dir"), "clientes.json");

    public RepositorioClientes() {
        try {
            Files.createDirectories(filePath.getParent());
            if (!Files.exists(filePath)) Files.writeString(filePath, "[]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Cliente> readAll() {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            Type listType = new TypeToken<List<Cliente>>() {}.getType();
            List<Cliente> clientes = gson.fromJson(reader, listType);
            return clientes != null ? clientes : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveAll(List<Cliente> clientes) {
        try (Writer writer = Files.newBufferedWriter(filePath)) {
            gson.toJson(clientes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Cliente agregar(Cliente c) {
        List<Cliente> clientes = readAll();
        int nextId = clientes.stream().mapToInt(Cliente::getId).max().orElse(0) + 1;
        c.setId(nextId);
        clientes.add(c);
        saveAll(clientes);
        return c;
    }

    public List<Cliente> listar() {
        return readAll();
    }

    public Optional<Cliente> buscarPorId(int id) {
        return readAll().stream().filter(c -> c.getId() == id).findFirst();
    }

    public boolean eliminar(int id) {
        List<Cliente> clientes = readAll();
        boolean removed = clientes.removeIf(c -> c.getId() == id);
        if (removed) saveAll(clientes);
        return removed;
    }

    public void actualizar(Cliente clienteActualizado) {
        List<Cliente> clientes = readAll();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == clienteActualizado.getId()) {
                clientes.set(i, clienteActualizado);
                break;
            }
        }
        saveAll(clientes);
    }
}
