package petshop;

public class Cliente extends Pessoa {
	private String endereco;

	public Cliente(String nome, String cpf, String telefone, String email, String endereco) {
		super(nome, cpf, telefone, email);
		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String paraArquivo() {
        return nome + ";" + cpf + ";" + telefone + ";" + email + ";" + endereco;
    }

}
