import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Evento> listaDeEventos = new ArrayList<>();
    private static final GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos();
    private static final Scanner scanner = new Scanner(System.in);



    public static  void main(String[] args) {
        listaDeEventos = gerenciador.carregarEventos();
        System.out.println(listaDeEventos.size() +  "evento(s) existente(s) foram carregado(s).");

        int opcao = 0;

        while (opcao != 9) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite apenas o número da opção.");
                opcao = 0;
                continue;
            }
            switch (opcao) {
                case 1:
                    cadastrarNovoEvento();
                    break;
                case 2:
                    listarTodosOsEventos();
                    break;
                case 9:
                    System.out.println("Obrigado por usar o sistema. Saindo...");
                    break;
                default:System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n======================================");
        System.out.println("   SISTEMA DE EVENTOS DA CIDADE");
        System.out.println("======================================");
        System.out.println("1. Cadastrar Novo Evento");
        System.out.println("2. Listar Todos os Eventos");

        System.out.println("9. Sair");
        System.out.print("--> Digite sua opção: ");
    }

    private static void cadastrarNovoEvento() {
        System.out.println("\n--- Cadastro de Novo Evento ---");

        System.out.print("Nome do Evento: ");
        String nome = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Categoria (Ex: Festa, Show): ");
        String categoria = scanner.nextLine();

        LocalDateTime horario = null;
        while (horario == null) {
            System.out.print("Data e Horário (formato dd/MM/yyyy HH:mm): ");
            String dataHoraString = scanner.nextLine();

            try {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

                horario = LocalDateTime.parse(dataHoraString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data/hora inválido. Use o formato dd/MM/yyyy HH:mm. Tente novamente.");
            }
        }

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();


        Evento novoEvento = new Evento(nome, endereco, categoria, horario, descricao);

        listaDeEventos.add(novoEvento);

        gerenciador.SalvarEventos(listaDeEventos);

        System.out.println("\n*** Evento cadastrado com sucesso! ***");
    }

    private static void listarTodosOsEventos() {
        System.out.println("\n--- Lista de Todos os Eventos ---");


        if (listaDeEventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado no momento.");
        } else {

            for (Evento evento : listaDeEventos) {
                System.out.println(evento);
            }
        }
    }
}

