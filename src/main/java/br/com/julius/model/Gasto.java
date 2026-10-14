package br.com.julius.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidade principal do sistema: vira a tabela "gasto" no MySQL.
 * As anotações de validação implementam RN001, RN002 e RNF04.
 */
@Entity
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // RN001 - descrição obrigatória
    @NotBlank(message = "Informe a descrição.")
    @Size(max = 100, message = "A descrição pode ter no máximo 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String descricao;

    // RN001 + RN003 - categoria obrigatória e dentre as disponíveis
    @NotNull(message = "Escolha uma categoria.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Categoria categoria;

    // RN001 + RN002 - valor obrigatório, numérico e maior que zero
    @NotNull(message = "Informe o valor.")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.")
    @Digits(integer = 10, fraction = 2, message = "Use no máximo 2 casas decimais.")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    // RN001 - data obrigatória
    @NotNull(message = "Informe a data.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false)
    private LocalDate data;

    // Forma de pagamento não está na RN001, então é opcional
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private FormaPagamento formaPagamento;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public FormaPagamento getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(FormaPagamento formaPagamento) { this.formaPagamento = formaPagamento; }
}
