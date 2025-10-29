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

public class RepositorioCarpas {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path filePath = Path.of(System.getProperty("user.dir"), "carpas.json");


    public RepositorioCarpas() {
        try {
            Files.createDirectories(filePath.getParent());
            if (!Files.exists(filePath)) Files.writeString(filePath, "[]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Carpa> readAll() {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            Type listType = new TypeToken<List<Carpa>>() {}.getType();
            List<Carpa> carpas = gson.fromJson(reader, listType);
            return carpas != null ? carpas : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveAll(List<Carpa> carpas) {
        try (Writer writer = Files.newBufferedWriter(filePath)) {
            gson.toJson(carpas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Carpa agregar(Carpa c) {
        List<Carpa> carpas = readAll();
        int nextId = carpas.stream().mapToInt(Carpa::getId).max().orElse(0) + 1;
        c.setId(nextId);
        c.setDisponible(true);
        carpas.add(c);
        saveAll(carpas);
        return c;
    }

    public List<Carpa> listar() {
        return readAll();
    }


    public Optional<Carpa> buscarPorId(int id) {
        return readAll().stream().filter(c -> c.getId() == id).findFirst();
    }

    public boolean eliminar(int id) {
        List<Carpa> carpas = readAll();
        boolean removed = carpas.removeIf(c -> c.getId() == id);
        if (removed) saveAll(carpas);
        return removed;
    }

    public void actualizar(Carpa carpaActualizada) {
        List<Carpa> carpas = readAll();
        for (int i = 0; i < carpas.size(); i++) {
            if (carpas.get(i).getId() == carpaActualizada.getId()) {
                carpas.set(i, carpaActualizada);
                break;
            }
        }
        saveAll(carpas);
    }
}
