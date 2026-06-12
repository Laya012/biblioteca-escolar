package model;
import model.Livro;
import model.Aluno;
import java.time.LocalDate;

public class EmprestimoFinalizado extends Emprestimo {
    
    public EmprestimoFinalizado(Aluno aluno, Livro livro, LocalDate dataRetirada,
                                LocalDate dataPrevistaDevolucao, LocalDate dataDevolucaoReal) {
        super(aluno, livro, dataRetirada, dataPrevistaDevolucao);
        this.dataDevolucaoReal = dataDevolucaoReal;
    }
    
    @Override
    public String getStatus() {
        long diasAtraso = getDiasAtraso();
        if (diasAtraso > 0) {
            return String.format("FINALIZADO COM ATRASO (%d dias)", diasAtraso);
        }
        return "FINALIZADO NO PRAZO";
    }
}
