package petshop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Historicoservico {
    private Pet pet;
    private List<Servico> servicos;

    public Historicoservico(Pet pet) {
        this.pet = pet;
        this.servicos = new ArrayList<>();
    }

    public void adicionarServico(Servico servico) {
        servicos.add(servico);
    }

    /*Lista serviços*/
    public List<Servico> listar() {
        return new ArrayList<>(servicos);
    }

    /*(Banho e Tosa, Consulta Vet, vacinação)*/
    public List<Servico> filtrarPorTipo(String tipo) {
        return servicos.stream()
                .filter(s -> s.getTipo().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());
    }

    /*(agendado, concluido, cancelado)*/
    public List<Servico> filtrarPorStatus(String status) {
        return servicos.stream()
                .filter(s -> s.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public Pet getPet()                { return pet; }
    public List<Servico> getServicos() { return servicos; }
    public void setServicos(List<Servico> servicos) { this.servicos = servicos; }
}
