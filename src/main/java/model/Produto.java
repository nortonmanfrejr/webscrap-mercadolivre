package model;

public class Produto {

    private String nome;
    private String valor;
    private String desconto;

    public Produto(String nome, String valor, String desconto){
        this.setNome(nome);
        this.setValor(valor);
        this.setDesconto(desconto);
    }

    public Produto(){}

    //#region Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    //endregion
}
