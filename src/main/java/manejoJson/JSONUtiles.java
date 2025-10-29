package manejoJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.*;

public class JSONUtiles {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public static <T> List<T> leerLista(Path ruta, Type tipoLista) {
        try {
            if (!Files.exists(ruta)) {
                System.out.println("No se encontró el archivo " + ruta + ". Se creará vacío.");
                crearArchivoVacio(ruta);
                return new ArrayList<>();
            }

            String json = Files.readString(ruta);
            if (json.isBlank()) {
                System.out.println("El archivo " + ruta + " está vacío. Devolviendo lista vacía.");
                return new ArrayList<>();
            }

            List<T> lista = gson.fromJson(json, tipoLista);
            if (lista == null) {
                System.out.println("Error al parsear JSON de " + ruta + ". Devolviendo lista vacía.");
                return new ArrayList<>();
            }

            return lista;

        } catch (Exception e) {
            System.err.println("Error leyendo archivo JSON: " + ruta);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public static <T> void guardarLista(Path ruta, List<T> lista) {
        try {
            if (!Files.exists(ruta.getParent())) {
                Files.createDirectories(ruta.getParent());
            }

            String json = gson.toJson(lista);
            Files.writeString(ruta, json, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Guardado correctamente en " + ruta);

        } catch (Exception e) {
            System.err.println("Error guardando JSON en " + ruta);
            e.printStackTrace();
        }
    }


    private static void crearArchivoVacio(Path ruta) {
        try {
            if (!Files.exists(ruta.getParent())) {
                Files.createDirectories(ruta.getParent());
            }
            Files.writeString(ruta, "[]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
