package petshop;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
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
	

}
