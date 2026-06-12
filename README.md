# 📚 Sistema de Biblioteca Escolar

## Proposta do Sistema
Sistema para gerenciamento de biblioteca escolar, controlando cadastro de alunos, livros, empréstimos, devoluções e penalidades educacionais.

## Regras de Penalidade
- **1º e 2º atraso:** Apenas aviso (notificação ao aluno)
- **3º atraso ou mais:** -0,5 pontos na nota processual por atraso
- A nota nunca fica negativa

## Requisitos Funcionais (8)

| RF | Descrição |
|----|-----------|
| RF01 | Cadastrar aluno |
| RF02 | Cadastrar livro |
| RF03 | Listar alunos |
| RF04 | Listar livros |
| RF05 | Registrar empréstimo |
| RF06 | Registrar devolução |
| RF07 | Consultar avisos do aluno |
| RF08 | Relatório de alunos com penalidade |

## Conceitos de POO Aplicados

| Conceito | Onde foi aplicado |
|----------|-------------------|
| **Herança** | `Aluno` e `Professor` herdam de `Pessoa`; `EmprestimoAtivo`, `EmprestimoFinalizado` e `EmprestimoAtrasado` herdam de `Emprestimo` |
| **Encapsulamento** | Todos os atributos são `private` com getters e setters |
| **Polimorfismo** | Método `exibirInfo()` se comporta diferente em cada classe |
| **Classe Abstrata** | `Pessoa` e `Emprestimo` são classes abstratas |
| **Enum** | `Categoria` define categorias fixas de livros |
| **Tratamento de Exceções** | `try-catch` e métodos `validarTexto()` / `validarInteiro()` |
| **ArrayList** | Armazenamento em memória (sem banco de dados) |

## Estrutura do Projeto
