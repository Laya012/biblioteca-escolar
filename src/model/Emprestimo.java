package model;

import model.Livro;
import model.Aluno;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public abstract class Emprestimo {
    protected Aluno aluno;
    protected Livro livro;
    protected LocalDate dataRetirada;
    protected LocalDate dataPrevistaDevolucao;
    protected LocalDate dataDevolucaoReal;
    
    public Emprestimo(Aluno aluno, Livro livro, LocalDate dataRetirada, LocalDate dataPrevistaDevolucao) {
        this.aluno = aluno;
        this.livro = livro;
        this.dataRetirada = dataRetirada;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }
    
    public Aluno getAluno() { return aluno; }
    public Livro getLivro() { return livro; }
    public LocalDate getDataPrevistaDevolucao() { return dataPrevistaDevolucao; }
    public LocalDate getDataRetirada() { return dataRetirada; }
    
    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }
    
    public long getDiasAtraso() {
        if (dataDevolucaoReal == null) return 0;
        if (dataDevolucaoReal.isAfter(dataPrevistaDevolucao)) {
            return ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataDevolucaoReal);
        }
        return 0;
    }
    
    public abstract String getStatus();
    
    public String exibirInfo() {
        return String.format("%s | Aluno: %s | Retirada: %s | Status: %s",
            livro.getTitulo(), aluno.getNome(),
            dataRetirada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), getStatus());
    }
}
