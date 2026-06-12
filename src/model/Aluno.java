
package model;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa {
    private String matricula;
    private String turma;
    private int serie;
    private double notaProcessual;
    private List<Aviso> avisos;
    private List<Emprestimo> emprestimos;
    
    public Aluno(String nome, String email, String telefone, String cpf,
                 String matricula, String turma, int serie) {
        super(nome, email, telefone, cpf);
        this.matricula = matricula;
        this.turma = turma;
        this.serie = serie;
        this.notaProcessual = 10.0;
        this.avisos = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }
    
    public String getMatricula() { return matricula; }
    public String getTurma() { return turma; }
    public int getSerie() { return serie; }
    public double getNotaProcessual() { return notaProcessual; }
    public List<Aviso> getAvisos() { return avisos; }
    public List<Emprestimo> getEmprestimos() { return emprestimos; }
    
    public void setNotaProcessual(double notaProcessual) {
        this.notaProcessual = Math.max(0, Math.min(10, notaProcessual));
    }
    
    public int getQuantidadeAvisos() {
        return avisos.size();
    }
    
    public int getQuantidadeAvisosNaoJustificados() {
        return (int) avisos.stream().filter(a -> !a.isJustificado()).count();
    }
    
    public double getDescontoAplicado() {
        int avisosNaoJustificados = getQuantidadeAvisosNaoJustificados();
        if (avisosNaoJustificados <= 2) return 0;
        return (avisosNaoJustificados - 2) * 0.5;
    }
    
    public void adicionarAviso(Aviso aviso) {
        avisos.add(aviso);
        atualizarNota();
    }
    
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }
    
    private void atualizarNota() {
        double desconto = getDescontoAplicado();
        notaProcessual = 10.0 - desconto;
        if (notaProcessual < 0) notaProcessual = 0;
    }
    
    public boolean podePegarLivro() {
        long emprestimosAtivos = emprestimos.stream()
            .filter(e -> e instanceof EmprestimoAtivo || e instanceof EmprestimoAtrasado)
            .count();
        return emprestimosAtivos < 3;
    }
    
    @Override
    public String exibirInfo() {
        return String.format("🎓 %s | Mat: %s | Turma: %s | Nota: %.1f | Avisos: %d",
            nome, matricula, turma, notaProcessual, getQuantidadeAvisosNaoJustificados());
    }
}
