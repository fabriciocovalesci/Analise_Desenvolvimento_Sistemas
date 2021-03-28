package Model;

import java.util.List;

public class Produto {

    private int id;
    private String nome;
    private Double custo;
    private Double preco;

    public String toString(){
        return "Produto " + getNome() + " \ncusto "+ getCusto() + " \npreco " + getPreco() + " \nID " + getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

}
