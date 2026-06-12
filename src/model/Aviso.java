package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Aviso {
    private String motivo;
    private int numeroAviso;
    private LocalDate dataAviso;
    private boolean justificado;
    private String justificativa;
    
    public Aviso(String motivo, int numeroAviso) {
        this.motivo = motivo;
        this.numeroAviso = numeroAviso;
        this.dataAviso = LocalDate.now();
        this.justificado = false;
    }
    
    public int getNumeroAviso() { return numeroAviso; }
    public LocalDate getDataAviso() { return dataAviso; }
    public boolean isJustificado() { return justificado; }
    
    public void justificar(String justificativa) {
        this.justificado = true;
        this.justificativa = justificativa;
    }
    
    public String exibirInfo() {
        String status = justificado ? "[Justificado]" : "[Pendente]";
        return String.format("%dº Aviso - %s - %s %s",
            numeroAviso, motivo, dataAviso.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), status);
    }
}
