package petshop;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
    	
        Gerenciamento.iniciarFuncionarios();
        int opcao1 = -1;
        while (opcao1 != 0) {
            limparTela();
            System.out.println("=================================");
            System.out.println("       SISTEMA PETSHOP           ");
            System.out.println("=================================");
            System.out.println("1. Entrar como Cliente");
            System.out.println("2. Entrar como Funcionário");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao1 = Integer.parseInt(sc.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Erro: Digite apenas números. Pressione Enter para continuar...");
                sc.nextLine();
                continue;
            }
            switch (opcao1) {
                case 1 -> menuCliente();
                case 2 -> menuFuncionario();
                case 0 -> System.out.println("Saindo..");
                default -> {
                    System.out.println("Opção inválida! Pressione Enter para continuar...");
                    sc.nextLine();
                }
            }
        }
    }
    
    private static void menuCliente() {
        int opcao2 = -1;
        while (opcao2 != 3) { 
            limparTela();
            System.out.println("--- ACESSO DO CLIENTE ---");
            System.out.println("1. Já sou cadastrado (Fazer Login)");
            System.out.println("2. Cadastrar");
            System.out.println("3. Voltar");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao2 = Integer.parseInt(sc.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Erro: Digite apenas números. Pressione Enter para continuar...");
                sc.nextLine();
                continue;
            }
            
            switch (opcao2) {
                case 1 -> {
                    limparTela();
                    System.out.print("Digite seu CPF para entrar: ");
                    String cpf = sc.nextLine();
                    
                    Cliente clienteLogin = Gerenciamento.buscarCliente(cpf);
                    if(clienteLogin != null) {
                        menuClienteEntrou(clienteLogin); 
                        System.out.println("CPF não encontrado! Pressione Enter para continuar...");
                        sc.nextLine();
                    }
                }
                case 2 -> {
                    Cliente novoCliente = cadastrarCliente();
                    if (novoCliente != null) {
                        menuClienteEntrou(novoCliente);
                    }
                }
                case 3 -> System.out.println("Voltando ao menu inicial...");
                default -> {
                    System.out.println("Opção inválida! Pressione Enter para continuar...");
                    sc.nextLine();
                }
            }
        }
    }
    
    private static Cliente cadastrarCliente() {
        limparTela();
        System.out.println("--- CADASTRO DE NOVO CLIENTE ---");
        System.out.print("Nome: "); String nomeNovo = sc.nextLine();
        System.out.print("CPF: "); String cpfNovo = sc.nextLine();
        System.out.print("Telefone: "); String telNovo = sc.nextLine();
        System.out.print("Email: "); String emailNovo = sc.nextLine();
        System.out.print("Endereço: "); String endNovo = sc.nextLine();

        Cliente novo = new Cliente(nomeNovo, cpfNovo, telNovo, emailNovo, endNovo);
        Gerenciamento.salvarCliente(novo);
        
        System.out.println("\nCadastro efetuado com sucesso! Redirecionando para a sua conta... Pressione Enter.");
        sc.nextLine();
        return novo;
    }
    
    private static void menuClienteEntrou(Cliente clienteLogin) {
        int opcao3 = -1;
        while (opcao3 != 0) {
            limparTela();
            System.out.println("--- BEM-VINDO, " + clienteLogin.getNome().toUpperCase() + " ---");
            System.out.println("1. Ver meu Perfil");
            System.out.println("2. Atualizar meus Dados");
            System.out.println("3. [PET] Cadastrar Pet");
            System.out.println("4. [PET] Atualizar Pet");
            System.out.println("5. [PET] Listar Meus Pets");
            System.out.println("6. [PET] Excluir Pet");
            System.out.println("7. [SERVIÇO] Agendar Serviço");
            System.out.println("0. Fazer Logout");
            System.out.print("Escolha uma opção: ");

            try {
                opcao3 = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números. Pressione Enter para continuar...");
                sc.nextLine();
                continue;
            }
            
            switch(opcao3) {
                case 1 -> {
                    limparTela();
                    System.out.println("--- MEU PERFIL ---");
                    System.out.println("Nome: " + clienteLogin.getNome());
                    System.out.println("CPF: " + clienteLogin.getCpf());
                    System.out.println("Telefone: " + clienteLogin.getTelefone());
                    System.out.println("Email: " + clienteLogin.getEmail());
                    System.out.println("Endereço: " + clienteLogin.getEndereco());
                    System.out.println("\nPressione Enter para voltar ao painel...");
                    sc.nextLine();
                }
                case 2 -> {
                    limparTela();
                    System.out.println("--- ATUALIZAR MEUS DADOS ---");
                    System.out.print("Digite o novo Telefone: ");
                    String novoTel = sc.nextLine();
                    clienteLogin.setTelefone(novoTel);

                    System.out.print("Digite o novo Email: ");
                    String novoEmail = sc.nextLine();
                    clienteLogin.setEmail(novoEmail);

                    System.out.print("Digite o novo Endereço: ");
                    String novoEnd = sc.nextLine();
                    clienteLogin.setEndereco(novoEnd);

                    Gerenciamento.atualizarCliente(clienteLogin);
                    System.out.println("Pressione Enter para continuar...");
                    sc.nextLine();
                }
                case 3, 4, 5, 6, 7 -> {
                    System.out.println("\n[Aviso] Funcionalidade a ser implementada pelo grupo. Pressione Enter...");
                    sc.nextLine();
                }
                case 0 -> System.out.println("Efetuando logout da conta...");
                default -> {
                    System.out.println("Opção inválida! Pressione Enter para continuar...");
                    sc.nextLine();
                }
            }
        }
    }
    
    private static void menuFuncionario() {
        limparTela();
        System.out.print("Digite sua matrícula de funcionário: ");
        String matricula = sc.nextLine();

        if (Gerenciamento.validarFuncionario(matricula)) {
            System.out.println("Painel do Funcionário Liberado. Pressione Enter para voltar...");
            sc.nextLine();
        } else {
            System.out.println("Matrícula não cadastrada! Pressione Enter para voltar...");
            sc.nextLine();
        }
    }

    // simular a limpeza do console da IDE
    private static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}