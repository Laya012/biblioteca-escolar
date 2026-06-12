package model;

public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private String editora;
    private int ano;
    private int quantidade;
    private int quantidadeDisponivel;
    private Categoria categoria;
    private int numeroPaginas;
    private String localizacao;
    
    public Livro(String titulo, String autor, String isbn, String editora,
                 int ano, int quantidade, Categoria categoria,
                 int numeroPaginas, String localizacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.editora = editora;
        this.ano = ano;
        this.quantidade = quantidade;
        this.quantidadeDisponivel = quantidade;
        this.categoria = categoria;
        this.numeroPaginas = numeroPaginas;
        this.localizacao = localizacao;
    }
    
    // Getters
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public String getEditora() { return editora; }
    public int getAno() { return ano; }
    public int getQuantidade() { return quantidade; }
    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public Categoria getCategoria() { return categoria; }
    public int getNumeroPaginas() { return numeroPaginas; }
    public String getLocalizacao() { return localizacao; }
    
    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }
    
    public boolean isDisponivel() {
        return quantidadeDisponivel > 0;
    }
    
    public void emprestar() {
        if (quantidadeDisponivel > 0) quantidadeDisponivel--;
    }
    
    public void devolver() {
        if (quantidadeDisponivel < quantidade) quantidadeDisponivel++;
    }
    
    public String exibirInfo() {
        return String.format("📖 %s | %s | %s | %d págs | Disp: %d/%d | Local: %s",
            titulo, autor, categoria.getDescricao(), numeroPaginas, quantidadeDisponivel, quantidade, localizacao);
    }
}
