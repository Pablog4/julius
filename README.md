# JULIUS - Sistema Web de Controle de Gastos Pessoais

Aplicação web para registrar e acompanhar gastos pessoais. O nome é uma homenagem ao Julius, pai do Chris na série *Todo Mundo Odeia o Chris*, famoso por controlar cada centavo da família.

## Funcionalidades

- Cadastro de gastos com descrição, categoria, valor, data e forma de pagamento
- Listagem dos gastos cadastrados, do mais recente para o mais antigo
- Edição e exclusão de gastos (com confirmação antes de excluir)
- Pesquisa pela descrição
- Filtro por categoria
- Total dos gastos exibidos, atualizado conforme a pesquisa e o filtro
- Validação dos campos obrigatórios e do valor (deve ser maior que zero)
- Valores e datas no padrão brasileiro

## Tecnologias

- Java 17
- Spring Boot 3.5 (Spring Web, Spring Data JPA, Bean Validation)
- Thymeleaf
- Bootstrap 5
- MySQL / MariaDB

## Como executar

**Pré-requisitos:** JDK 17 ou superior e um servidor MySQL ou MariaDB (por exemplo, o do XAMPP) rodando na porta 3306.

1. Clone o repositório:
   ```
   git clone <link-do-repositorio>
   ```
2. Em `src/main/resources/application.properties`, ajuste o usuário e a senha do banco:
   ```
   spring.datasource.username=root
   spring.datasource.password=
   ```
   O banco `julius` e a tabela `gasto` são criados automaticamente na primeira execução.
3. Execute a classe `JuliusApplication` pela IDE, ou pelo terminal com `mvn spring-boot:run`.
4. Acesse http://localhost:8080

## Estrutura do projeto

```
src/main/java/br/com/julius
├── model/          # Entidade Gasto e enums Categoria e FormaPagamento
├── repository/     # Acesso ao banco com Spring Data JPA
└── controller/     # Rotas e regras da aplicação

src/main/resources
├── templates/gastos/   # Telas: lista.html e form.html
├── static/css/         # Estilos
└── application.properties
```

## Requisitos atendidos

| Requisito | Descrição |
|---|---|
| RF01 | Cadastrar gasto |
| RF02 | Listar gastos |
| RF03 | Editar gasto |
| RF04 | Excluir gasto |
| RF05 | Pesquisar gasto pela descrição |
| RF06 | Filtrar por categoria e totalizar |
| RN001 | Descrição, categoria, valor e data obrigatórios |
| RN002 | Valor numérico e maior que zero |
| RN003 | Gasto associado a uma categoria do sistema |
| RN004 | Alteração e exclusão apenas de gastos existentes, com confirmação na exclusão |

## Autor

Pablo Gabriel- [github.com/Pablog4](https://github.com/Pablog4)