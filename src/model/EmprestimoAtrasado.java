package model;
import model.Livro;
import model.Aluno;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmprestimoAtrasado extends Emprestimo {
    
    public EmprestimoAtrasado(Aluno aluno, Livro livro, LocalDate dataRetirada, LocalDate dataPrevistaDevolucao) {
        super(aluno, livro, dataRetirada, dataPrevistaDevolucao);
    }
    
    @Override
    public String getStatus() {
        long dias = ChronoUnit.DAYS.between(dataPrevistaDevolucao, LocalDate.now());
        return String.format("ATRASADO (%d dias)", dias);
    }
}
