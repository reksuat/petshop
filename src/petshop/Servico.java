package petshop;

public class Servico {
    private int id;
    private String tipo;
    private String data;
    private double valor;
    private Funcionario funcionario;
    private Pet pet;
    private String status;

    public Servico(int id, String tipo, String data, double valor, Funcionario funcionario, Pet pet) {
        this.id = id;
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
        this.funcionario = funcionario;
        this.pet = pet;
        this.status = "AGENDADO";
    }

    public String paraArquivo() {
        return id + ";" + tipo + ";" + data + ";" + valor + ";"
                + funcionario.getMatricula() + ";" + pet.getId() + ";" + status;
    }

    public void cancelar() { this.status = "CANCELADO"; }
    public void concluir() { this.status = "CONCLUIDO"; }

    public int getId()                  { return id; }
    public String getTipo()             { return tipo; }
    public String getData()             { return data; }
    public double getValor()            { return valor; }
    public Funcionario getFuncionario() { return funcionario; }
    public Pet getPet()                 { return pet; }
    public String getStatus()           { return status; }
    public void setStatus(String status){ this.status = status; }

    @Override
    public String toString() {
        return "[ID:" + id + "] " + tipo
                + " | Data: " + data
                + " | Valor: R$" + String.format("%.2f", valor)
                + " | Funcionário: " + funcionario.getNome()
                + " | Status: " + status;
    }
}
