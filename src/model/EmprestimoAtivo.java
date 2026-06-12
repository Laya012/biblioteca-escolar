package model;
import model.Livro;
import model.Aluno;
import java.time.LocalDate;

public class EmprestimoAtivo extends Emprestimo {
    
    public EmprestimoAtivo(Aluno aluno, Livro livro, LocalDate dataRetirada, LocalDate dataPrevistaDevolucao) {
        super(aluno, livro, dataRetirada, dataPrevistaDevolucao);
    }
    
    @Override
    public String getStatus() {
        if (LocalDate.now().isAfter(dataPrevistaDevolucao)) {
            return "ATRASADO";
        }
        return "ATIVO";
    }
}
