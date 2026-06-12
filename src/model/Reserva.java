package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reserva {
    private Aluno aluno;
    private Livro livro;
    private LocalDate dataReserva;
    private int posicao;
    private boolean ativa;
    
    public Reserva(Aluno aluno, Livro livro, int posicao) {
        this.aluno = aluno;
        this.livro = livro;
        this.dataReserva = LocalDate.now();
        this.posicao = posicao;
        this.ativa = true;
    }
    
    public Aluno getAluno() { return aluno; }
    public Livro getLivro() { return livro; }
    public int getPosicao() { return posicao; }
    public boolean isAtiva() { return ativa; }
    
    public void setPosicao(int posicao) { this.posicao = posicao; }
    public void cancelar() { this.ativa = false; }
    
    public String exibirInfo() {
        if (ativa) {
            return String.format("⏰ %s | %s | Posição %d | %s",
                aluno.getNome(), livro.getTitulo(), posicao,
                dataReserva.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        return String.format("❌ CANCELADA: %s - %s", aluno.getNome(), livro.getTitulo());
    }
}
