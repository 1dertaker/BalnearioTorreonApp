package repo;

import classes.;
import com.google.gson.reflect.TypeToken;

import java.io.;
import java.util.*;

public class RepositorioCarpas extends RepositorioGenerico<Carpa> implements IRepositorio<Carpa> {
    public RepositorioCarpas() {
        super("carpas.json", new TypeToken<List<Carpa>>() {
        }.getType());
    }

    @Override
    public Carpa agregar(Carpa c) {
        List<Carpa> carpas = readAll();
        int nextId = carpas.stream().mapToInt(Carpa::getId).max().orElse(0) + 1;
        c.setId(nextId);
        c.setDisponible(true);
        carpas.add(c);
        saveAll(carpas);
        return c;
    }

    @Override
    public List<Carpa> listar() {
        return readAll();
    }

    @Override
    public Optional<Carpa> buscarPorId(int id) {
        return readAll().stream().filter(c -> c.getId() == id).findFirst();
    }

    @Override
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
