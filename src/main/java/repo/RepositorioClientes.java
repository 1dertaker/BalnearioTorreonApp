package repo;

import classes.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.*;



public class RepositorioClientes extends RepositorioGenerico<Cliente> implements IRepositorio<Cliente> {
    public RepositorioClientes() {
        super("clientes.json", new TypeToken<List<Cliente>>() {}.getType());
    }

    @Override
    public Cliente agregar(Cliente c) {
        List<Cliente> clientes = readAll();
        int nextId = clientes.stream().mapToInt(Cliente::getId).max().orElse(0) + 1;
        c.setId(nextId);
        clientes.add(c);
        saveAll(clientes);
        return c;
    }

    @Override
    public List<Cliente> listar() {
        return readAll();
    }

    @Override
    public Optional<Cliente> buscarPorId(int id) {
        return readAll().stream().filter(c -> c.getId() == id).findFirst();
    }

    @Override
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
