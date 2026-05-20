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
            } catch (NumberFormatException e) {
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

    // ─────────────────────────── MENU CLIENTE ────────────────────────────

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
            } catch (NumberFormatException e) {
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
                    if (clienteLogin != null) {
                        menuClienteEntrou(clienteLogin);
                    } else {
                        System.out.println("CPF não encontrado! Pressione Enter para continuar...");
                        sc.nextLine();
                    }
                }
                case 2 -> {
                    Cliente novoCliente = cadastrarCliente();
                    if (novoCliente != null) menuClienteEntrou(novoCliente);
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
        System.out.print("Nome: ");     String nomeNovo  = sc.nextLine();
        System.out.print("CPF: ");      String cpfNovo   = sc.nextLine();
        if (Gerenciamento.buscarCliente(cpfNovo) != null) {
            System.out.println("\n[Erro] CPF já cadastrado! Pressione Enter para voltar...");
            sc.nextLine();
            return null;
        }
        System.out.print("Telefone: "); String telNovo   = sc.nextLine();
        System.out.print("Email: ");    String emailNovo = sc.nextLine();
        System.out.print("Endereço: "); String endNovo   = sc.nextLine();

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
            System.out.println("5. Agendar Serviço");
            System.out.println("6. Histórico de Serviços");
            System.out.println("7. Cancelar Serviço");
            System.out.println("0. Fazer Logout");
            System.out.print("Escolha uma opção: ");

            try {
                opcao3 = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números. Pressione Enter para continuar...");
                sc.nextLine();
                continue;
            }

            switch (opcao3) {
                case 1 -> {
                    limparTela();
                    System.out.println("--- MEU PERFIL ---");
                    System.out.println("Nome: "     + clienteLogin.getNome());
                    System.out.println("CPF: "      + clienteLogin.getCpf());
                    System.out.println("Telefone: " + clienteLogin.getTelefone());
                    System.out.println("Email: "    + clienteLogin.getEmail());
                    System.out.println("Endereço: " + clienteLogin.getEndereco());
                    System.out.println("\nPressione Enter para voltar ao painel...");
                    sc.nextLine();
                }
                case 2 -> {
                    limparTela();
                    System.out.println("--- ATUALIZAR MEUS DADOS ---");
                    System.out.print("Digite o novo Telefone: ");
                    clienteLogin.setTelefone(sc.nextLine());
                    System.out.print("Digite o novo Email: ");
                    clienteLogin.setEmail(sc.nextLine());
                    System.out.print("Digite o novo Endereço: ");
                    clienteLogin.setEndereco(sc.nextLine());
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
                            System.out.println("\nID: " + p.getId() + " | Nome: " + p.getNome()
                                + " | Espécie: " + p.getEspecie()
                                + " | Raça: " + p.getRaca()
                                + " | Idade: " + p.getIdade() + " anos");
                            temPet = true;
                        }
                    }
                    if (!temPet) System.out.println("Você ainda não tem pets cadastrados.");
                    System.out.println("\nPressione Enter para continuar...");
                    sc.nextLine();
                }
                case 5 -> menuAgendarServico(clienteLogin);
                case 6 -> menuHistorico(clienteLogin);
                case 7 -> menuCancelarServico(clienteLogin);
                case 0 -> System.out.println("Efetuando logout da conta...");
                default -> {
                    System.out.println("Opção inválida! Pressione Enter para continuar...");
                    sc.nextLine();
                }
            }
        }
    }

    // ─────────────────────────── CANCELAR SERVIÇO ────────────────────────

    private static void menuCancelarServico(Cliente clienteLogin) {
        limparTela();
        System.out.println("--- CANCELAR SERVIÇO ---");

        List<Pet> todosPets = Gerenciamento.listarPets();
        List<Pet> meusPets = new java.util.ArrayList<>();
        for (Pet p : todosPets) {
            if (p.getDono().getCpf().equals(clienteLogin.getCpf())) meusPets.add(p);
        }

        if (meusPets.isEmpty()) {
            System.out.println("Você não tem pets cadastrados.");
            System.out.println("Pressione Enter para voltar...");
            sc.nextLine();
            return;
        }

        System.out.println("Seus pets:");
        for (Pet p : meusPets) {
            System.out.println("  ID: " + p.getId() + " | " + p.getNome());
        }
        System.out.print("ID do pet: ");

        Pet petEscolhido = null;
        try {
            int idPet = Integer.parseInt(sc.nextLine());
            for (Pet p : meusPets) {
                if (p.getId() == idPet) { petEscolhido = p; break; }
            }
        } catch (NumberFormatException e) { /* null */ }

        if (petEscolhido == null) {
            System.out.println("Pet não encontrado. Pressione Enter...");
            sc.nextLine();
            return;
        }

        Historicoservico historico = Gerenciamento.buscarHistorico(petEscolhido);
        List<Servico> agendados = historico.filtrarPorStatus("AGENDADO");

        if (agendados.isEmpty()) {
            System.out.println("\nNenhum serviço agendado para " + petEscolhido.getNome() + ".");
            System.out.println("Pressione Enter para voltar...");
            sc.nextLine();
            return;
        }

        System.out.println("\nServiços agendados de " + petEscolhido.getNome() + ":");
        for (Servico s : agendados) System.out.println("  " + s);

        System.out.print("\nID do serviço a cancelar: ");
        try {
            int idServico = Integer.parseInt(sc.nextLine());

            boolean pertence = agendados.stream().anyMatch(s -> s.getId() == idServico);
            if (!pertence) {
                System.out.println("[Erro] Serviço não encontrado ou não pertence ao seu pet.");
                System.out.println("Pressione Enter para voltar...");
                sc.nextLine();
                return;
            }

            System.out.print("Confirmar cancelamento? (s/n): ");
            String confirm = sc.nextLine();
            if (confirm.equalsIgnoreCase("s")) {
                Gerenciamento.atualizarStatusServico(idServico, "CANCELADO");
                System.out.println("[Sucesso] Serviço cancelado.");
            } else {
                System.out.println("Cancelamento abortado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }

        System.out.println("Pressione Enter para continuar...");
        sc.nextLine();
    }

    // ─────────────────────────── AGENDAR SERVIÇO ─────────────────────────

    private static void menuAgendarServico(Cliente clienteLogin) {
        limparTela();
        System.out.println("--- AGENDAR SERVIÇO ---");

        // lista apenas os pets do cliente logado
        List<Pet> todosPets = Gerenciamento.listarPets();
        List<Pet> meusPets = new java.util.ArrayList<>();
        for (Pet p : todosPets) {
            if (p.getDono().getCpf().equals(clienteLogin.getCpf())) meusPets.add(p);
        }

        if (meusPets.isEmpty()) {
            System.out.println("Você não tem pets cadastrados. Cadastre um pet primeiro.");
            System.out.println("Pressione Enter para voltar...");
            sc.nextLine();
            return;
        }

        System.out.println("Seus pets:");
        for (Pet p : meusPets) {
            System.out.println("  ID: " + p.getId() + " | " + p.getNome() + " (" + p.getEspecie() + ")");
        }
        System.out.print("ID do pet: ");

        Pet petEscolhido = null;
        try {
            int idPet = Integer.parseInt(sc.nextLine());
            for (Pet p : meusPets) {
                if (p.getId() == idPet) { petEscolhido = p; break; }
            }
        } catch (NumberFormatException e) { /* cai no null abaixo */ }

        if (petEscolhido == null) {
            System.out.println("Pet não encontrado. Pressione Enter para voltar...");
            sc.nextLine();
            return;
        }

        System.out.println("\nTipos de serviço disponíveis:");
        System.out.println("  1. Banho e Tosa   - R$ 80,00");
        System.out.println("  2. Consulta Vet.  - R$ 150,00");
        System.out.println("  3. Vacinação      - R$ 60,00");
        System.out.print("Escolha o tipo (1-3): ");

        String tipo; double valor; String matricula;
        try {
            int escolha = Integer.parseInt(sc.nextLine());
            switch (escolha) {
                case 1 -> { tipo = "Banho e Tosa";  valor = 80.0;  matricula = "M02"; }
                case 2 -> { tipo = "Consulta Vet."; valor = 150.0; matricula = "M01"; }
                case 3 -> { tipo = "Vacinação";     valor = 60.0;  matricula = "M01"; }
                default -> {
                    System.out.println("Opção inválida. Pressione Enter...");
                    sc.nextLine();
                    return;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Pressione Enter...");
            sc.nextLine();
            return;
        }

        System.out.print("Data do serviço (dd/mm/aaaa): ");
        String data = sc.nextLine();

        Funcionario func = Gerenciamento.buscarFuncionario(matricula);
        if (func == null) {
            System.out.println("Funcionário não encontrado. Pressione Enter...");
            sc.nextLine();
            return;
        }

        Gerenciamento.salvarServico(tipo, data, valor, func, petEscolhido);
        System.out.println("Funcionário responsável: " + func.getNome());
        System.out.println("Pressione Enter para continuar...");
        sc.nextLine();
    }

    // ─────────────────────────── HISTÓRICO ───────────────────────────────

    private static void menuHistorico(Cliente clienteLogin) {
        limparTela();
        System.out.println("--- HISTÓRICO DE SERVIÇOS ---");

        List<Pet> todosPets = Gerenciamento.listarPets();
        List<Pet> meusPets = new java.util.ArrayList<>();
        for (Pet p : todosPets) {
            if (p.getDono().getCpf().equals(clienteLogin.getCpf())) meusPets.add(p);
        }

        if (meusPets.isEmpty()) {
            System.out.println("Você não tem pets cadastrados.");
            System.out.println("Pressione Enter para voltar...");
            sc.nextLine();
            return;
        }

        System.out.println("Seus pets:");
        for (Pet p : meusPets) {
            System.out.println("  ID: " + p.getId() + " | " + p.getNome());
        }
        System.out.print("ID do pet para ver o histórico: ");

        Pet petEscolhido = null;
        try {
            int idPet = Integer.parseInt(sc.nextLine());
            for (Pet p : meusPets) {
                if (p.getId() == idPet) { petEscolhido = p; break; }
            }
        } catch (NumberFormatException e) { /* null */ }

        if (petEscolhido == null) {
            System.out.println("Pet não encontrado. Pressione Enter...");
            sc.nextLine();
            return;
        }

        Historicoservico historico = Gerenciamento.buscarHistorico(petEscolhido);
        List<Servico> servicos = historico.listar();

        limparTela();
        System.out.println("=== Histórico de " + petEscolhido.getNome() + " ===");

        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço registrado para este pet.");
        } else {
            System.out.println("1. Ver todos  2. Filtrar por tipo  3. Filtrar por status");
            System.out.print("Escolha: ");
            List<Servico> resultado;
            try {
                int filtro = Integer.parseInt(sc.nextLine());
                switch (filtro) {
                    case 2 -> {
                        System.out.print("Tipo (Banho e Tosa / Consulta Vet. / Vacinação): ");
                        resultado = historico.filtrarPorTipo(sc.nextLine());
                    }
                    case 3 -> {
                        System.out.print("Status (AGENDADO / CONCLUIDO / CANCELADO): ");
                        resultado = historico.filtrarPorStatus(sc.nextLine());
                    }
                    default -> resultado = servicos;
                }
            } catch (NumberFormatException e) {
                resultado = servicos;
            }

            if (resultado.isEmpty()) {
                System.out.println("Nenhum serviço encontrado com esse filtro.");
            } else {
                for (Servico s : resultado) System.out.println("  " + s);
            }
        }

        System.out.println("\nPressione Enter para voltar...");
        sc.nextLine();
    }

    // ─────────────────────────── MENU FUNCIONÁRIO ────────────────────────

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
            System.out.println("── SERVIÇOS ──");
            System.out.println("6. Atualizar status de serviço");
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
                    System.out.print("Confirmar exclusão? (s/n): ");
                    String confirm = sc.nextLine();
                    if (confirm.equalsIgnoreCase("s")) {
                        boolean ok = Gerenciamento.excluirCliente(cpf);
                        System.out.println(ok ? "\n[Sucesso] Cliente excluído." : "\n[Erro] Não foi possível excluir.");
                    } else if (confirm.equalsIgnoreCase("n")) {
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
                            System.out.println("\nID: " + p.getId() + " | Nome: " + p.getNome()
                                + " | Espécie: " + p.getEspecie()
                                + " | Dono: " + p.getDono().getNome()
                                + " (CPF: " + p.getDono().getCpf() + ")");
                        }
                    }
                    System.out.println("\nPressione Enter para continuar...");
                    sc.nextLine();
                }
                case 5 -> {
                    limparTela();
                    System.out.println("--- MEU PERFIL ---");
                    System.out.println("Nome: "      + func.getNome());
                    System.out.println("CPF: "       + func.getCpf());
                    System.out.println("Telefone: "  + func.getTelefone());
                    System.out.println("Email: "     + func.getEmail());
                    System.out.println("Cargo: "     + func.getCargo());
                    System.out.println("Matrícula: " + func.getMatricula());
                    System.out.println("Salário: "   + func.getSalario());
                    System.out.println("\nPressione Enter para continuar...");
                    sc.nextLine();
                }
                case 6 -> {
                    limparTela();
                    System.out.println("--- ATUALIZAR STATUS DE SERVIÇO ---");
                    System.out.print("ID do serviço: ");
                    try {
                        int idServ = Integer.parseInt(sc.nextLine());
                        System.out.println("Novo status: 1. CONCLUIDO   2. CANCELADO");
                        System.out.print("Escolha: ");
                        int s = Integer.parseInt(sc.nextLine());
                        String novoStatus = (s == 1) ? "CONCLUIDO" : "CANCELADO";
                        Gerenciamento.atualizarStatusServico(idServ, novoStatus);
                        System.out.println("[Sucesso] Status atualizado para " + novoStatus + ".");
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida.");
                    }
                    System.out.println("Pressione Enter para continuar...");
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

    private static void limparTela() {
        for (int i = 0; i < 50; i++) System.out.println();
    }
}
