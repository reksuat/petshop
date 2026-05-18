package petshop;
import java.util.Scanner;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		Gerenciamento.iniciarFuncionarios();
		int opcao1=-1;
		while (opcao1 != 0) {
			System.out.println("\n=================================");
            System.out.println("       SISTEMA PETSHOP           ");
            System.out.println("=================================");
            System.out.println("1. Entrar como Cliente");
            System.out.println("2. Entrar como Funcionário");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma opção: ");
            
            try {
            	opcao1 = Integer.parseInt(sc.nextLine());
            } catch(NumberFormatException e) {
            	System.out.println("Erro: Digite apenas números");
            	continue;
            }
            switch (opcao1) {
            case 1 -> menuCliente();
            case 2 -> menuFuncionario();
            case 0 -> System.out.println("Saindo..");
            default -> System.out.println("Opção inválida!");
            }
		}
	}
	private static void menuCliente() {
		System.out.println("\n--- ACESSO DO CLIENTE ---");
        System.out.println("1. Já sou cadastrado (Fazer Login)");
        System.out.println("2. Cadastrar");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao2 = -1;
        try {
        	opcao2 = Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
        	System.out.println("Erro: Digite apenas números");
        	return;
        }
        switch (opcao2) {
        case 1-> {
        	System.out.print("Digite seu CPF para entrar: ");
        	String cpf = sc.nextLine();
        	
        	Cliente clienteLogin = Gerenciamento.buscarCliente(cpf);
        	if(clienteLogin != null) {
        		menuClienteEntrou(clienteLogin);
        	}else {
        		System.out.println("CPF não encontrado!");
        	}
        }
        case 2 -> {
        	System.out.println("\n--- CADASTRO DE NOVO CLIENTE ---");
            System.out.print("Nome: "); String nomeNovo = sc.nextLine();
            System.out.print("CPF: "); String cpfNovo = sc.nextLine();
            System.out.print("Telefone: "); String telNovo = sc.nextLine();
            System.out.print("Email: "); String emailNovo = sc.nextLine();
            System.out.print("Endereço: "); String endNovo = sc.nextLine();

            Cliente novo = new Cliente(nomeNovo, cpfNovo, telNovo, emailNovo, endNovo);
            Gerenciamento.salvarCliente(novo);
        }
        case 3-> System.out.println("Voltando..");
        default-> System.out.println("Opção inválida!");
        }
	}
	
	private static void menuClienteEntrou(Cliente clienteLogin) {
        int opcao3 = -1;
        while (opcao3 != 0) {
            System.out.println("\n--- BEM-VINDO, " + clienteLogin.getNome().toUpperCase() + " ---");
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
                System.out.println("Erro: Digite apenas números");
                continue;
            }
            switch(opcao3) {
            case 1 -> {
            	System.out.println("\n--- MEU PERFIL ---");
                System.out.println("Nome: " + clienteLogin.getNome());
                System.out.println("CPF: " + clienteLogin.getCpf());
                System.out.println("Telefone: " + clienteLogin.getTelefone());
                System.out.println("Email: " + clienteLogin.getEmail());
                System.out.println("Endereço: " + clienteLogin.getEndereco());
            	}
            case 2 -> {
            	System.out.println("\n--- ATUALIZAR MEUS DADOS ---");
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
            }
            case 0 -> System.out.println("Saindo..");
            default -> System.out.println("Opção inválida!");
            	}
            }
        }
	private static void menuFuncionario() {
        System.out.print("\nDigite sua matrícula de funcionário: ");
        String matricula = sc.nextLine();

        if (Gerenciamento.validarFuncionario(matricula)) {
            System.out.println("Painel do Funcionário Liberado.");
           
        } else {
            System.out.println("Matrícula não cadastrada!");
        }
    }
}
