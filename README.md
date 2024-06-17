## Projeto de Gerenciamento de Biblioteca com Sockets

Este projeto implementa um servidor que gerencia um registro/cadastro de livros de uma biblioteca. Ele permite listar, alugar, devolver e cadastrar livros. A comunicação entre o cliente e o servidor é feita via sockets.

## Requisitos

Java Development Kit (JDK) 8 ou superior.
Biblioteca GSON (gson-2.8.9.jar) disponível na pasta lib.

## Preparando o Ambiente

## 1. Clonar o Repositório

Clone o repositório para sua máquina local:

```bash
git clone https://github.com/seu-usuario/BibliotecaSocket.git
```

## Baixar e Adicionar a Biblioteca GSON

Certifique-se de que o arquivo gson-2.8.9.jar está na pasta lib/. Se não estiver, baixe-o do site oficial do Gson e coloque-o na pasta lib.

## Compilando o Projeto

Para compilar o projeto, siga as instruções abaixo:

1. Abra um terminal e navegue até o diretório do projeto:

```bash
cd BibliotecaSocket
```

2. Compile os arquivos Java, especificando a saída para o diretório bin e incluindo a biblioteca GSON no classpath:

```bash
javac -d bin -cp lib/gson-2.8.9.jar src/main/java/servidor/*.java 
```
Isso compilará todos os arquivos .java na pasta src/main/java/servidor e colocará os arquivos .class resultantes na pasta bin.

## Executando o Projeto

1. Executar o Servidor
Primeiro, inicie o servidor. Abra um terminal e execute:

```bash
java -cp "bin;lib/gson-2.8.9.jar" servidor.Servidor
```

Você verá a mensagem:

Servidor iniciado e aguardando conexões na porta 8080

2. Executar o Cliente
Com o servidor em execução, abra um novo terminal para iniciar o cliente:

```bash
java -cp "bin;lib/gson-2.8.9.jar" servidor.Cliente
```

O cliente exibirá um menu com opções para listar, alugar, devolver ou cadastrar livros.

Menu do Cliente
Ao executar o cliente, você verá as seguintes opções:

1. Listar livros disponíveis
2. Alugar um livro
3. Devolver um livro
4. Cadastrar um novo livro
5. Sair

Escolha uma opção:
1. Listar Livros
O cliente enviará uma solicitação ao servidor para listar todos os livros disponíveis. O servidor retornará a lista de livros, que será exibida no cliente.

2. Alugar um Livro
O cliente solicitará o título do livro a ser alugado. Se o livro estiver disponível, o servidor reduzirá o número de exemplares e confirmará o aluguel.

3. Devolver um Livro
O cliente solicitará o título do livro a ser devolvido. O servidor aumentará o número de exemplares e confirmará a devolução.

4. Cadastrar um Novo Livro
O cliente solicitará os detalhes do novo livro (título, autor, gênero, exemplares). O servidor adicionará o novo livro ao registro e confirmará o cadastro.

5. Sair
Fecha a conexão com o servidor e encerra o cliente.

## Considerações Finais

Integridade do Arquivo JSON: O arquivo livros.json é atualizado automaticamente pelo servidor sempre que ocorre um aluguel, devolução ou cadastro de livro.
Manutenção: Certifique-se de que o servidor esteja sempre em execução para atender às solicitações dos clientes.

Filipe Mesquita Castelo Branco, Ciência da Computação, 5˚ semestre, Universidade Católica de Brasília, Programação Concorrente e Distribuída.
Caio Alexandre Cutrim Medeiros, Ciência da Computação, 5˚ semestre, Universidade Católica de Brasília, Programação Concorrente e Distribuída.