package view;

import model.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaGUI extends JFrame {
    
    private List<Aluno> alunos = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    
    private DefaultListModel<String> listModelAlunos;
    private DefaultListModel<String> listModelLivros;
    private DefaultListModel<String> listModelEmprestimos;
    
    private JList<String> listaAlunos;
    private JList<String> listaLivros;
    private JList<String> listaEmprestimos;
    
    private JTextArea areaLog;
    
    public BibliotecaGUI() {
        setTitle("📚 Sistema de Biblioteca Escolar");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        criarInterface();
        carregarDadosExemplo();
        atualizarTodasListas();
    }
    
    private void criarInterface() {
        setLayout(new BorderLayout(10, 10));
        
        JLabel titulo = new JLabel("SISTEMA DE BIBLIOTECA ESCOLAR - AVISOS E NOTA PROCESSUAL", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(0, 102, 204));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("📚 Livros", criarPainelLivros());
        tabbedPane.addTab("👥 Alunos", criarPainelAlunos());
        tabbedPane.addTab("📖 Empréstimos", criarPainelEmprestimos());
        tabbedPane.addTab("⚠️ Avisos", criarPainelAvisos());
        tabbedPane.addTab("📊 Relatórios", criarPainelRelatorios());
        
        add(tabbedPane, BorderLayout.CENTER);
        
        areaLog = new JTextArea();
        areaLog.setEditable(false);
        areaLog.setFont(new Font("Monospaced", Font.PLAIN, 11));
        areaLog.setBackground(new Color(240, 248, 255));
        JScrollPane scrollLog = new JScrollPane(areaLog);
        scrollLog.setBorder(BorderFactory.createTitledBorder("📋 Log do Sistema"));
        scrollLog.setPreferredSize(new Dimension(1080, 120));
        add(scrollLog, BorderLayout.SOUTH);
    }
    
    private JPanel criarPainelLivros() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        listModelLivros = new DefaultListModel<>();
        listaLivros = new JList<>(listModelLivros);
        JScrollPane scrollLivros = new JScrollPane(listaLivros);
        scrollLivros.setBorder(BorderFactory.createTitledBorder("📚 Acervo"));
        
        JPanel panelBotoes = new JPanel(new GridLayout(3, 1, 5, 5));
        JButton btnListar = new JButton("📋 Atualizar Lista");
        JButton btnCadastrar = new JButton("➕ Cadastrar Livro");
        JButton btnPesquisar = new JButton("🔍 Pesquisar Livro");
        
        panelBotoes.add(btnListar);
        panelBotoes.add(btnCadastrar);
        panelBotoes.add(btnPesquisar);
        
        painel.add(scrollLivros, BorderLayout.CENTER);
        painel.add(panelBotoes, BorderLayout.EAST);
        
        btnListar.addActionListener(e -> atualizarListaLivros());
        btnCadastrar.addActionListener(e -> cadastrarLivro());
        btnPesquisar.addActionListener(e -> pesquisarLivro());
        
        return painel;
    }
    
    private JPanel criarPainelAlunos() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        listModelAlunos = new DefaultListModel<>();
        listaAlunos = new JList<>(listModelAlunos);
        JScrollPane scrollAlunos = new JScrollPane(listaAlunos);
        scrollAlunos.setBorder(BorderFactory.createTitledBorder("👥 Alunos"));
        
        JPanel panelBotoes = new JPanel(new GridLayout(3, 1, 5, 5));
        JButton btnListar = new JButton("📋 Atualizar Lista");
        JButton btnCadastrar = new JButton("➕ Cadastrar Aluno");
        JButton btnConsultarAvisos = new JButton("⚠️ Consultar Avisos");
        
        panelBotoes.add(btnListar);
        panelBotoes.add(btnCadastrar);
        panelBotoes.add(btnConsultarAvisos);
        
        painel.add(scrollAlunos, BorderLayout.CENTER);
        painel.add(panelBotoes, BorderLayout.EAST);
        
        btnListar.addActionListener(e -> atualizarListaAlunos());
        btnCadastrar.addActionListener(e -> cadastrarAluno());
        btnConsultarAvisos.addActionListener(e -> consultarAvisoAluno());
        
        return painel;
    }
    
    private JPanel criarPainelEmprestimos() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        listModelEmprestimos = new DefaultListModel<>();
        listaEmprestimos = new JList<>(listModelEmprestimos);
        JScrollPane scrollEmprestimos = new JScrollPane(listaEmprestimos);
        scrollEmprestimos.setBorder(BorderFactory.createTitledBorder("📖 Empréstimos"));
        
        JPanel panelBotoes = new JPanel(new GridLayout(3, 1, 5, 5));
        JButton btnListar = new JButton("📋 Atualizar Lista");
        JButton btnRegistrar = new JButton("➕ Registrar Empréstimo");
        JButton btnDevolver = new JButton("🔄 Registrar Devolução");
        
        panelBotoes.add(btnListar);
        panelBotoes.add(btnRegistrar);
        panelBotoes.add(btnDevolver);
        
        painel.add(scrollEmprestimos, BorderLayout.CENTER);
        painel.add(panelBotoes, BorderLayout.EAST);
        
        btnListar.addActionListener(e -> atualizarListaEmprestimos());
        btnRegistrar.addActionListener(e -> registrarEmprestimo());
        btnDevolver.addActionListener(e -> registrarDevolucao());
        
        return painel;
    }
    
    private JPanel criarPainelAvisos() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JTextArea areaAvisos = new JTextArea();
        areaAvisos.setEditable(false);
        areaAvisos.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollAvisos = new JScrollPane(areaAvisos);
        scrollAvisos.setBorder(BorderFactory.createTitledBorder("⚠️ Avisos do Aluno"));
        
        JButton btnConsultar = new JButton("🔍 Consultar Avisos");
        
        btnConsultar.addActionListener(e -> {
            if (alunos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado!");
                return;
            }
            
            String[] nomes = alunos.stream().map(Aluno::getNome).toArray(String[]::new);
            String selecionado = (String) JOptionPane.showInputDialog(null, "Selecione o aluno:", "Consultar Avisos",
                JOptionPane.QUESTION_MESSAGE, null, nomes, nomes[0]);
            
            if (selecionado == null) return;
            
            Aluno aluno = alunos.stream().filter(a -> a.getNome().equals(selecionado)).findFirst().orElse(null);
            if (aluno == null) return;
            
            StringBuilder sb = new StringBuilder();
            sb.append("⚠️ AVISOS DE ").append(aluno.getNome()).append("\n");
            sb.append("═══════════════════════════════════════════\n");
            sb.append("Nota Processual: ").append(aluno.getNotaProcessual()).append("\n\n");
            
            if (aluno.getAvisos().isEmpty()) {
                sb.append("Nenhum aviso registrado.\n");
            } else {
                for (Aviso aviso : aluno.getAvisos()) {
                    sb.append(aviso.exibirInfo()).append("\n");
                }
            }
            areaAvisos.setText(sb.toString());
        });
        
        painel.add(btnConsultar, BorderLayout.NORTH);
        painel.add(scrollAvisos, BorderLayout.CENTER);
        
        return painel;
    }
    
    private JPanel criarPainelRelatorios() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JTextArea areaRelatorio = new JTextArea();
        areaRelatorio.setEditable(false);
        areaRelatorio.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollRelatorio = new JScrollPane(areaRelatorio);
        
        JPanel panelBotoes = new JPanel(new GridLayout(2, 1, 5, 5));
        JButton btnPenalidades = new JButton("⚠️ Alunos com Penalidade");
        JButton btnAtrasados = new JButton("📖 Empréstimos Atrasados");
        
        panelBotoes.add(btnPenalidades);
        panelBotoes.add(btnAtrasados);
        
        painel.add(panelBotoes, BorderLayout.NORTH);
        painel.add(scrollRelatorio, BorderLayout.CENTER);
        
        btnPenalidades.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("⚠️ ALUNOS COM PENALIDADE (nota < 10)\n");
            sb.append("═══════════════════════════════════════════\n\n");
            for (Aluno a : alunos) {
                if (a.getNotaProcessual() < 10) {
                    sb.append(a.getNome()).append(" | Nota: ").append(a.getNotaProcessual()).append("\n");
                }
            }
            areaRelatorio.setText(sb.toString());
        });
        
        btnAtrasados.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("📖 EMPRÉSTIMOS ATRASADOS\n");
            sb.append("═══════════════════════════════════════════\n\n");
            for (Emprestimo emp : emprestimos) {
                if (emp instanceof EmprestimoAtrasado || 
                    (emp instanceof EmprestimoAtivo && LocalDate.now().isAfter(emp.getDataPrevistaDevolucao()))) {
                    sb.append(emp.getLivro().getTitulo()).append(" - Aluno: ").append(emp.getAluno().getNome()).append("\n");
                }
            }
            areaRelatorio.setText(sb.toString());
        });
        
        return painel;
    }
    
    private void atualizarListaAlunos() {
        listModelAlunos.clear();
        for (Aluno a : alunos) {
            listModelAlunos.addElement(a.exibirInfo());
        }
        log("👥 Total de alunos: " + alunos.size());
    }
    
    private void atualizarListaLivros() {
        listModelLivros.clear();
        for (Livro l : livros) {
            listModelLivros.addElement(l.exibirInfo());
        }
        log("📚 Total de livros: " + livros.size());
    }
    
    private void atualizarListaEmprestimos() {
        listModelEmprestimos.clear();
        for (Emprestimo e : emprestimos) {
            listModelEmprestimos.addElement(e.exibirInfo());
        }
        log("📖 Total de empréstimos: " + emprestimos.size());
    }
    
    private void atualizarTodasListas() {
        atualizarListaAlunos();
        atualizarListaLivros();
        atualizarListaEmprestimos();
    }
    
    private void cadastrarLivro() {
        JTextField txtTitulo = new JTextField();
        JTextField txtAutor = new JTextField();
        JTextField txtIsbn = new JTextField();
        JTextField txtEditora = new JTextField();
        JTextField txtAno = new JTextField();
        JTextField txtQuantidade = new JTextField();
        JComboBox<Categoria> cbCategoria = new JComboBox<>(Categoria.values());
        JTextField txtPaginas = new JTextField();
        JTextField txtLocalizacao = new JTextField();
        
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.add(new JLabel("Título:")); panel.add(txtTitulo);
        panel.add(new JLabel("Autor:")); panel.add(txtAutor);
        panel.add(new JLabel("ISBN:")); panel.add(txtIsbn);
        panel.add(new JLabel("Editora:")); panel.add(txtEditora);
        panel.add(new JLabel("Ano:")); panel.add(txtAno);
        panel.add(new JLabel("Quantidade:")); panel.add(txtQuantidade);
        panel.add(new JLabel("Categoria:")); panel.add(cbCategoria);
        panel.add(new JLabel("Páginas:")); panel.add(txtPaginas);
        panel.add(new JLabel("Localização:")); panel.add(txtLocalizacao);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Cadastrar Livro", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Livro livro = new Livro(
                    txtTitulo.getText().trim(),
                    txtAutor.getText().trim(),
                    txtIsbn.getText().trim(),
                    txtEditora.getText().trim(),
                    Integer.parseInt(txtAno.getText().trim()),
                    Integer.parseInt(txtQuantidade.getText().trim()),
                    (Categoria) cbCategoria.getSelectedItem(),
                    Integer.parseInt(txtPaginas.getText().trim()),
                    txtLocalizacao.getText().trim()
                );
                livros.add(livro);
                atualizarListaLivros();
                log("✅ Livro cadastrado: " + livro.getTitulo());
                JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
    }
    
    private void cadastrarAluno() {
        JTextField txtNome = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtTelefone = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtMatricula = new JTextField();
        JTextField txtTurma = new JTextField();
        JTextField txtSerie = new JTextField();
        
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.add(new JLabel("Nome:")); panel.add(txtNome);
        panel.add(new JLabel("Email:")); panel.add(txtEmail);
        panel.add(new JLabel("Telefone:")); panel.add(txtTelefone);
        panel.add(new JLabel("CPF:")); panel.add(txtCpf);
        panel.add(new JLabel("Matrícula:")); panel.add(txtMatricula);
        panel.add(new JLabel("Turma:")); panel.add(txtTurma);
        panel.add(new JLabel("Série:")); panel.add(txtSerie);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Cadastrar Aluno", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Aluno aluno = new Aluno(
                    txtNome.getText().trim(), txtEmail.getText().trim(), txtTelefone.getText().trim(),
                    txtCpf.getText().trim(), txtMatricula.getText().trim(), txtTurma.getText().trim(),
                    Integer.parseInt(txtSerie.getText().trim())
                );
                alunos.add(aluno);
                atualizarListaAlunos();
                log("👥 Aluno cadastrado: " + aluno.getNome());
                JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
    }
    
    private void registrarEmprestimo() {
        if (alunos.isEmpty() || livros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastre alunos e livros primeiro!");
            return;
        }
        
        String[] nomesAlunos = alunos.stream().map(Aluno::getNome).toArray(String[]::new);
        String[] titulosLivros = livros.stream().filter(Livro::isDisponivel).map(Livro::getTitulo).toArray(String[]::new);
        
        if (titulosLivros.length == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum livro disponível!");
            return;
        }
        
        String alunoSelecionado = (String) JOptionPane.showInputDialog(null, "Selecione o aluno:", "Empréstimo",
            JOptionPane.QUESTION_MESSAGE, null, nomesAlunos, nomesAlunos[0]);
        
        if (alunoSelecionado == null) return;
        
        String livroSelecionado = (String) JOptionPane.showInputDialog(null, "Selecione o livro:", "Empréstimo",
            JOptionPane.QUESTION_MESSAGE, null, titulosLivros, titulosLivros[0]);
        
        if (livroSelecionado == null) return;
        
        Aluno aluno = alunos.stream().filter(a -> a.getNome().equals(alunoSelecionado)).findFirst().orElse(null);
        Livro livro = livros.stream().filter(l -> l.getTitulo().equals(livroSelecionado)).findFirst().orElse(null);
        
        if (aluno == null || livro == null) return;
        
        if (!aluno.podePegarLivro()) {
            JOptionPane.showMessageDialog(null, "Aluno já tem 3 livros emprestados!");
            return;
        }
        
        livro.emprestar();
        LocalDate dataRetirada = LocalDate.now();
        LocalDate dataPrevista = dataRetirada.plusDays(7);
        EmprestimoAtivo emprestimo = new EmprestimoAtivo(aluno, livro, dataRetirada, dataPrevista);
        emprestimos.add(emprestimo);
        aluno.adicionarEmprestimo(emprestimo);
        
        atualizarListaLivros();
        atualizarListaEmprestimos();
        
        log("📖 Empréstimo realizado: " + aluno.getNome() + " -> " + livro.getTitulo());
        JOptionPane.showMessageDialog(null, "Empréstimo realizado!\nData prevista: " + dataPrevista);
    }
    
    private void registrarDevolucao() {
        List<Emprestimo> ativos = emprestimos.stream()
            .filter(e -> e instanceof EmprestimoAtivo)
            .collect(Collectors.toList());
        
        if (ativos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há empréstimos ativos!");
            return;
        }
        
        String[] opcoes = ativos.stream().map(e -> e.getLivro().getTitulo() + " - " + e.getAluno().getNome()).toArray(String[]::new);
        String selecionado = (String) JOptionPane.showInputDialog(null, "Selecione o empréstimo:", "Devolução",
            JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
        
        if (selecionado == null) return;
        
        int idx = 0;
        for (int i = 0; i < opcoes.length; i++) {
            if (opcoes[i].equals(selecionado)) {
                idx = i;
                break;
            }
        }
        
        Emprestimo emp = ativos.get(idx);
        Aluno aluno = emp.getAluno();
        Livro livro = emp.getLivro();
        LocalDate dataDevolucao = LocalDate.now();
        
        if (dataDevolucao.isAfter(emp.getDataPrevistaDevolucao())) {
            int novoNumeroAviso = aluno.getQuantidadeAvisos() + 1;
            Aviso aviso = new Aviso("Atraso na devolução do livro: " + livro.getTitulo(), novoNumeroAviso);
            aluno.adicionarAviso(aviso);
            log("⚠️ Aviso #" + novoNumeroAviso + " registrado para " + aluno.getNome());
            
            if (aluno.getQuantidadeAvisosNaoJustificados() >= 3) {
                log("📉 Penalidade aplicada! Nota processual: " + aluno.getNotaProcessual());
                JOptionPane.showMessageDialog(null, "⚠️ Aviso #" + novoNumeroAviso + " registrado!\nNota do aluno: " + aluno.getNotaProcessual());
            } else {
                JOptionPane.showMessageDialog(null, "⚠️ Aviso #" + novoNumeroAviso + " registrado!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "✅ Devolução no prazo!");
        }
        
        livro.devolver();
        
        int index = emprestimos.indexOf(emp);
        EmprestimoFinalizado finalizado = new EmprestimoFinalizado(aluno, livro, emp.getDataRetirada(),
            emp.getDataPrevistaDevolucao(), dataDevolucao);
        emprestimos.set(index, finalizado);
        
        atualizarListaLivros();
        atualizarListaEmprestimos();
        
        log("🔄 Devolução registrada: " + aluno.getNome() + " devolveu " + livro.getTitulo());
    }
    
    private void consultarAvisoAluno() {
        if (alunos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado!");
            return;
        }
        
        String[] nomes = alunos.stream().map(Aluno::getNome).toArray(String[]::new);
        String selecionado = (String) JOptionPane.showInputDialog(null, "Selecione o aluno:", "Consultar Avisos",
            JOptionPane.QUESTION_MESSAGE, null, nomes, nomes[0]);
        
        if (selecionado == null) return;
        
        Aluno aluno = alunos.stream().filter(a -> a.getNome().equals(selecionado)).findFirst().orElse(null);
        if (aluno == null) return;
        
        StringBuilder sb = new StringBuilder();
        sb.append("⚠️ AVISOS DE ").append(aluno.getNome()).append("\n");
        sb.append("═══════════════════════════════════════════\n");
        sb.append("Nota Processual: ").append(aluno.getNotaProcessual()).append("\n\n");
        
        if (aluno.getAvisos().isEmpty()) {
            sb.append("Nenhum aviso registrado.\n");
        } else {
            for (Aviso aviso : aluno.getAvisos()) {
                sb.append(aviso.exibirInfo()).append("\n");
            }
        }
        
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(500, 400));
        JOptionPane.showMessageDialog(null, scroll, "Avisos do Aluno", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void pesquisarLivro() {
        String termo = JOptionPane.showInputDialog(null, "Digite título, autor ou ISBN:", "Pesquisar", JOptionPane.QUESTION_MESSAGE);
        if (termo == null || termo.trim().isEmpty()) return;
        
        List<Livro> resultados = livros.stream()
            .filter(l -> l.getTitulo().toLowerCase().contains(termo.toLowerCase()) ||
                         l.getAutor().toLowerCase().contains(termo.toLowerCase()) ||
                         l.getIsbn().contains(termo))
            .collect(Collectors.toList());
        
        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum livro encontrado!");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("🔍 RESULTADOS: \"").append(termo).append("\"\n");
        sb.append("═══════════════════════════════════════════\n\n");
        for (Livro l : resultados) {
            sb.append(l.exibirInfo()).append("\n");
        }
        
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Resultados", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void log(String msg) {
        areaLog.append("[" + LocalDate.now() + "] " + msg + "\n");
        areaLog.setCaretPosition(areaLog.getDocument().getLength());
    }
    
    private void carregarDadosExemplo() {
        alunos.add(new Aluno("Ana Silva", "ana@email.com", "11999999999", "12345678901", "2024001", "3º A", 3));
        alunos.add(new Aluno("João Souza", "joao@email.com", "11988888888", "10987654321", "2024002", "3º A", 3));
        
        livros.add(new Livro("Dom Casmurro", "Machado de Assis", "9788535902811", "Editora Globo", 2008, 3,
            Categoria.FICCAO, 256, "Estante A1"));
        livros.add(new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", "9788595081512", "Agir", 2015, 2,
            Categoria.FICCAO, 96, "Estante B2"));
        
        log("🚀 Sistema inicializado com dados de exemplo");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {}
            new BibliotecaGUI().setVisible(true);
        });
    }
}
