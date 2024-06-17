package servidor;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Servidor {
    private static final int PORTA = 8080;

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Servidor iniciado na porta " + PORTA);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String comando = in.readLine();
                    if (comando == null) continue;

                    switch (comando.toLowerCase()) {
                        case "listar":
                            List<Livro> livros = biblioteca.listarLivros();
                            out.println(new Gson().toJson(livros));
                            break;
                        case "alugar":
                            String tituloAluguel = in.readLine();
                            boolean sucessoAluguel = biblioteca.alugarLivro(tituloAluguel);
                            out.println(sucessoAluguel ? "Livro alugado com sucesso." : "Livro não disponível.");
                            break;
                        case "devolver":
                            String tituloDevolucao = in.readLine();
                            boolean sucessoDevolucao = biblioteca.devolverLivro(tituloDevolucao);
                            out.println(sucessoDevolucao ? "Livro devolvido com sucesso." : "Erro na devolução.");
                            break;
                        case "cadastrar":
                            String jsonLivro = in.readLine();
                            Livro novoLivro = new Gson().fromJson(jsonLivro, Livro.class);
                            biblioteca.cadastrarLivro(novoLivro);
                            out.println("Livro cadastrado com sucesso.");
                            break;
                        default:
                            out.println("Comando inválido.");
                    }
                } catch (IOException e) {
                    System.err.println("Erro de comunicação: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
}
