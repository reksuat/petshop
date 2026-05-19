package petshop;
import java.util.List;
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
                        menuClienteEntrou(clienteLogin); }
                        else {
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
        if (Gerenciamento.buscarCliente(cpfNovo) != null) {
            System.out.println("\n[Erro] CPF já cadastrado! Pressione Enter para voltar...");
            sc.nextLine();
            return null;
        }

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
            System.out.println("3. Cadastrar meu Pet");
            System.out.println("4. Ver meus Pets");
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
                case 3 -> {
                    limparTela();
                    System.out.println("--- CADASTRAR MEU PET ---");
                    System.out.print("ID do Pet: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Nome do Pet: ");
                    String nomePet = sc.nextLine();
                    System.out.print("Espécie: ");
                    String especie = sc.nextLine();
                    System.out.print("Raça: ");
                    String raca = sc.nextLine();
                    System.out.print("Idade: ");
                    int idade = Integer.parseInt(sc.nextLine());

                    Pet novoPet = new Pet(id, nomePet, especie, raca, idade, clienteLogin);
                    Gerenciamento.salvarPet(novoPet);
                    System.out.println("\nPressione Enter para continuar...");
                    sc.nextLine();
                }
                case 4 -> {
                    limparTela();
                    System.out.println("--- MEUS PETS ---");
                    List<Pet> todosPets = Gerenciamento.listarPets();
                    boolean temPet = false;
                    for (Pet p : todosPets) {
                        if (p.getDono().getCpf().equals(clienteLogin.getCpf())) {
                            System.out.println("\nID: " + p.getId() + " | Nome: " + p.getNome() + " | Espécie: " + p.getEspecie() + " | Raça: " + p.getRaca() + " | Idade: " + p.getIdade() + " anos");
                            temPet = true;
                        }
                    }
                    if (!temPet) {
                        System.out.println("Você ainda não tem pets cadastrados.");
                    }
                    System.out.println("\nPressione Enter para continuar...");
                    sc.nextLine();
                }
                case 5, 6, 7 -> {
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
        System.out.print("Digite sua matrícula: ");
        String matricula = sc.nextLine();
        Funcionario funcLogin = Gerenciamento.buscarFuncionario(matricula);
        if (funcLogin == null) {
            System.out.println("\nMatrícula não cadastrada! Pressione Enter para voltar...");
            sc.nextLine();
            return;
        }
        System.out.println("\nBem-vindo(a), " + funcLogin.getNome());
        System.out.println("Pressione Enter para continuar...");
        sc.nextLine();
        menuFuncionarioEntrou(funcLogin);
    }
    
    private static void menuFuncionarioEntrou(Funcionario func) {
        int opcao4 = -1;
        while (opcao4 != 0) {
            limparTela();
            System.out.println("=== PAINEL DO FUNCIONÁRIO ===");
            System.out.println("Logado como: " + func.getNome() + " [" + func.getCargo() + "]");
            System.out.println("─────────────────────────────");
            System.out.println("── CLIENTES ──");
            System.out.println("1. Buscar Cliente por CPF");
            System.out.println("2. Listar todos os Clientes");
            System.out.println("3. Excluir Cliente");
            System.out.println("─────────────────────────────");
            System.out.println("── PAINEL PETS ──");
            System.out.println("4. Listar todos os Pets do Sistema");
            System.out.println("─────────────────────────────");
            System.out.println("── FUNCIONÁRIOS ──");
            System.out.println("5. Ver meu Perfil");
            System.out.println("─────────────────────────────");
            System.out.println("0. Fazer Logout");
            System.out.print("Escolha uma opção: ");
 
            try {
                opcao4 = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números. Pressione Enter para continuar...");
                sc.nextLine();
                continue;
            }
            switch (opcao4) {
            case 1 -> {
            	limparTela();
            	System.out.println("--- BUSCAR CLIENTE ---");
            	System.out.println("CPF do cliente: ");
            	String cpf = sc.nextLine();
                Cliente encontrado = Gerenciamento.buscarCliente(cpf);
                if (encontrado != null) {
                    System.out.println("\n[Encontrado]");
                    System.out.println("Nome: "     + encontrado.getNome());
                    System.out.println("CPF: "      + encontrado.getCpf());
                    System.out.println("Telefone: " + encontrado.getTelefone());
                    System.out.println("Email: "    + encontrado.getEmail());
                    System.out.println("Endereço: " + encontrado.getEndereco());
                } else {
                    System.out.println("\n[Não encontrado] Nenhum cliente com esse CPF.");
                }
                System.out.println("\nPressione Enter para continuar...");
                sc.nextLine();
            }
            case 2 -> {
                limparTela();
                System.out.println("--- LISTA DE CLIENTES ---");
                List<Cliente> clientes = Gerenciamento.buscarTodosClientes();
                if (clientes.isEmpty()) {
                    System.out.println("Nenhum cliente cadastrado.");
                } else {
                    int i = 1;
                    for (Cliente cli : clientes) {
                        System.out.println("\n[" + i++ + "] " + cli.getNome()
                            + " | CPF: " + cli.getCpf()
                            + " | Tel: " + cli.getTelefone());
                    }
                }
                System.out.println("\nPressione Enter para continuar...");
                sc.nextLine();

            }
            case 3 -> {
                limparTela();
                System.out.println("--- EXCLUIR CLIENTE ---");
                System.out.print("CPF do cliente a excluir: ");
                String cpf = sc.nextLine();

                Cliente cli = Gerenciamento.buscarCliente(cpf);
                if (cli == null) {
                    System.out.println("\n[Erro] Cliente não encontrado.");
                    System.out.println("Pressione Enter para continuar...");
                    sc.nextLine();
                    break;
                }

                System.out.println("\nCliente encontrado: " + cli.getNome() + " | CPF: " + cli.getCpf());
                System.out.print("Confirmar exclusão? (s/n): ");   // RNF2
                String confirm = sc.nextLine();

                if (confirm.equalsIgnoreCase("s")) {
                    boolean ok = Gerenciamento.excluirCliente(cpf);
                    System.out.println(ok
                        ? "\n[Sucesso] Cliente excluído."
                        : "\n[Erro] Não foi possível excluir.");
                } else if (confirm.equalsIgnoreCase("n")){
                    System.out.println("\nExclusão cancelada.");
                }
                System.out.println("Pressione Enter para continuar...");
                sc.nextLine();

            }
            case 4 -> {
                limparTela();
                System.out.println("--- TODOS OS PETS REGISTRADOS ---");
                List<Pet> pets = Gerenciamento.listarPets();
                if (pets.isEmpty()) {
                    System.out.println("Nenhum pet cadastrado no sistema.");
                } else {
                    for (Pet p : pets) {
                        System.out.println("\nID: " + p.getId() + " | Nome: " + p.getNome() + " | Espécie: " + p.getEspecie() + " | Dono: " + p.getDono().getNome() + " (CPF: " + p.getDono().getCpf() + ")");
                    }
                }
                System.out.println("\nPressione Enter para continuar...");
                sc.nextLine();
            }
            case 5 -> {
                limparTela();
                System.out.println("--- MEU PERFIL ---");
                System.out.println("Nome: " + func.getNome());
                System.out.println("CPF: " + func.getCpf());
                System.out.println("Telefone: " + func.getTelefone());
                System.out.println("Email: " + func.getEmail());
                System.out.println("Cargo: " + func.getCargo());
                System.out.println("Matrícula: " + func.getMatricula());
                System.out.println("Salário: " + func.getSalario());
                System.out.println("\nPressione Enter para continuar...");
                sc.nextLine();
            }

            case 0 -> System.out.println("Efetuando logout...");

            default -> {
                System.out.println("Opção inválida! Pressione Enter para continuar...");
                sc.nextLine();
            }

            }
            }
        }


    // simular a limpeza do console da IDE
    private static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}