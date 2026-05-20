package petshop;

public class Pagamento {
    private Servico servico;
    private double total;
    private String formaPag;
    private boolean confirmado;

    public Pagamento(Servico servico, String formaPag) {
        this.servico = servico;
        this.formaPag = formaPag;
        if (servico != null) {
            this.total = servico.getValor();
        } else {
            this.total = 0.0;
        }
        this.confirmado = false;
    }

    public void processar() {
        System.out.println("\n[Processamento] Processando pagamento de R$ " + String.format("%.2f", total) + " via " + formaPag + "...");
    }

    public void confirmar() {
        this.confirmado = true;
        System.out.println("[Sucesso] Pagamento de R$ " + String.format("%.2f", total) + " CONFIRMADO!");
    }

    public void cancelar() {
        this.confirmado = false;
        System.out.println("[Aviso] Pagamento CANCELADO.");
    }

    public String recibo() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=======================================\n");
        sb.append("         RECIBO DE PAGAMENTO           \n");
        sb.append("=======================================\n");
        if (servico != null) {
            sb.append("Serviço:      ").append(servico.getTipo()).append("\n");
            if (servico.getPet() != null) {
                sb.append("Animal/Pet:   ").append(servico.getPet().getNome()).append("\n");
            }
            if (servico.getFuncionario() != null) {
                sb.append("Atendido por: ").append(servico.getFuncionario().getNome()).append("\n");
            }
        }
        sb.append("Forma de Pag: ").append(formaPag).append("\n");
        sb.append("Valor Pago:   R$ ").append(String.format("%.2f", total)).append("\n");
        sb.append("Status:       ").append(confirmado ? "PAGO / CONCLUÍDO" : "PENDENTE").append("\n");
        sb.append("=======================================");
        return sb.toString();
    }

    public String paraArquivo() {
        int idServico = (servico != null) ? servico.getId() : -1;
        return idServico + ";" + total + ";" + formaPag + ";" + confirmado;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(String formaPag) {
        this.formaPag = formaPag;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    @Override
    public String toString() {
        int idServico = (servico != null) ? servico.getId() : -1;
        return "Pagamento [Serviço ID: " + idServico + " | Total: R$ " + String.format("%.2f", total) + " | Forma: " + formaPag + " | Confirmado: " + confirmado + "]";
    }
}