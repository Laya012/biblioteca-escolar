package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Pessoa {
    protected String nome;
    protected String email;
    protected String telefone;
    protected String cpf;
    protected LocalDate dataCadastro;
    
    public Pessoa(String nome, String email, String telefone, String cpf) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataCadastro = LocalDate.now();
    }
    
    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public LocalDate getDataCadastro() { return dataCadastro; }
    
    public abstract String exibirInfo();
    
    public String getDataCadastroFormatada() {
        return dataCadastro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}

