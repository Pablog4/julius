# JULIUS — Sistema Web de Controle de Gastos Pessoais

Java 17 + Spring Boot 3 + Thymeleaf + Bootstrap + Spring Data JPA + MySQL
(exatamente a arquitetura do item 7.2 da documentação).

## Como rodar

1. Instale: **JDK 17+**, **MySQL 8** e uma IDE (IntelliJ Community é a mais simples — já vem com Maven).
2. Abra `src/main/resources/application.properties` e coloque o usuário e a senha do seu MySQL.
   O banco `julius` e a tabela `gasto` são criados sozinhos.
3. Na IDE: abra a pasta do projeto, espere o Maven baixar as dependências e rode `JuliusApplication`.
   Pelo terminal: `mvn spring-boot:run`
4. Acesse **http://localhost:8080**

## Onde cada requisito está no código

| Requisito | Onde |
|---|---|
| RF01 Cadastrar | `GastoController.novo()` + `cadastrar()` · `form.html` |
| RF02 Listar | `GastoController.listar()` · `lista.html` |
| RF03 Editar | `GastoController.editar()` + `atualizar()` · `form.html` |
| RF04 Excluir | `GastoController.excluir()` · botão Excluir em `lista.html` |
| RF05 Pesquisar | `GastoRepository.findByDescricaoContaining...` |
| RF06 Filtrar e totalizar | `GastoRepository...AndCategoria...` + soma no `listar()` |
| RNF03 Banco relacional | `application.properties` (MySQL) |
| RNF04 Validação | anotações `@NotBlank`, `@NotNull`, `@DecimalMin` em `Gasto.java` + `@Valid` no controller |
| RNF05 Padrão brasileiro | `spring.web.locale=pt_BR` + `#numbers.formatCurrency` e `dd/MM/yyyy` |
| RN001 Campos obrigatórios | `Gasto.java` |
| RN002 Valor > 0 | `@DecimalMin("0.01")` em `Gasto.java` |
| RN003 Categorias | `Categoria.java` (enum) |
| RN004 Alteração/exclusão | `existsById` no controller + `confirm()` no botão Excluir |

## Fluxo de uma requisição (o que explicar na apresentação)

```
Navegador  →  Controller  →  Repository  →  MySQL
   ↑              │
   └── Template (Thymeleaf) ←┘
```

1. O usuário clica em algo → o navegador faz um GET ou POST para uma URL.
2. O **Controller** recebe (ex.: `@PostMapping("/gastos")`).
3. O **Repository** conversa com o banco (o Spring gera o SQL pelo nome do método).
4. O Controller coloca os dados no `Model` e devolve o nome do **template**.
5. O Thymeleaf monta o HTML e o navegador mostra.
