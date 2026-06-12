
package model;

public class Professor extends Pessoa {
    private String disciplina;
    private String departamento;
    
    public Professor(String nome, String email, String telefone, String cpf,
                     String disciplina, String departamento) {
        super(nome, email, telefone, cpf);
        this.disciplina = disciplina;
        this.departamento = departamento;
    }
    
    public String getDisciplina() { return disciplina; }
    public String getDepartamento() { return departamento; }
    
    @Override
    public String exibirInfo() {
        return String.format("👨‍🏫 %s | %s | %s", nome, disciplina, departamento);
    }
}
