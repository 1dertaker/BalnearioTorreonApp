package repo;

import classes.;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public abstract class RepositorioGenerico<T> {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path filePath;
    private final Type listType;

    protected RepositorioGenerico(String fileName, Type listType) {
        this.filePath = Path.of(System.getProperty("user.dir"), fileName);
        this.listType = listType;
        try {
            Files.createDirectories(filePath.getParent());
            if (!Files.exists(filePath)) Files.writeString(filePath, "[]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected List<T> readAll() {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            List<T> items = gson.fromJson(reader, listType);
            return items != null ? items : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    protected void saveAll(List<T> items) {
        try (Writer writer = Files.newBufferedWriter(filePath)) {
            gson.toJson(items, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
