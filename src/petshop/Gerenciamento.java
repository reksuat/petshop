package petshop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gerenciamento {
    private static final String ARQUIVO_CLIENTES = "clientes.txt";
    private static final String ARQUIVO_FUNCIONARIOS = "funcionarios.txt";
    private static final String ARQUIVO_PETS = "pets.txt";

    public static void iniciarFuncionarios() {
        File file = new File(ARQUIVO_FUNCIONARIOS);
        if (!file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_FUNCIONARIOS))) {
                bw.write("Oliver Oliveira;111.222.333-44;42 99999-1111;Oliver@pet.com;M01;Veterinario;5000.0");
                bw.newLine();
                bw.write("Nick Jam;555.666.777-88;42 99999-2222;Nick@pet.com;M02;Profissional banho e tosa;2500.0");
                bw.newLine();
            } catch (IOException e) {
                System.out.println("Erro ao inicializar funcionários: " + e.getMessage());
            }
        }
    }

    public static void salvarCliente(Cliente cliente) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_CLIENTES, true))) {
            bw.write(cliente.paraArquivo());
            bw.newLine();
            System.out.println("\n[Sucesso] Cliente cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar cliente no arquivo: " + e.getMessage());
        }
    }

    public static Cliente buscarCliente(String cpfBusca) {
        File file = new File(ARQUIVO_CLIENTES);
        if (!file.exists()) return null;

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CLIENTES))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[1].equals(cpfBusca)) {
                    return new Cliente(dados[0], dados[1], dados[2], dados[3], dados[4]);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler banco de dados de clientes: " + e.getMessage());
        }
        return null;
    }
    public static void atualizarCliente(Cliente clienteModificado) {
        File arquivo = new File(ARQUIVO_CLIENTES);
        List<String> todasAsLinhas = new ArrayList<>();

        if (!arquivo.exists()) {
            System.out.println("Erro: Arquivo de dados não encontrado.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[1].equals(clienteModificado.getCpf())) {
                    todasAsLinhas.add(clienteModificado.paraArquivo()); 
                } else {
                    todasAsLinhas.add(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar atualização: " + e.getMessage());
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, false))) {
            for (String l : todasAsLinhas) {
                bw.write(l);
                bw.newLine();
            }
            System.out.println("\n[Sucesso] Dados atualizados no arquivo clientes.txt!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar atualizações no arquivo: " + e.getMessage());
        }
    }
    public static boolean validarFuncionario(String matriculaBusca) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_FUNCIONARIOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[4].equalsIgnoreCase(matriculaBusca)) {
                    System.out.println("\nBem-vindo(a), " + dados[0] + " [" + dados[5] + "]");
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao validar credenciais: " + e.getMessage());
        }
        return false;
    }

    public static List<Cliente> buscarTodosClientes() {
        List<Cliente> lista = new ArrayList<>();
        File file = new File(ARQUIVO_CLIENTES);
        if (!file.exists()) return lista;
 
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CLIENTES))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                if (d.length >= 5) {
                    lista.add(new Cliente(d[0], d[1], d[2], d[3], d[4]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
        return lista;
    }
    
    public static boolean excluirCliente(String cpfExcluir) {
        File arquivo = new File(ARQUIVO_CLIENTES);
        if (!arquivo.exists()) return false;
 
        List<String> todasAsLinhas = new ArrayList<>();
        boolean encontrado = false;
 
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                if (d.length >= 2 && d[1].equals(cpfExcluir)) {
                    encontrado = true;          
                } else {
                    todasAsLinhas.add(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar exclusão: " + e.getMessage());
            return false;
        }
 
        if (!encontrado) return false;
 
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, false))) {
            for (String l : todasAsLinhas) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar após exclusão: " + e.getMessage());
            return false;
        }
        return true;
    }
    public static Funcionario buscarFuncionario(String matriculaBusca) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_FUNCIONARIOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                if (d.length >= 7 && d[4].equalsIgnoreCase(matriculaBusca)) {
                    return new Funcionario(d[0], d[1], d[2], d[3],
                                          d[4], d[5], Double.parseDouble(d[6]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao validar credenciais: " + e.getMessage());
        }
        return null;
    }
    public static void salvarPet(Pet pet) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_PETS, true))) {
            bw.write(pet.paraArquivo());
            bw.newLine();
            System.out.println("\n[Sucesso] Pet cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar pet no arquivo: " + e.getMessage());
        }
    }

    public static List<Pet> listarPets() {
        List<Pet> lista = new ArrayList<>();
        File file = new File(ARQUIVO_PETS);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_PETS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                if (d.length >= 6) {
                    int id = Integer.parseInt(d[0]);
                    String nome = d[1];
                    String especie = d[2];
                    String raca = d[3];
                    int idade = Integer.parseInt(d[4]);
                    String cpfDono = d[5];

                    Cliente dono = buscarCliente(cpfDono);
                    if (dono == null) {
                        dono = new Cliente("Desconhecido", cpfDono, "", "", "");
                    }

                    lista.add(new Pet(id, nome, especie, raca, idade, dono));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao listar pets: " + e.getMessage());
        }
        return lista;
    }
}
