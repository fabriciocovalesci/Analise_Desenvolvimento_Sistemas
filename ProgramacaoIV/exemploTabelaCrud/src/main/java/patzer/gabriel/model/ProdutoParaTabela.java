package patzer.gabriel.model;

import javafx.beans.property.*;

public class ProdutoParaTabela {
    private IntegerProperty _id;
    private StringProperty nome;
    private DoubleProperty preco;
    private IntegerProperty quantidade;

    public int get_id() {
        return _id.get();
    }

    public IntegerProperty _idProperty() {
        return _id;
    }

    public void set_id(int _id) {
        if (this._id != null)
            this._id.set(_id);
        else
            this._id = new SimpleIntegerProperty(_id);
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public double getPreco() {
        return preco.get();
    }

    public DoubleProperty precoProperty() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco.set(preco);
    }

    public int getQuantidade() {
        return quantidade.get();
    }

    public IntegerProperty quantidadeProperty() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade.set(quantidade);
    }

    public ProdutoParaTabela(int _id, String nome, double preco, int quantidade) {
        if (_id == -1)
            this._id = null;
        else
            this._id = new SimpleIntegerProperty(_id);
        this.nome = new SimpleStringProperty(nome);
        this.preco = new SimpleDoubleProperty(preco);
        this.quantidade = new SimpleIntegerProperty(quantidade);
    }

    @Override
    public String toString() {
        return "ProdutoParaTabela{" +
                "_id=" + _id +
                ", nome=" + nome +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                '}';
    }
}

