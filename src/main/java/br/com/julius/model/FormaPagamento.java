package br.com.julius.model;

public enum FormaPagamento {
    DINHEIRO("Dinheiro"),
    PIX("Pix"),
    CARTAO_DEBITO("Cartão de débito"),
    CARTAO_CREDITO("Cartão de crédito"),
    BOLETO("Boleto");

    private final String label;

    FormaPagamento(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
