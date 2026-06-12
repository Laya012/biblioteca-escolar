# 📚 Sistema de Biblioteca Escolar

## Proposta do Sistema
Sistema para gerenciamento de biblioteca escolar, controlando cadastro de alunos, livros, empréstimos, devoluções e penalidades educacionais.

## Regras de Penalidade
- **1º e 2º atraso:** Apenas aviso (notificação ao aluno)
- **3º atraso ou mais:** -0,5 pontos na nota processual por atraso
- A nota nunca fica negativa

## Estrutura do Projeto
Sim, está certo! ✅
O que está correto:
Item	Status
Título	✅ "Sistema de Biblioteca Escolar"
Proposta	✅ Explica o que o sistema faz
Regras de Penalidade	✅ 1º/2º aviso, 3º perde 0,5
Estrutura do Projeto	✅ Vai mostrar a árvore de pastas
Só falta uma coisa: O desenho da estrutura

Você precisa colar o desenho da estrutura depois de "Estrutura do Projeto".
Adicione isto depois de "## Estrutura do Projeto":
markdown

biblioteca-escolar/
│
├── README.md
├── MenuPrincipal.java
│
└── src/
└── model/
├── Aluno.java
├── Aviso.java
├── Categoria.java
├── Emprestimo.java
├── EmprestimoAtivo.java
├── EmprestimoAtrasado.java
├── EmprestimoFinalizado.java
├── Livro.java
├── Pessoa.java
├── Professor.java
└── Reserva.java
text

Como deve ficar:
markdown

## Estrutura do Projeto

biblioteca-escolar/
│
├── README.md
├── MenuPrincipal.java
│
└── src/
└── model/
├── Aluno.java
├── Aviso.java
...
text

Se quiser ver como está agora:
bash

cd ~/biblioteca-escolar
cat README.md

Se o desenho da estrutura não aparecer, edite o arquivo e cole o código acima.

Resumo: O que você escreveu está correto, só falta colar o desenho da árvore de arquivos! 🚀
me ajuda a editar

Vamos editar o README.md direto pelo terminal.
PASSO 1: Abrir o README.md
bash

cd ~/biblioteca-escolar
nano README.md

PASSO 2: Substituir TODO o conteúdo por este código completo

Copie e cole (Ctrl+Shift+V) dentro do nano:
markdown

# 📚 Sistema de Biblioteca Escolar

## Proposta do Sistema
Sistema para gerenciamento de biblioteca escolar, controlando cadastro de alunos, livros, empréstimos, devoluções e penalidades educacionais.

## Regras de Penalidade
- **1º e 2º atraso:** Apenas aviso (notificação ao aluno)
- **3º atraso ou mais:** -0,5 pontos na nota processual por atraso
- A nota nunca fica negativa

## Estrutura do Projeto

biblioteca-escolar/
│
├── README.md
├── MenuPrincipal.java
│
└── src/
└── model/
├── Aluno.java
├── Aviso.java
├── Categoria.java
├── Emprestimo.java
├── EmprestimoAtivo.java
├── EmprestimoAtrasado.java
├── EmprestimoFinalizado.java
├── Livro.java
├── Pessoa.java
├── Professor.java
└── Reserva.java


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
| **Herança** | `Aluno` e `Professor` herdam de `Pessoa` |
| **Encapsulamento** | Atributos `private`, getters e setters |
| **Polimorfismo** | Método `exibirInfo()` muda por classe |
| **Classe Abstrata** | `Pessoa` e `Emprestimo` são abstratas |
| **Enum** | `Categoria` (FICCAO, DIDATICO, etc.) |
| **Tratamento de Exceções** | `try-catch` e métodos de validação |
| **ArrayList** | Armazenamento em memória (sem banco) |

## Como Executar

```bash
cd ~/biblioteca-escolar
javac src/model/*.java MenuPrincipal.java
java MenuPrincipal
