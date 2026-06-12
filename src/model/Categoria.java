package model;

public enum Categoria {
    FICCAO("Ficção"),
    NAO_FICCAO("Não-ficção"),
    DIDATICO("Didático"),
    REVISTA("Revista"),
    TCC("TCC/Monografia"),
    PERIODICO("Periódico");
    
    private String descricao;
    
    Categoria(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}
