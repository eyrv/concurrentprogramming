package servidor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;
    private final String arquivoJson = "src/main/resources/livros.json";

    public Biblioteca() {
        this.livros = carregarLivros();
    }

    private List<Livro> carregarLivros() {
        try (FileReader reader = new FileReader(arquivoJson)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            Type listType = new TypeToken<ArrayList<Livro>>() {}.getType();
            return gson.fromJson(jsonObject.get("livros"), listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void salvarLivros() {
        try (FileWriter writer = new FileWriter(arquivoJson)) {
            Gson gson = new Gson();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("livros", gson.toJsonTree(livros));
            gson.toJson(jsonObject, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized List<Livro> listarLivros() {
        return new ArrayList<>(livros);
    }

    public synchronized boolean alugarLivro(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo) && livro.getExemplares() > 0) {
                livro.setExemplares(livro.getExemplares() - 1);
                salvarLivros();
                return true;
            }
        }
        return false;
    }

    public synchronized boolean devolverLivro(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livro.setExemplares(livro.getExemplares() + 1);
                salvarLivros();
                return true;
            }
        }
        return false;
    }

    public synchronized void cadastrarLivro(Livro livro) {
        livros.add(livro);
        salvarLivros();
    }
}
