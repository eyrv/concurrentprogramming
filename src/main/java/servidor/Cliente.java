package servidor;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import com.google.gson.reflect.TypeToken;


public class Cliente {
    private static final String HOST = "localhost";
    private static final int PORTA = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORTA);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Você está conectado ao servidor!");

            while (true) {
                System.out.println("Escolha entre uma das operação: listar, alugar, devolver, cadastrar, sair");
                String operacao = scanner.nextLine();

                if (operacao.equalsIgnoreCase("sair")) break;

                out.println(operacao);

                switch (operacao.toLowerCase()) {
                    case "listar":
                        String respostaListagem = in.readLine();
                        List<Livro> livros = new Gson().fromJson(respostaListagem, new TypeToken<List<Livro>>(){}.getType());
                        livros.forEach(System.out::println);
                        break;
                    case "alugar":
                        System.out.println("Digite o nome do livro que deseja fazer o aluguel:");
                        String nomeAluguel = scanner.nextLine();
                        out.println(nomeAluguel);
                        System.out.println(in.readLine());
                        break;
                    case "devolver":
                        System.out.println("Digite o nome do livro que deseja devolver:");
                        String nomeDevolucao = scanner.nextLine();
                        out.println(nomeDevolucao);
                        System.out.println(in.readLine());
                        break;
                    case "cadastrar":
                        System.out.println("Digite o nome do autor do livro:");
                        String autor = scanner.nextLine();
                        System.out.println("Digite o nome do livro:");
                        String nome = scanner.nextLine();
                        System.out.println("Digite o gênero do livro:");
                        String genero = scanner.nextLine();
                        System.out.println("Digite a quantidade de exemplares:");
                        int exemplares = Integer.parseInt(scanner.nextLine());

                        Livro novoLivro = new Livro(autor, nome, genero, exemplares);
                        out.println(new Gson().toJson(novoLivro));
                        System.out.println(in.readLine());
                        break;
                    default:
                        System.out.println("Operação inválida.");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro na comunicação entre o servidor: " + e.getMessage());
        }
    }
}
