package br.com.julius.controller;

import br.com.julius.model.Categoria;
import br.com.julius.model.FormaPagamento;
import br.com.julius.model.Gasto;
import br.com.julius.repository.GastoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class GastoController {

    private final GastoRepository repository;

    public GastoController(GastoRepository repository) {
        this.repository = repository;
    }

    // Disponível em todas as telas (para preencher os <select>)
    @ModelAttribute("categorias")
    public Categoria[] categorias() {
        return Categoria.values();
    }

    @ModelAttribute("formasPagamento")
    public FormaPagamento[] formasPagamento() {
        return FormaPagamento.values();
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/gastos";
    }

    // RF02 Listar + RF05 Pesquisar + RF06 Filtrar e totalizar
    @GetMapping("/gastos")
    public String listar(@RequestParam(required = false) String busca,
                         @RequestParam(required = false) Categoria categoria,
                         Model model) {
        String termo = (busca == null) ? "" : busca.trim();

        List<Gasto> gastos = (categoria == null)
                ? repository.findByDescricaoContainingIgnoreCaseOrderByDataDesc(termo)
                : repository.findByDescricaoContainingIgnoreCaseAndCategoriaOrderByDataDesc(termo, categoria);

        BigDecimal total = gastos.stream()
                .map(Gasto::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("gastos", gastos);
        model.addAttribute("total", total);
        model.addAttribute("busca", termo);
        model.addAttribute("categoriaSelecionada", categoria);
        return "gastos/lista";
    }

    // RF01 Cadastrar - abre o formulário vazio
    @GetMapping("/gastos/novo")
    public String novo(Model model) {
        model.addAttribute("gasto", new Gasto());
        return "gastos/form";
    }

    // RF01 Cadastrar - recebe o formulário
    @PostMapping("/gastos")
    public String cadastrar(@Valid @ModelAttribute("gasto") Gasto gasto,
                            BindingResult result,
                            RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "gastos/form"; // volta pra tela mostrando os erros (RNF04)
        }
        gasto.setId(null); // garante que é um registro novo
        repository.save(gasto);
        ra.addFlashAttribute("mensagem", "Gasto cadastrado.");
        return "redirect:/gastos";
    }

    // RF03 Editar - abre o formulário preenchido
    @GetMapping("/gastos/{id}/editar")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes ra) {
        return repository.findById(id)
                .map(gasto -> {
                    model.addAttribute("gasto", gasto);
                    return "gastos/form";
                })
                .orElseGet(() -> {
                    ra.addFlashAttribute("erro", "Gasto não encontrado."); // RN004
                    return "redirect:/gastos";
                });
    }

    // RF03 Editar - recebe as alterações
    @PostMapping("/gastos/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("gasto") Gasto gasto,
                            BindingResult result,
                            RedirectAttributes ra) {
        if (!repository.existsById(id)) { // RN004
            ra.addFlashAttribute("erro", "Gasto não encontrado.");
            return "redirect:/gastos";
        }
        if (result.hasErrors()) {
            return "gastos/form";
        }
        gasto.setId(id);
        repository.save(gasto); // com id preenchido, o JPA faz UPDATE
        ra.addFlashAttribute("mensagem", "Gasto atualizado.");
        return "redirect:/gastos";
    }

    // RF04 Excluir (a confirmação da RN004 é feita na tela, com confirm())
    @PostMapping("/gastos/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            ra.addFlashAttribute("mensagem", "Gasto excluído.");
        } else {
            ra.addFlashAttribute("erro", "Gasto não encontrado.");
        }
        return "redirect:/gastos";
    }
}
