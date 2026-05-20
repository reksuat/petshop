package petshop;

public class Pet {
	private int id;
	private String nome;
	private String especie;
	private String raca;
	private int idade;
	private Cliente dono;

	public Pet(int id, String nome, String especie, String raca, int idade, Cliente dono) {
		this.id = id;
		this.nome = nome;
		this.especie = especie;
		this.raca = raca;
		this.idade = idade;
		this.dono = dono;
	}

	public void setHistorico() {
		System.out.println("Histórico do pet " + nome + " atualizado.");
	}
	
	public String paraArquivo() {
	    return id + ";" + nome + ";" + especie + ";" + raca + ";" + idade + ";" + dono.getCpf();
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getEspecie() { return especie; }
	public void setEspecie(String especie) { this.especie = especie; }

	public String getRaca() { return raca; }
	public void setRaca(String raca) { this.raca = raca; }

	public int getIdade() { return idade; }
	public void setIdade(int idade) { this.idade = idade; }

	public Cliente getDono() { return dono; }
	public void setDono(Cliente dono) { this.dono = dono; }
}