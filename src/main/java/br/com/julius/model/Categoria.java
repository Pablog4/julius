package br.com.julius.model;

/** RN003 - categorias disponíveis no sistema. */
public enum Categoria {
    ALIMENTACAO("Alimentação"),
    TRANSPORTE("Transporte"),
    MORADIA("Moradia"),
    SAUDE("Saúde"),
    EDUCACAO("Educação"),
    LAZER("Lazer"),
    OUTROS("Outros");

    private final String label;

    Categoria(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
