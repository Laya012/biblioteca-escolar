import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    private static List<Aluno> alunos = new ArrayList<>();
    private static List<Livro> livros = new ArrayList<>();
    private static List<Emprestimo> emprestimos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║        📚 SISTEMA DE BIBLIOTECA ESCOLAR - POO           ║");
        System.out.println("║     (Avisos e Penalidade na Nota Processual)            ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        carregarDadosExemplo();

        int opcao = 0;
        do {
            System.out.println("\n┌─────────────────────────────────────────────────────┐");
            System.out.println("│                    MENU PRINCIPAL                     │");
            System.out.println("├─────────────────────────────────────────────────────┤");
            System.out.println("│  1 - Cadastrar Aluno                                 │");
            System.out.println("│  2 - Cadastrar Livro                                 │");
            System.out.println("│  3 - Listar Alunos                                   │");
            System.out.println("│  4 - Listar Livros                                   │");
            System.out.println("│  5 - Registrar Empréstimo                            │");
            System.out.println("│  6 - Registrar Devolução                             │");
            System.out.println("│  7 - Consultar Avisos de um Aluno                    │");
            System.out.println("│  8 - Relatório de Alunos com Penalidade              │");
            System.out.println("│  9 - Sair                                            │");
            System.out.println("└─────────────────────────────────────────────────────┘");
            System.out.print("👉 Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: cadastrarAluno(); break;
                    case 2: cadastrarLivro(); break;
                    case 3: listarAlunos(); break;
                    case 4: listarLivros(); break;
                    case 5: registrarEmprestimo(); break;
                    case 6: registrarDevolucao(); break;
                    case 7: consultarAvisos(); break;
                    case 8: relatorioPenalidade(); break;
                    case 9: System.out.println("\n✅ Saindo...\n"); break;
                    default: System.out.println("❌ Opção inválida! Digite 1 a 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ ERRO: Digite apenas números!");
                opcao = 0;
            } catch (Exception e) {
                System.out.println("❌ ERRO: " + e.getMessage());
                opcao = 0;
            }
        } while (opcao != 9);
    }

    private static String validarTexto(String texto, String campo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " não pode ficar vazio!");
        }
        if (texto.trim().length() < 3) {
            throw new IllegalArgumentException(campo + " deve ter pelo menos 3 caracteres!");
        }
        return texto.trim();
    }

    private static int validarInteiro(String valor, String campo) {
        try {
            int num = Integer.parseInt(valor.trim());
            if (num <= 0) {
                throw new IllegalArgumentException(campo + " deve ser maior que zero!");
            }
            if (campo.equals("Série") && (num < 1 || num > 9)) {
                throw new IllegalArgumentException("Série deve ser entre 1 e 9!");
            }
            if (campo.equals("Ano") && (num < 1900 || num > LocalDate.now().getYear())) {
                throw new IllegalArgumentException("Ano deve ser entre 1900 e " + LocalDate.now().getYear());
            }
            if (campo.equals("Quantidade") && num > 100) {
                throw new IllegalArgumentException("Quantidade não pode ser maior que 100!");
            }
            if (campo.equals("Páginas") && (num < 1 || num > 5000)) {
                throw new IllegalArgumentException("Páginas deve ser entre 1 e 5000!");
            }
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(campo + " deve ser um número válido!");
        }
    }

    private static void cadastrarAluno() {
        System.out.println("\n--- CADASTRO DE ALUNO ---");
        try {
            System.out.print("Nome: ");
            String nome = validarTexto(scanner.nextLine(), "Nome");
            
            System.out.print("Email: ");
            String email = validarTexto(scanner.nextLine(), "Email");
            
            System.out.print("Telefone: ");
            String telefone = validarTexto(scanner.nextLine(), "Telefone");
            
            System.out.print("CPF (11 dígitos): ");
            String cpf = scanner.nextLine().trim();
            if (cpf.length() != 11 || !cpf.matches("\\d+")) {
                throw new IllegalArgumentException("CPF deve ter 11 dígitos numéricos!");
            }
            
            System.out.print("Matrícula: ");
            String matricula = validarTexto(scanner.nextLine(), "Matrícula");
            
            System.out.print("Turma (ex: 3º A): ");
            String turma = validarTexto(scanner.nextLine(), "Turma");
            
            System.out.print("Série (1 a 9): ");
            int serie = validarInteiro(scanner.nextLine(), "Série");

            Aluno aluno = new Aluno(nome, email, telefone, cpf, matricula, turma, serie);
            alunos.add(aluno);
            System.out.println("✅ Aluno cadastrado com sucesso!");
            
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar: " + e.getMessage());
        }
    }

    private static void cadastrarLivro() {
        System.out.println("\n--- CADASTRO DE LIVRO ---");
        try {
            System.out.print("Título: ");
            String titulo = validarTexto(scanner.nextLine(), "Título");
            
            System.out.print("Autor: ");
            String autor = validarTexto(scanner.nextLine(), "Autor");
            
            System.out.print("ISBN: ");
            String isbn = validarTexto(scanner.nextLine(), "ISBN");
            
            System.out.print("Editora: ");
            String editora = validarTexto(scanner.nextLine(), "Editora");
            
            System.out.print("Ano: ");
            int ano = validarInteiro(scanner.nextLine(), "Ano");
            
            System.out.print("Quantidade: ");
            int quantidade = validarInteiro(scanner.nextLine(), "Quantidade");
            
            System.out.print("Páginas: ");
            int paginas = validarInteiro(scanner.nextLine(), "Páginas");
            
            System.out.print("Localização (ex: Estante A1): ");
            String localizacao = validarTexto(scanner.nextLine(), "Localização");
            
            System.out.print("Categoria (FICCAO, DIDATICO, REVISTA, TCC, PERIODICO): ");
            String catStr = scanner.nextLine().toUpperCase();
            
            Categoria categoria;
            try {
                categoria = Categoria.valueOf(catStr);
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ Categoria inválida! Usando FICCAO como padrão.");
                categoria = Categoria.FICCAO;
            }

            Livro livro = new Livro(titulo, autor, isbn, editora, ano, quantidade, categoria, paginas, localizacao);
            livros.add(livro);
            System.out.println("✅ Livro cadastrado com sucesso!");
            
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar: " + e.getMessage());
        }
    }

    private static void listarAlunos() {
        System.out.println("\n--- LISTA DE ALUNOS ---");
        if (alunos.isEmpty()) {
            System.out.println("   Nenhum aluno cadastrado.");
        } else {
            for (Aluno a : alunos) {
                System.out.println("   • " + a.exibirInfo());
            }
            System.out.println("\n   📊 Total: " + alunos.size() + " aluno(s)");
        }
    }

    private static void listarLivros() {
        System.out.println("\n--- LISTA DE LIVROS ---");
        if (livros.isEmpty()) {
            System.out.println("   Nenhum livro cadastrado.");
        } else {
            for (Livro l : livros) {
                System.out.println("   • " + l.exibirInfo());
            }
            System.out.println("\n   📊 Total: " + livros.size() + " livro(s)");
        }
    }

    private static void registrarEmprestimo() {
        if (alunos.isEmpty()) {
            System.out.println("❌ Cadastre alunos primeiro!");
            return;
        }
        if (livros.isEmpty()) {
            System.out.println("❌ Cadastre livros primeiro!");
            return;
        }

        System.out.println("\n--- REGISTRAR EMPRÉSTIMO ---");
        
        System.out.println("\n📋 ALUNOS DISPONÍVEIS:");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println("   " + (i + 1) + " - " + alunos.get(i).getNome());
        }
        System.out.print("Escolha o número do aluno: ");
        int alunoIdx;
        try {
            alunoIdx = Integer.parseInt(scanner.nextLine()) - 1;
            if (alunoIdx < 0 || alunoIdx >= alunos.size()) {
                System.out.println("❌ Aluno inválido!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Digite um número válido!");
            return;
        }
        
        Aluno aluno = alunos.get(alunoIdx);
        
        if (!aluno.podePegarLivro()) {
            System.out.println("❌ Aluno já tem 3 livros emprestados!");
            return;
        }

        List<Livro> livrosDisponiveis = livros.stream().filter(Livro::isDisponivel).toList();
        if (livrosDisponiveis.isEmpty()) {
            System.out.println("❌ Nenhum livro disponível!");
            return;
        }
        
        System.out.println("\n📚 LIVROS DISPONÍVEIS:");
        for (int i = 0; i < livrosDisponiveis.size(); i++) {
            System.out.println("   " + (i + 1) + " - " + livrosDisponiveis.get(i).getTitulo());
        }
        System.out.print("Escolha o número do livro: ");
        int livroIdx;
        try {
            livroIdx = Integer.parseInt(scanner.nextLine()) - 1;
            if (livroIdx < 0 || livroIdx >= livrosDisponiveis.size()) {
                System.out.println("❌ Livro inválido!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Digite um número válido!");
            return;
        }
        
        Livro livro = livrosDisponiveis.get(livroIdx);

        livro.emprestar();
        LocalDate dataRetirada = LocalDate.now();
        LocalDate dataPrevista = dataRetirada.plusDays(7);
        EmprestimoAtivo emprestimo = new EmprestimoAtivo(aluno, livro, dataRetirada, dataPrevista);
        emprestimos.add(emprestimo);
        aluno.adicionarEmprestimo(emprestimo);

        System.out.println("\n✅ Empréstimo realizado com sucesso!");
        System.out.println("   📖 Livro: " + livro.getTitulo());
        System.out.println("   👤 Aluno: " + aluno.getNome());
        System.out.println("   📅 Data prevista para devolução: " + dataPrevista);
    }

    private static void registrarDevolucao() {
        List<Emprestimo> ativos = emprestimos.stream()
                .filter(e -> e instanceof EmprestimoAtivo)
                .toList();

        if (ativos.isEmpty()) {
            System.out.println("❌ Não há empréstimos ativos!");
            return;
        }

        System.out.println("\n--- REGISTRAR DEVOLUÇÃO ---");
        for (int i = 0; i < ativos.size(); i++) {
            Emprestimo e = ativos.get(i);
            System.out.println((i + 1) + " - " + e.getLivro().getTitulo() + " (Aluno: " + e.getAluno().getNome() + ")");
        }
        
        System.out.print("Escolha o empréstimo (número): ");
        int idx;
        try {
            idx = Integer.parseInt(scanner.nextLine()) - 1;
            if (idx < 0 || idx >= ativos.size()) {
                System.out.println("❌ Opção inválida!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Digite um número válido!");
            return;
        }

        Emprestimo emp = ativos.get(idx);
        Aluno aluno = emp.getAluno();
        Livro livro = emp.getLivro();
        LocalDate dataDevolucao = LocalDate.now();

        if (dataDevolucao.isAfter(emp.getDataPrevistaDevolucao())) {
            int novoAviso = aluno.getQuantidadeAvisos() + 1;
            Aviso aviso = new Aviso("Atraso na devolução: " + livro.getTitulo(), novoAviso);
            aluno.adicionarAviso(aviso);
            System.out.println("\n⚠️ Aviso #" + novoAviso + " registrado!");
            if (aluno.getQuantidadeAvisosNaoJustificados() >= 3) {
                System.out.println("📉 Penalidade aplicada! Nota do aluno: " + aluno.getNotaProcessual());
            }
        } else {
            System.out.println("\n✅ Devolução no prazo!");
        }

        livro.devolver();
        int index = emprestimos.indexOf(emp);
        EmprestimoFinalizado finalizado = new EmprestimoFinalizado(aluno, livro, emp.getDataRetirada(),
                emp.getDataPrevistaDevolucao(), dataDevolucao);
        emprestimos.set(index, finalizado);

        System.out.println("✅ Devolução registrada com sucesso!");
    }

    private static void consultarAvisos() {
        if (alunos.isEmpty()) {
            System.out.println("❌ Nenhum aluno cadastrado!");
            return;
        }

        System.out.println("\n--- CONSULTAR AVISOS ---");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println("   " + (i + 1) + " - " + alunos.get(i).getNome());
        }
        System.out.print("Digite o número do aluno: ");
        
        int idx;
        try {
            idx = Integer.parseInt(scanner.nextLine()) - 1;
            if (idx < 0 || idx >= alunos.size()) {
                System.out.println("❌ Aluno inválido!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Digite um número válido!");
            return;
        }
        
        Aluno aluno = alunos.get(idx);

        System.out.println("\n📋 AVISOS DE " + aluno.getNome());
        System.out.println("═══════════════════════════════════════════");
        System.out.println("🎓 Nota Processual: " + aluno.getNotaProcessual());
        System.out.println("📉 Desconto aplicado: " + aluno.getDescontoAplicado());
        System.out.println("⚠️ Total de avisos: " + aluno.getQuantidadeAvisosNaoJustificados());
        System.out.println();
        
        if (aluno.getAvisos().isEmpty()) {
            System.out.println("   Nenhum aviso registrado.");
        } else {
            for (Aviso aviso : aluno.getAvisos()) {
                System.out.println("   • " + aviso.exibirInfo());
            }
        }
    }

    private static void relatorioPenalidade() {
        System.out.println("\n--- ALUNOS COM PENALIDADE (nota < 10) ---");
        boolean encontrou = false;
        for (Aluno a : alunos) {
            if (a.getNotaProcessual() < 10) {
                System.out.println("   • " + a.getNome() + " | Nota: " + a.getNotaProcessual() + " | Desconto: " + a.getDescontoAplicado());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("   ✅ Nenhum aluno com penalidade.");
        }
    }

    private static void carregarDadosExemplo() {
        alunos.add(new Aluno("Ana Silva", "ana@email.com", "11999999999", "12345678901", "2024001", "3º A", 3));
        alunos.add(new Aluno("João Souza", "joao@email.com", "11988888888", "10987654321", "2024002", "3º A", 3));
        livros.add(new Livro("Dom Casmurro", "Machado de Assis", "9788535902811", "Globo", 2008, 3, Categoria.FICCAO, 256, "A1"));
        livros.add(new Livro("Matemática 3º Ano", "José Roberto", "9788535712345", "Moderna", 2023, 5, Categoria.DIDATICO, 320, "B2"));
        System.out.println("📚 Dados de exemplo carregados!");
    }
}
